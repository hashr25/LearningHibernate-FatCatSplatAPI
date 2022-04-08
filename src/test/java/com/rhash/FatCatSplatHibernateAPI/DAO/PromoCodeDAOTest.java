/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.DAO;

import com.rhash.FatCatSplatHibernateAPI.DTO.PromoCode;
import com.rhash.FatCatSplatHibernateAPI.DTO.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author capta
 */
@SpringBootTest
public class PromoCodeDAOTest {
    
    @Autowired
    private PromoCodeDAO promoCodeDao;
    
    @Autowired
    private UserDAO userDao;
    
    public PromoCodeDAOTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        userDao.deleteAll();
        promoCodeDao.deleteAll();
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateFindById(){
        //Arrange
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);
    
        //Act
        promoCode = promoCodeDao.save(promoCode);
        
        PromoCode fromDao = promoCodeDao.findById(promoCode.getId()).orElse(null);
        
        //Assert
        assertNotNull(fromDao);
        assertEquals(promoCode, fromDao);
    }
    
    @Test
    public void testCreateFindByPromoCode(){
        //Arrange
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);
    
        //Act
        promoCode = promoCodeDao.save(promoCode);
        
        PromoCode fromDao = promoCodeDao.findByPromoCode(promoCode.getPromoCode());
        
        //Assert
        assertNotNull(fromDao);
        assertEquals(promoCode, fromDao);
    }
    
    @Test
    public void testReadAllPromoCodes() {
        //Arrange
        PromoCode promoCode1 = new PromoCode();
        promoCode1.setPromoCode("TESTCODE");
        promoCode1.setDescription("Test Promo Code");
        promoCode1.setCurrencyType("Coin");
        promoCode1.setCurrencyGiven(1000);
        promoCode1 = promoCodeDao.save(promoCode1);
        
        PromoCode promoCode2 = new PromoCode();
        promoCode2.setPromoCode("TESTCODE2");
        promoCode2.setDescription("Test Promo Code #2");
        promoCode2.setCurrencyType("Star");
        promoCode2.setCurrencyGiven(15);
        promoCode2 = promoCodeDao.save(promoCode2);
        
        //Act
        List<PromoCode> promoCodes = promoCodeDao.findAll();
        
        //Assert
        assertEquals(2, promoCodes.size());
        assertTrue(promoCodes.contains(promoCode1));
        assertTrue(promoCodes.contains(promoCode2));
    }
    
    @Test
    public void testReadAllPromoCodesForUserAndUserUsePromoCode() {
        //Arrange
        PromoCode promoCode1 = new PromoCode();
        promoCode1.setPromoCode("TESTCODE");
        promoCode1.setDescription("Test Promo Code");
        promoCode1.setCurrencyType("Coin");
        promoCode1.setCurrencyGiven(1000);
        promoCode1 = promoCodeDao.save(promoCode1);
        
        PromoCode promoCode2 = new PromoCode();
        promoCode2.setPromoCode("TESTCODE2");
        promoCode2.setDescription("Test Promo Code #2");
        promoCode2.setCurrencyType("Star");
        promoCode2.setCurrencyGiven(15);
        promoCode2 = promoCodeDao.save(promoCode2);
        
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");
        user = userDao.save(user);
        
        //Act
        user.getUserPromoCodes().add(promoCode1);
        userDao.save(user);
        
        List<PromoCode> promoCodes = promoCodeDao.findAll();
        
        //Assert
        assertEquals(2, promoCodes.size());
        assertTrue(promoCodes.contains(promoCode1));
        assertTrue(promoCodes.contains(promoCode2));
//        assertTrue(promoCodes.get(0).getUsers().contains(user));
//        assertFalse(promoCodes.get(1).getUsers().contains(user));
    }
    
    @Test
    public void testUpdatePromoCode() {
        //Arrange
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);
        promoCode = promoCodeDao.save(promoCode);
        
        PromoCode fromDao = promoCodeDao.findById(promoCode.getId()).orElse(null);
        
        assertEquals(promoCode, fromDao);
        
        //Act
        promoCode.setPromoCode("TESTCODEEDIT");
        promoCode.setDescription("Test Promo Code Edit");
        promoCode.setCurrencyType("Star");
        promoCode.setCurrencyGiven(15);
        
        promoCodeDao.save(promoCode);
        
        //Assert
        assertNotEquals(promoCode, fromDao);
        
        fromDao = promoCodeDao.findById(promoCode.getId()).orElse(null);
        
        assertEquals(promoCode, fromDao);
    }
    
    
    @Test
    public void testDeletePromoCode() {
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
        
        PromoCode fromDao = promoCodeDao.findById(promoCode.getId()).orElse(null);
        
        assertEquals(promoCode, fromDao);
        
        user.getUserPromoCodes().add(promoCode);
        userDao.save(user);
        
        //Act
        //Trying to delete original object
        //This fails because the promoCode object in this state has no User objects in it.
//        promoCodeDao.delete(promoCode); //Fails
        
        //Trying to delete by Id
        //This works because its not using an object, just naively deleting by ID
        user.setUserPromoCodes(null);
        userDao.save(user);
        promoCodeDao.deleteById(promoCode.getId()); // Fails
        
        //Trying to delete the object from the dao with the user in it.
        //This works because the fromDao object has the user in the object.
//        fromDao = promoCodeDao.findById(promoCode.getId()).orElse(null);
//        promoCodeDao.delete(fromDao);
        
        fromDao = promoCodeDao.findById(promoCode.getId()).orElse(null);
        
        //Assert
        assertNull(fromDao);
    }
}
