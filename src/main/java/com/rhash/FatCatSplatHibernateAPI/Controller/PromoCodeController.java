/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.Controller;

import com.rhash.FatCatSplatHibernateAPI.DTO.*;
import com.rhash.FatCatSplatHibernateAPI.Service.FatCatSplatApiService;
import com.rhash.FatCatSplatHibernateAPI.Service.InvalidPromoCodeException;
import com.rhash.FatCatSplatHibernateAPI.Service.InvalidSecurityTokenException;
import com.rhash.FatCatSplatHibernateAPI.Service.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author rhash
 */
@RestController
@RequestMapping("/api")
public class PromoCodeController {
    
    @Autowired 
    FatCatSplatApiService service;
    
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User AddUser(@RequestBody AddUserRequest request) throws InvalidSecurityTokenException{
        return service.AddUser(request.getUser(), request.getSecurityToken());
    }
    
    @PostMapping("/userExists")
    public boolean DoesUserExist(@RequestBody GetUserRequest request ) throws InvalidSecurityTokenException {
        User user = service.GetUserByUsername(request.getUserName(), request.getSecurityToken());
        return user != null; //Returns true if user exists, returns false if null. 
    }
    
    @PostMapping("/promoCode")
    @ResponseStatus(HttpStatus.CREATED)
    public PromoCode AddPromoCode(@RequestBody AddPromoCodeRequest request) throws InvalidSecurityTokenException{
        return service.AddPromoCode(request.getPromoCode(), request.getSecurityToken());
    }
    
    @PostMapping("/userPromoCodes")
    public List<PromoCode> GetPromoCodesValidForUser(@RequestBody GetPromoCodesForUserRequest request) throws InvalidSecurityTokenException, InvalidUserException {
        return service.GetPromoCodesForUser(request.getUserName(), request.getSecurityToken());
    }
    
    @PostMapping("/usePromoCode")
    public ResponseEntity AddUser(@RequestBody UserUsePromoCodeRequest request) throws InvalidSecurityTokenException, InvalidUserException, InvalidPromoCodeException{
        service.UserUsePromoCode(request.getUserName(), request.getPromoCode(), request.getSecurityToken());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
