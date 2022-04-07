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
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author capta
 */
@SpringBootTest
public class FatCatSplatApiServiceTest {
    
    @Autowired
    FatCatSplatApiService service;

    @Value("${fatcatsplat.security-token}")
    private String validSecurityToken;

    // ///////////////////////////////////////////////
    //Only used to clear the database for each test //
    // ///////////////////////////////////////////////
    @Autowired
    UserDAO userDao;

    // ///////////////////////////////////////////////
    //Only used to clear the database for each test //
    // ///////////////////////////////////////////////
    @Autowired
    PromoCodeDAO promoCodeDao;
    
    public FatCatSplatApiServiceTest() {
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

    /**
     * Test of AddUser method, of class FatCatSplatApiService.
     */
    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        try {
            user = service.AddUser(user, validSecurityToken);

            assertTrue(user.getId() > 0);
        } catch (InvalidSecurityTokenException ex) {
            fail("InvalidSecurityTokenException thrown when valid token provided to service.AddUser().");
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user with valid security token.\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testAddUserInvalidSecurityToken() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        String garbageValidationToken = "HelloItsMe!~Adele";

        try {
            user = service.AddUser(user, garbageValidationToken);

            fail("InvalidSecurityTokenException expected to be thrown when invalid token provided to service.AddUser().");
        } catch (InvalidSecurityTokenException ex) {
            //Test passes
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user with invalid security token.\n" + ex.getMessage());
        }
    }

    /**
     * Test of GetUserByUsername method, of class FatCatSplatApiService.
     */
    @Test
    public void testGetUserByUsername() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        try {
            user = service.AddUser(user, validSecurityToken);
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user or promo code with valid security token during user use promo code test.\n" + ex.getMessage());
        }

        try {
            User fromService = service.GetUserByUsername(user.getUserName(), validSecurityToken);

            assertTrue(fromService != null);
            assertTrue(user.equals(fromService));
        } catch (InvalidSecurityTokenException ex) {
            fail("InvalidSecurityTokenException thrown when valid token provided to service.UserUsePromoCode().");
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testGetUserByUsernameInvalidSecurityToken() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        try {
            user = service.AddUser(user, validSecurityToken);
            
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user or promo code with valid security token during user use promo code test.\n" + ex.getMessage());
        }
        
        String garbageValidationToken = "HelloItsMe!~Adele";

        try {
            service.GetUserByUsername(user.getUserName(), garbageValidationToken);

            fail("InvalidSecurityTokenException expected to be thrown when invalid token provided to service.UserUsePromoCode().");
        } catch (InvalidSecurityTokenException ex) {
            //Test Passes
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testGetUserByUsernameNoUserExists() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");


        try {
            user = service.AddUser(user, validSecurityToken);
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user or promo code with valid security token during user use promo code test.\n" + ex.getMessage());
        }
        
        String garbageUserName = "HelloItsMe!~Adele";

        try {
            User fromService = service.GetUserByUsername(garbageUserName, validSecurityToken);

            //Expected to return null
            assertNull(fromService);
        } catch (InvalidSecurityTokenException ex) {
            fail("InvalidSecurityTokenException thrown when valid security token provided to service.UserUsePromoCode().");
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }

    /**
     * Test of UserUsePromoCode method, of class FatCatSplatApiService.
     */
    @Test
    public void testUserUsePromoCode() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);

        try {
            user = service.AddUser(user, validSecurityToken);
            promoCode = service.AddPromoCode(promoCode, validSecurityToken);
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user or promo code with valid security token during user use promo code test.\n" + ex.getMessage());
        }

        try {
            service.UserUsePromoCode(user.getUserName(), promoCode.getPromoCode(), validSecurityToken);

            assertTrue(user.getId() > 0);
            assertTrue(promoCode.getId() > 0);
        } catch (InvalidSecurityTokenException ex) {
            fail("InvalidSecurityTokenException thrown when valid token provided to service.UserUsePromoCode().");
        } catch (InvalidUserException ex) {
            fail("InvalidUserException thrown when valid user provided to service.UserUsePromoCode().");
        } catch (InvalidPromoCodeException ex) {
            fail("InvalidPromoCodeException thrown when valid promo code provided to service.UserUsePromoCode().");
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testUserUsePromoCodeInvalidSecurityToken() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);

        try {
            user = service.AddUser(user, validSecurityToken);
            promoCode = service.AddPromoCode(promoCode, validSecurityToken);
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user or promo code with valid security token during user use promo code test.\n" + ex.getMessage());
        }
        
        String garbageValidationToken = "HelloItsMe!~Adele";

        try {
            service.UserUsePromoCode(user.getUserName(), promoCode.getPromoCode(), garbageValidationToken);

            fail("InvalidSecurityTokenException expected to be thrown when invalid token provided to service.UserUsePromoCode().");
        } catch (InvalidSecurityTokenException ex) {
            //Test Passes
        } catch (InvalidUserException ex) {
            fail("InvalidUserException thrown when valid user provided to service.UserUsePromoCode().");
        } catch (InvalidPromoCodeException ex) {
            fail("InvalidPromoCodeException thrown when valid promo code provided to service.UserUsePromoCode().");
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testUserUsePromoCodeInvalidUser() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);

        try {
            user = service.AddUser(user, validSecurityToken);
            promoCode = service.AddPromoCode(promoCode, validSecurityToken);
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user or promo code with valid security token during user use promo code test.\n" + ex.getMessage());
        }
        
        String garbageUserName = "HelloItsMe!~Adele";

        try {
            service.UserUsePromoCode(garbageUserName, promoCode.getPromoCode(), validSecurityToken);

            fail("InvalidUserException expected to be thrown when invalid token provided to service.UserUsePromoCode().");
        } catch (InvalidSecurityTokenException ex) {
            fail("InvalidSecurityTokenException thrown when valid security token provided to service.UserUsePromoCode().");
        } catch (InvalidUserException ex) {
            //Test Passes
        } catch (InvalidPromoCodeException ex) {
            fail("InvalidPromoCodeException thrown when valid promo code provided to service.UserUsePromoCode().");
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testUserUsePromoCodeInvalidPromoCode() throws Exception {
        User user = new User();
        user.setUserName("Bill-123");
        user.setDeviceType("Crapple");
        user.setDeviceModel("iPhoney 32s");

        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);

        try {
            user = service.AddUser(user, validSecurityToken);
            promoCode = service.AddPromoCode(promoCode, validSecurityToken);
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add user or promo code with valid security token during user use promo code test.\n" + ex.getMessage());
        }
        
        String garbagePromoCode = "HelloItsMe!~Adele";

        try {
            service.UserUsePromoCode(user.getUserName(), garbagePromoCode, validSecurityToken);

            fail("InvalidPromoCodeException expected to be thrown when invalid token provided to service.UserUsePromoCode().");
        } catch (InvalidSecurityTokenException ex) {
            fail("InvalidSecurityTokenException thrown when valid user provided to service.UserUsePromoCode().");
        } catch (InvalidUserException ex) {
            fail("InvalidUserException thrown when valid user provided to service.UserUsePromoCode().");
        } catch (InvalidPromoCodeException ex) {
            //Test Passes
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }

    /**
     * Test of AddPromoCode method, of class FatCatSplatApiService.
     */
    @Test
    public void testAddPromoCode() throws Exception {
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode("TESTCODE");
        promoCode.setDescription("Test Promo Code");
        promoCode.setCurrencyType("Coin");
        promoCode.setCurrencyGiven(1000);

        try {
            promoCode = service.AddPromoCode(promoCode, validSecurityToken);

            assertTrue(promoCode.getId() > 0);
        } catch (InvalidSecurityTokenException ex) {
            fail("InvalidSecurityTokenException thrown when valid token provided to service.AddPromoCode().");
        } catch (Exception ex) {
            fail("General Exception thrown while attempting to add promo code with valid security token.\n" + ex.getMessage());
        }
    }

    /**
     * Test of GetPromoCodesForUser method, of class FatCatSplatApiService.
     */
    //This was missing from the original project. 
    //Need to implement tests to test valid inputs and all invalid inputs
//    @Test
//    public void testGetPromoCodesForUser() throws Exception {
//        System.out.println("GetPromoCodesForUser");
//        String userName = "";
//        String securityToken = "";
//        FatCatSplatApiService instance = new FatCatSplatApiService();
//        List<PromoCode> expResult = null;
//        List<PromoCode> result = instance.GetPromoCodesForUser(userName, securityToken);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
