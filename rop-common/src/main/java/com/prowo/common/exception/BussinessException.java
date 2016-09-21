package com.prowo.common.exception;

public class BussinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -5609021297595863252L;

    private String code;
    private String message;

    public BussinessException() {
    }

    public BussinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BussinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public BussinessException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
