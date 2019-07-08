package com.hlx.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = {"com.hlx.manager.controller"})
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception e){
        Map<String, Object> atrrs= new HashMap();
        String errorCode= e.getMessage();
        ErrorEnum errorEnum=ErrorEnum.getByCode(errorCode);
        atrrs.put("code",errorEnum.getCode());
        atrrs.put("message",errorEnum.getMessage());
        atrrs.put("conRetry",errorEnum.getConRetry());
        atrrs.put("type","Advice");
        Assert.isNull(atrrs,"Advice");
        return new ResponseEntity(atrrs, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
