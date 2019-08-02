package hoperun.pagoda.demo.bean;

import java.io.Serializable;

import hoperun.pagoda.demo.exception.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * coomon response bean
 *
 * @author zhangxiqin
 * @param <T>
 */
@ToString
@NoArgsConstructor
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -8130068200180072992L;
    private int code;
    private String msg;
    private T data;

    public static BaseResponse ok() {
        return ok("");
    }

    public static BaseResponse ok(Object o) {
        return new BaseResponse(ResultCode.SUCCESS, o);
    }

    public static BaseResponse failure(ResultCode code) {
        return failure(code, "");
    }

    public static BaseResponse failure(ResultCode code, Object o) {
        return new BaseResponse(code, o);
    }

    public BaseResponse(ResultCode resultCode) {
        setResultCode(resultCode);
    }

    public BaseResponse(ResultCode resultCode, T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
}
