package hoperun.pagoda.demo.service.impl;

import java.io.File;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.FileDownLoadRequest;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.service.FileDownService;
import hoperun.pagoda.demo.service.GroupService;
import hoperun.pagoda.dxlAnalyse.constant.Constants;
import hoperun.pagoda.dxlAnalyse.utils.AnalyseCommonUtils;

/**
 * aae transform service.
 * @author zhangxiqin
 *
 */
@Service
public class FileDownLoadServiceImpl implements FileDownService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownLoadServiceImpl.class);

    /**
     * group service.
     */
    @Autowired
    GroupService groupService;

    /**
     * get target folder path.
     */
    @Override
    public String genTargetFilepath(final FileDownLoadRequest request) {
        String filePath = "";
        // get group info
        Group group = Optional.ofNullable(groupService.findByGrupId(request.getGroupId())).orElse(new Group());
        switch (request.getType()) {
            case 1 :
            case 2 :
            case Constants.NUM_3 :
                filePath = group.getExportResultPath();
                break;
            case Constants.NUM_4 :
                filePath = group.getExportResultPath().concat(File.separator).concat(group.getNotesDBPath()).concat(File.separator)
                        .concat(request.getFileName().substring(0, request.getFileName().lastIndexOf('.')));
                break;
            case Constants.NUM_5 :
                filePath = group.getExportResultPath().concat(File.separator).concat(group.getNotesDBPath()).concat(File.separator)
                        .concat(Constant.AAE_RESULT);
                break;
            default :
                break;
        }
        return filePath;
    }

    /**
     * get target file name.
     */
    @Override
    public String getTargetFileName(final int type, final String path) {
        String fileName = "";
        // get group info
        switch (type) {
            case 1 :
                fileName = "allCountOfExport.xlsx";
                break;
            case 2 :
                fileName = "DB類似性纏め.xlsx";
                break;
            case Constant.NUMBER_3 :
                fileName = "referenceDB.xlsx";
                break;
            case Constants.NUM_4 :
                fileName = AnalyseCommonUtils.getDBInfoExcel(new File(path));
                break;
            case Constants.NUM_5 :
                fileName = Constant.AAE_RESULT_FILE_NAME;
                break;
            default :
                break;
        }
        return fileName;
    }
}
