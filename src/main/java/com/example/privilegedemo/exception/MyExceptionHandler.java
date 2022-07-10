package com.example.privilegedemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandle(HttpServletRequest req, Exception e) throws Exception {
        log.info("error", e);
        return "ERRO: " + e.getMessage();
    }
}
