package com.example.networkrequest.exception;

public class BaseException extends RuntimeException{

    private String errorCode;

    public BaseException(String detailMessage) {
    }

    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
