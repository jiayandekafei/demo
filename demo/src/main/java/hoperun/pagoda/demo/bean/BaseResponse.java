package hoperun.pagoda.demo.bean;

import java.io.Serializable;

import hoperun.pagoda.demo.exception.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * common response bean.
 *
 * @author zhangxiqin
 * @param <T>
 */
@ToString
@NoArgsConstructor
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -8130068200180072992L;
    /**
     * code.
     */
    private int code;
    /**
     * message.
     */
    private String msg;
    /**
     * reponse data, if error occured(except code=200),indicate the reason of error.
     */
    private transient T data;

    /**
     * successful response without any body.
     * 
     * @return BaseResponse
     */
    @SuppressWarnings("rawtypes")
    public static BaseResponse ok() {
        return ok("");
    }

    /**
     * successful response with body.
     * 
     * @param o object
     * @return BaseResponse reponse
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static BaseResponse ok(final Object o) {
        return new BaseResponse(ResultCode.SUCCESS, o);
    }

    /**
     * failure response.
     * 
     * @param code
     *            error code
     * @return BaseResponse reponse
     */

    @SuppressWarnings("rawtypes")
    public static BaseResponse failure(final ResultCode code) {
        return failure(code, "");
    }

    /**
     * 
     * @param code
     *            error code
     * @param o
     *            error reason
     * @return BaseResponse reponse
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static BaseResponse failure(final ResultCode code, final Object o) {
        return new BaseResponse(code, o);
    }

    /**
     * Constructor.
     * 
     * @param resultCode
     *            result code.
     */
    public BaseResponse(final ResultCode resultCode) {
        setResultCode(resultCode);
    }

    /**
     * Constructor.
     * 
     * @param resultCode
     *            result code
     * @param mdata
     *            reponse data
     */
    public BaseResponse(final ResultCode resultCode, final T mdata) {
        setResultCode(resultCode);
        this.data = mdata;
    }

    /**
     * set result code.
     * 
     * @param resultCode resultCode
     */
    public void setResultCode(final ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
}
