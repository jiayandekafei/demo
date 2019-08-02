package hoperun.pagoda.demo.exception;

public enum ResultCode {

    SUCCESS(200, "successful"),

    BAD_REQUEST(400, "Bad request parameter"), UNAUTHORIZED(401, "Authentication failed"), LOGIN_ERROR(401,
            "invalid user name or password"), FORBIDDEN(403,
                    "Access forbidden"), NOT_FOUND(404, "resource not found"), OPERATE_ERROR(405, ""), TIME_OUT(408, "time out"),

    SERVER_ERROR(500, "internal serval error"),;
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
