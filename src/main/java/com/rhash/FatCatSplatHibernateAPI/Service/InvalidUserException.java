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
public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
    
    public InvalidUserException(String message, Throwable cause){
        super(message, cause);
    }
}
