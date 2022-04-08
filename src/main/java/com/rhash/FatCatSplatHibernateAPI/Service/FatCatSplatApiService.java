/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.Service;

import com.rhash.FatCatSplatHibernateAPI.DAO.PromoCodeDAO;
import com.rhash.FatCatSplatHibernateAPI.DAO.UserDAO;
import com.rhash.FatCatSplatHibernateAPI.DTO.PromoCode;
import com.rhash.FatCatSplatHibernateAPI.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author rhash
 */
@Service
public class FatCatSplatApiService {

    @Value("${fatcatsplat.security-token}")
    private String actualSecurityToken;

    @Autowired
    UserDAO userDao;

    @Autowired
    PromoCodeDAO promoCodeDao;

    //User Methods
    public User AddUser(User user, String securityToken) throws InvalidSecurityTokenException {
        validateSecurityToken(securityToken);
        return userDao.save(user);
    }
    
    public User GetUserByUsername(String userName, String securityToken) throws InvalidSecurityTokenException {
        validateSecurityToken(securityToken);
        return userDao.findByUserName(userName);
    }

    public void UserUsePromoCode(String userName, String code, String securityToken) throws InvalidSecurityTokenException, InvalidUserException, InvalidPromoCodeException {
        validateSecurityToken(securityToken);

        User user = validateUser(userName);

        PromoCode promoCode = validatePromoCode(code);
        
        user.getUserPromoCodes().add(promoCode);

        userDao.save(user);
    }

    public PromoCode AddPromoCode(PromoCode promoCode, String securityToken) throws InvalidSecurityTokenException {
        validateSecurityToken(securityToken);
        return promoCodeDao.save(promoCode);
    }

    public List<PromoCode> GetPromoCodesForUser(String userName, String securityToken) throws InvalidSecurityTokenException, InvalidUserException {
        validateSecurityToken(securityToken);
        User user = validateUser(userName);
        
        List<PromoCode> allCodes = promoCodeDao.findAll();
        allCodes.removeAll(user.getUserPromoCodes());

        return allCodes;
    }

    private void validateSecurityToken(String securityToken) throws InvalidSecurityTokenException {
        if (!securityToken.equals(actualSecurityToken)) {
            throw new InvalidSecurityTokenException("You are not authorized to perform this action.");
        }
    }

    private User validateUser(String userName) throws InvalidUserException {
        User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new InvalidUserException("That user does not exist in the system.");
        }

        return user;
    }

    private PromoCode validatePromoCode(String code) throws InvalidPromoCodeException {
        PromoCode promoCode = promoCodeDao.findByPromoCode(code);
        if (promoCode == null) {
            throw new InvalidPromoCodeException("That promo code does not exist in the system.");
        }

        return promoCode;
    }
}
