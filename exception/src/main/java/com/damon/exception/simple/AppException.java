package com.damon.exception.simple;

/**
 * Created by damon on 16/4/14.
 */
public class AppException extends RuntimeException {

    private int errorCode;

    private String messageText;

    public AppException() {
    }

    public AppException(int errorCode, String messageText) {
        this.errorCode = errorCode;
        this.messageText = messageText;
    }

    public AppException(String message, int errorCode, String messageText) {
        super(message);
        this.errorCode = errorCode;
        this.messageText = messageText;
    }

    public AppException(String message, Throwable cause, int errorCode, String messageText) {
        super(message, cause);
        this.errorCode = errorCode;
        this.messageText = messageText;
    }

    public AppException(Throwable cause, int errorCode, String messageText) {
        super(cause);
        this.errorCode = errorCode;
        this.messageText = messageText;
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode, String messageText) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.messageText = messageText;
    }
}
