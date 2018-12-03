package com.primeton.renyh.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.primeton.renyh.demo.common.ResponseResult;
import com.primeton.renyh.demo.exception.DemoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //处理自定义的异常
    @ExceptionHandler(DemoException.class)
    public  ResponseEntity<ResponseResult<Object>> customHandler(DemoException ce, HttpServletRequest request, HttpServletResponse res,
                                                                 Exception e){
        logger.error(ce.getMessage(),ce);
        return new ResponseEntity<>( new ResponseResult<>(ce.getErrCode(),ce.getMessage()), ce.getHttpStatus());
    }

    //其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus ( value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseResult<Object>> exceptionHandler(Exception e){
        logger.error( "系統异常",e.getMessage());
        HttpStatus httpstatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>( new ResponseResult<>( 500, e.getMessage()),httpstatus);
    }

    public GlobalExceptionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }


}