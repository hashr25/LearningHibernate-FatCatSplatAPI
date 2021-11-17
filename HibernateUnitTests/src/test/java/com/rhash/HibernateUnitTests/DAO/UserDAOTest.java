/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.HibernateUnitTests.DAO;

import com.rhash.HibernateUnitTests.DTO.PromoCode;
import com.rhash.HibernateUnitTests.DTO.User;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author capta
 */
@SpringBootTest
public class UserDAOTest {
    
    @Autowired
    private PromoCodeDAO promoCodeDao;
    
    @Autowired
    private UserDAO userDao;
    
    public UserDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        promoCodeDao.deleteAll();
        userDao.deleteAll();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateUserGetUserById() {
        //Arrange
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");
        
        //Act
        user = userDao.save(user);
        
        User fromDao = userDao.findById(user.getId()).orElse(null);
        
        //Assert
        assertEquals(user, fromDao);
    }
    
    
    @Test
    public void testCreateUserGetUserByUserName() {
        //Arrange
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");
        
        //Act
        user = userDao.save(user);
        
        User fromDao = userDao.findByUserName(user.getUserName());
        
        //Assert
        assertEquals(user, fromDao);
    }
    
    
    @Test
    public void testReadAllUsers() {
        //Arrange
        User user1 = new User();
        user1.setUserName("Bill-123");
        user1.setDeviceType("Crapple");
        user1.setDeviceModel("iPhoney 32s");
        user1 = userDao.save(user1);
        
        User user2 = new User();
        user2.setUserName("Ted-456");
        user2.setDeviceType("Androod");
        user2.setDeviceModel("Samdung Poo Phone");
        user2 = userDao.save(user2);
        
        //Act
        List<User> users = userDao.findAll();
        
        //Assert
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }
    
    @Test
    public void testUpdateUser() {
        //Arrange
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");
        user = userDao.save(user);
        
        User fromDao = userDao.findById(user.getId()).orElse(null);
        
        assertEquals(user, fromDao);
        
        //Act
        user.setUserName("Ted-456");
        user.setDeviceType("Androod");
        user.setDeviceModel("Samdung Poo Phone");
        
        userDao.save(user);
        
        //Assert
        assertNotEquals(user, fromDao);
        
        fromDao = userDao.findById(user.getId()).orElse(null);
        
        assertEquals(user, fromDao);
    }
    
    
    @Test
    public void testDeleteUser() {
        //Arrange
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");
        user = userDao.save(user);
        
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);
        promoCode = promoCodeDao.save(promoCode);
        
        user.getUserPromoCodes().add(promoCode);
        userDao.save(user);
        
        
        //Act
        userDao.deleteById(user.getId());
        
        User fromDao = userDao.findById(user.getId()).orElse(null);
        
        //Assert
        assertNull(fromDao);
    }
}
