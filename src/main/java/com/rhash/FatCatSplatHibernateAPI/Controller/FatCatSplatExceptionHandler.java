/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.Controller;

import com.rhash.FatCatSplatHibernateAPI.DTO.FatCatSplatError;
import com.rhash.FatCatSplatHibernateAPI.Service.InvalidPromoCodeException;
import com.rhash.FatCatSplatHibernateAPI.Service.InvalidSecurityTokenException;
import com.rhash.FatCatSplatHibernateAPI.Service.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author rhash
 */
@ControllerAdvice
@RestController
public class FatCatSplatExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(InvalidPromoCodeException.class)
    public final ResponseEntity<FatCatSplatError> handleInvalidPromoCodeException(
            InvalidPromoCodeException ex,
            WebRequest request){
        
        FatCatSplatError err = new FatCatSplatError();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(InvalidSecurityTokenException.class)
    public final ResponseEntity<FatCatSplatError> handleInvalidSecurityTokenException(
            InvalidSecurityTokenException ex,
            WebRequest request){
        
        FatCatSplatError err = new FatCatSplatError();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(InvalidUserException.class)
    public final ResponseEntity<FatCatSplatError> handleInvalidUserException(
            InvalidUserException ex,
            WebRequest request){
        
        FatCatSplatError err = new FatCatSplatError();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
