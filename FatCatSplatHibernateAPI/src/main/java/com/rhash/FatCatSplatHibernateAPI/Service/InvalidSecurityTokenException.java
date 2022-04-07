/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.Service;

/**
 *
 * @author rhash
 */
public class InvalidSecurityTokenException extends Exception {
    public InvalidSecurityTokenException(String message) {
        super(message);
    }
    
    public InvalidSecurityTokenException(String message, Throwable cause){
        super(message, cause);
    }
    
}
