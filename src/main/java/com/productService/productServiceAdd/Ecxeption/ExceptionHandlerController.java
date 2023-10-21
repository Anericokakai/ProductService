package com.productService.productServiceAdd.Ecxeption;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String,String > entityNotFoundExceptionHanlder(EntityNotFoundException ex){

        Map<String ,String > errorMap= new HashMap<>();
        errorMap.put("errorMessage",ex.getMessage());
        return  errorMap;
    }


//! bad request by feign client handler

@ExceptionHandler(FeignException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleFeignBadRequest(FeignException ex){

      Map<String,String > errMap= new HashMap<>();
errMap.put("errorMessage",getErrorFromResponse(ex.contentUTF8()));

return errMap;
}

    private String getErrorFromResponse(String response) {
        // Parse the error message from the response
        return response.substring(response.indexOf("\"errorMessage\":\"") + 16, response.indexOf("\"}"));
    }


//    custom exception for no products
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String,String > notFoundHanlder(NotFoundException ex){
Map<String ,String > errorMap= new HashMap<>();
errorMap.put("errorMessage",ex.getMessage());
return  errorMap;
    }


}
