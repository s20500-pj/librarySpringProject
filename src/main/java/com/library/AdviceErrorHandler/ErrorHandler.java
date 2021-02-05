package com.library.AdviceErrorHandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse customerror(CustomException ex){
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleInternalServer(HttpServerErrorException.InternalServerError ex){
        return new ApiErrorResponse(ex.getStatusCode(), ex);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleIllegalArgument(IllegalArgumentException ex){
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(NoHandlerFoundException.class )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(Exception ex) {
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(Exception.class )
    public ApiErrorResponse unknownException(Exception ex) {
        return new ApiErrorResponse(HttpStatus.NOT_FOUND,ex);
    }




}
