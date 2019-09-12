package hoperun.pagoda.demo.constant;

public enum StatusCode {

    W("watting for approve"), A("approved");
    private String msg;

    StatusCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
