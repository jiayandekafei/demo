package hoperun.pagoda.demo.utils;

import java.io.File;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;

/**
 * notes utils.
 *
 * @author zhangxiqin
 *
 */
@Component
public final class NotesUtils {

    /**
     * no parameter Construtor.
     */
    private NotesUtils() {
    }

    /**
     * check notes server.
     * @param ip  notes server ip
     * @param groupName  group name
     * @return serverIp
     */
    public static String getHost(final String ip, final String groupName) {
        // server ip
        String serverIP = getNaNValue(ip, " notes server address can be null ,please kindly check, the group name is: " + groupName);
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

}
