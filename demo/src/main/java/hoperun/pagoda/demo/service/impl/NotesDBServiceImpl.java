package hoperun.pagoda.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.service.GroupService;
import hoperun.pagoda.demo.service.NotesDBService;
import hoperun.pagoda.demo.utils.NotesUtils;
import hoperun.pagoda.diiopanalyse.export.api.ExportDominoserverData;
import hoperun.pagoda.diiopanalyse.export.dto.ExportDataDto;

/**
 * notes db service.
 * @author zhangxiqin
 *
 */
@Service
public class NotesDBServiceImpl implements NotesDBService {

    /**
     * group service.
     */
    @Autowired
    GroupService groupService;

    /**
     * export DB info.
     * @param type 1 if export single DB otherwise 2
     * @param groupId target group id
     * @param dbName target db name(only for single export)
     */
    @Override
    public void export(final int type, final int groupId, final String dbName) {
        ExportDataDto para = genExpportParameter(groupId);
        try {
            // export one DB
            if (1 == type) {
                para.setDatabaseName(dbName);
                ExportDominoserverData.exportDataByDominoserver(para);
            } else {
                // get export all DB
                ExportDominoserverData.exportAllDataByDominoserver(para);
            }
        } catch (Exception e) {
            throw new BusinessException(BaseResponse.failure(ResultCode.SERVER_ERROR, e.getMessage()));
        }
    }

    /**
     * Generate export parameter.
     * @param groupId groupId
     * @return ExportDataDto Export Data paramter
     */
    private ExportDataDto genExpportParameter(final int groupId) {
        // get group info
        Group group = Optional.ofNullable(groupService.findByGrupId(groupId)).orElse(new Group());
        String host = NotesUtils.getHost(group.getServer(), group.getGroupname());
        String user = NotesUtils.getNaNValue(group.getServerUser(),
                " notes server's user  can be null ,please kindly check, the group name is: " + group.getGroupname());
        String password = NotesUtils.getNaNValue(group.getServerPassword(),
                " notes server's user  can be null ,please kindly check, the group name is: " + group.getGroupname());
        String exportPath = NotesUtils.getExportPath(group.getExportResultPath(), group.getGroupname());

        final ExportDataDto exportDataDto = new ExportDataDto();
        exportDataDto.setNotesHost(host);
        exportDataDto.setUserName(user);
        exportDataDto.setPassword(password);
        exportDataDto.setOutPutFilePath(exportPath);
        exportDataDto.setNsfPath(group.getNotesDBPath());
        return exportDataDto;
    }
}
