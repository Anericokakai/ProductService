package com.productService.productServiceAdd.Ecxeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class )
    public Map<String,String >  invalidInputsException(MethodArgumentNotValidException ex){
        Map<String ,String > errorMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(er->{
            errorMap.put(er.getField(),er.getDefaultMessage());
        });

        return errorMap;


    }

}
