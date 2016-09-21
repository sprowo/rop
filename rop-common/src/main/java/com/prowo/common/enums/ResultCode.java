package com.prowo.common.enums;

/**
 * @author linan
 *         响应码
 */
public enum ResultCode {

    // 系统响应码
    SYS_SUCCESS("0000", "成功"),
    SYS_FAILED("9999", "失败"),
    SYS_UNKNOWN_EXCEPTION("0099", "系统未知异常"),
    SYS_TIMEOUT("0002", "超时"),
    SYS_REQUEST_CONCURRENCY("0098", "并发请求"),;
    private String code;

    private String msg;

    private ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
