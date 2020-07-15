package hoperun.pagoda.demo.utils;

import java.io.File;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.diiopanalyse.export.dto.ExportDataDto;

/**
 * notes utils.
 *
 * @author zhangxiqin
 *
 */
@Component
public final class DBUtils {

    /**
     * no parameter Construtor.
     */
    private DBUtils() {
    }

    /**
     * check notes server.
     * @param ip  notes server ip
     * @param groupName  group name
     * @return serverIp
     */
    public static String getHost(final String ip, final String groupName) {
        // server ip
        String serverIP = getNaNValue(ip, " notes server address can not be blank ,please kindly check, the group name is: " + groupName);
        Pattern pattern = Pattern.compile(Constant.IP_REGEX);
        Matcher matcher = pattern.matcher(serverIP);
        if (!matcher.matches()) {
            throw new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST,
                    "notes server address is incorrect,please kindly check, the group name is: " + groupName));
        }
        return serverIP;
    }

    /**
     * check notes server.
     * @param value  value
     * @param message message
     * @return not null value.
     */
    public static String getNaNValue(final String value, final String message) {
        return Optional.ofNullable(value).orElseThrow(() -> new BusinessException(BaseResponse.failure(ResultCode.BAD_REQUEST, message)));
    }

    /**
     * get export path.
     * @param path  path
     * @param groupName groupName
     * @return not null value.
     */
    public static String getExportPath(final String path, final String groupName) {
        File file = new File(path);
        if (!file.exists()) {
            throw new BusinessException(
                    BaseResponse.failure(ResultCode.BAD_REQUEST, "export path dose not exist,please kindly check, the group name is: " + groupName));
        }
        return path;
    }

    /**
     * Generate export parameter.
     * @param group group
     * @return ExportDataDto Export Data paramter
     */
    public static ExportDataDto genExportParameter(final Group group) {
        String host = getHost(group.getServer(), group.getGroupname());
        String user = getNaNValue(group.getServerUser(),
                " notes server's user can not be blank ,please kindly check, the group name is: " + group.getGroupname());
        String password = getNaNValue(group.getServerPassword(),
                " notes server's password can not be blank ,please kindly check, the group name is: " + group.getGroupname());
        String exportPath = getExportPath(group.getExportResultPath(), group.getGroupname());

        final ExportDataDto exportDataDto = new ExportDataDto();
        exportDataDto.setNotesHost(host);
        exportDataDto.setUserName(user);
        exportDataDto.setPassword(password);
        exportDataDto.setOutPutFilePath(exportPath);
        exportDataDto.setDataSourcePath(exportPath.concat(File.separator).concat(group.getNotesDBPath()));
        exportDataDto.setNsfPath(group.getNotesDBPath());
        return exportDataDto;
    }
}
