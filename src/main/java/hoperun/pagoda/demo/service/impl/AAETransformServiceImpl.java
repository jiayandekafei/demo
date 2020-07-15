package hoperun.pagoda.demo.service.impl;

import java.io.File;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoperun.pagoda.demo.bean.AAETransformRequest;
import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.service.AAETransformService;
import hoperun.pagoda.demo.service.GroupService;
import hoperun.pagoda.demo.utils.DBUtils;
import hoperun.pagoda.diiopanalyse.export.api.ExportDominoserverData;
import hoperun.pagoda.diiopanalyse.export.dto.ExportDataDto;
import hoperun.pagoda.dxlAnalyse.XmlGenerator;

/**
 * aae transsform service.
 * @author zhangxiqin
 *
 */
@Service
public class AAETransformServiceImpl implements AAETransformService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AAETransformServiceImpl.class);

    /**
     * group service.
     */
    @Autowired
    GroupService groupService;

    /**
     * aae transform.
     * @param  request AAETransformRequest request
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BaseResponse<String> transform(final AAETransformRequest request) {
        String method = "transform";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "transform started");
        }
        String fileName = Constant.AAE_RESULT_FILE_NAME.concat(String.valueOf(System.currentTimeMillis()));
        // get group info
        Group group = Optional.ofNullable(groupService.findByGrupId(request.getGroupId())).orElse(new Group());
        ExportDataDto param = DBUtils.genExportParameter(group);
        param.setColorCode(request.getColor());
        String resultFolder = param.getOutPutFilePath().concat(File.separator).concat(param.getNsfPath()).concat(File.separator)
                .concat(Constant.AAE_RESULT).concat(File.separator).concat(fileName);
        param.setOutPutFilePath(resultFolder);
        try {
            // one DB
            if (1 == request.getType()) {
                param.setType(1);
                param.setDataSourcePath(param.getDataSourcePath().concat(File.separator).concat(request.getDbName()));
            }
            ExportDominoserverData.aaeAutoMigration(param);
            new XmlGenerator().compress(resultFolder, resultFolder + Constant.FILE_SUFFIX_ZIP);
            FileUtils.deleteDirectory(new File(resultFolder));

        } catch (Exception e) {
            e.printStackTrace();
            // update execute status as executing
            LOGGER.error(Constant.LOG_PATTERLN, method, e);
            return BaseResponse.failure(ResultCode.SERVER_ERROR, e.getMessage());
        }
        return BaseResponse.ok(fileName + Constant.FILE_SUFFIX_ZIP);
    }

}
