package com.library.AdviceErrorHandler;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

    private HttpStatus status;
    private String message;
    private String debugMessage;

    ApiErrorResponse(HttpStatus status) {
        this.status = status;
    }

    ApiErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


    ApiErrorResponse(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                '}';
    }
}
