package com.example.demo.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yef
 * @date 2019/8/7
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object methodArgumentNotValidExceptionHandler(BindException e){
        BindingResult result = e.getBindingResult();
        Map<String,String> fieldErrors=new HashMap<>();
        for(FieldError fieldError:result.getFieldErrors()){
            fieldErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return fieldErrors;
    }
}
