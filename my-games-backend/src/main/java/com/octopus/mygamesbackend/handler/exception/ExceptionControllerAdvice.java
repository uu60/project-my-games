package com.octopus.mygamesbackend.handler.exception;

import com.octopus.mygamesbackend.utils.properties.MyConstants;
import com.octopus.mygamesbackend.utils.http.Resp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"pers.djz.gulimall.product.controller"})
public class ExceptionControllerAdvice {
    @ExceptionHandler(Throwable.class)
    public Resp handleException(Throwable throwable) {
        throwable.printStackTrace();
        return Resp.error(MyConstants.HTTP_INTERNAL_SERVER_ERROR).put("message", throwable.getMessage()).put("exception_class", throwable.getClass());
    }
}
