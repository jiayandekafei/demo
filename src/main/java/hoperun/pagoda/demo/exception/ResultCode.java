package hoperun.pagoda.demo.exception;

/**
 * Result code.
 * @author zhangxiqin
 *
 */
public enum ResultCode {

    /**
     * successful.
     */
    SUCCESS(200, "successful"),
    /**
     * bad request.
     */
    BAD_REQUEST(400, "Bad request parameter"),
    /**
     * Authentication failed.
     */
    UNAUTHORIZED(401, "Authentication failed"),
    /**
     * user name or password is invalid.
     */
    LOGIN_ERROR(401, "invalid user name or password"),

    /**
     * has no acceess right.
     */
    FORBIDDEN(403, "Access forbidden"),
    /**
     * not found.
     */
    NOT_FOUND(404, "resource not found"),
    /**
     * internal serval error.
     */
    SERVER_ERROR(500, "internal serval error"),;
    /**
     * code.
     */
    private int code;
    /**
     * message.
     */
    private String msg;

    /**
     * Constructor.
     * @param mCode code
     * @param message message
     */
    ResultCode(final int mCode, final String message) {
        this.code = mCode;
        this.msg = message;
    }

    /**
     * get code.
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * get message.
     * @return message
     */
    public String getMsg() {
        return msg;
    }
}
