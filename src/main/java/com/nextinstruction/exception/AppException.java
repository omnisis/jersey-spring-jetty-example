package com.nextinstruction.exception;

import java.io.Serializable;

public class AppException extends RuntimeException implements Serializable {


    public AppException(String s) {
        super(s);
    }

    public AppException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
