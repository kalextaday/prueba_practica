package com.example.demo.domain.exception;


import java.io.Serializable;

public class UserException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7405432357544406448L;

    private final ErrorInfoResponse errorInfoResponse;

    public UserException(ErrorInfoResponse errorInfoResponse){
        super(errorInfoResponse.getMessage());
        this.errorInfoResponse = errorInfoResponse;
    }

    public ErrorInfoResponse getErrorInfoResponse() {
        return errorInfoResponse;
    }
}
