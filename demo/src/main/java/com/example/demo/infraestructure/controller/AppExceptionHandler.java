package com.example.demo.infraestructure.controller;

import com.example.demo.domain.exception.AccountException;
import com.example.demo.domain.exception.TransactionException;
import com.example.demo.domain.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<?> handleException(UserException exception, WebRequest request) {
        exception.getErrorInfoResponse().setUriRequested(request.getDescription(false));

        return ResponseEntity.status(exception.getErrorInfoResponse().getStatusCode()).body(exception.getErrorInfoResponse());
    }

    @ResponseBody
    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity<?> handleExceptionTransaction(TransactionException exception, WebRequest request) {
        exception.getErrorInfoResponse().setUriRequested(request.getDescription(false));

        return ResponseEntity.status(exception.getErrorInfoResponse().getStatusCode()).body(exception.getErrorInfoResponse());
    }

    @ResponseBody
    @ExceptionHandler(value = AccountException.class)
    public ResponseEntity<?> handleExceptionAccount(AccountException exception, WebRequest request) {
        exception.getErrorInfoResponse().setUriRequested(request.getDescription(false));

        return ResponseEntity.status(exception.getErrorInfoResponse().getStatusCode()).body(exception.getErrorInfoResponse());
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<?> handleExceptionGlobal(UserException exception, WebRequest request) {
        exception.getErrorInfoResponse().setUriRequested(request.getDescription(false));

        return ResponseEntity.status(exception.getErrorInfoResponse().getStatusCode()).body(exception.getErrorInfoResponse());
    }
}
