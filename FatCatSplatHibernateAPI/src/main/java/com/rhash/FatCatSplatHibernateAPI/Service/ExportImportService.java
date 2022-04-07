package com.rhash.FatCatSplatHibernateAPI.Service;

import com.rhash.FatCatSplatHibernateAPI.DAO.PromoCodeDAO;
import com.rhash.FatCatSplatHibernateAPI.DAO.UserDAO;
import com.rhash.FatCatSplatHibernateAPI.DTO.PromoCode;
import com.rhash.FatCatSplatHibernateAPI.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExportImportService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private PromoCodeDAO promoCodeDao;

    @Value("google.user_filename")
    private String userFileName;

    @Value("google.promo_code_filename")
    private String promoCodeFileName;

    @Value("google.access_token")
    private String googleAccessToken;

    private final String LINE_DELIMITER = "@#!%!@#";

    public boolean Export(){


        return true;
    }

    public boolean Import(){


        return true;
    }

    private String serializeUser(User user){
        String userString = "";

        return userString;
    }

    private User deserializeUser(String userString){
        User user = new User();

        return user;
    }

    private String createUserFile(List<User> users){
        String userFileInformation = "";

        for(User user : users){
            userFileInformation += serializeUser(user);
            userFileInformation += LINE_DELIMITER;
        }
        //Remove last delimiter
        userFileInformation = userFileInformation.substring(0, userFileInformation.length()-LINE_DELIMITER.length());

        return userFileInformation;
    }

    private List<User> loadUsersFromFile(String userFileInformation){
        List<User> users = new ArrayList<>();

        List<String> userFileInformationLines = Arrays.stream(userFileInformation.split(LINE_DELIMITER)).toList();
        for(String userString : userFileInformationLines){
            users.add(deserializeUser(userString));
        }

        return users;
    }

    private String serializePromoCode(PromoCode promoCode){
        String promoCodeString = "";

        return promoCodeString;
    }

    private PromoCode deserializePromoCode(String promoCodeString){
        PromoCode promoCode = new PromoCode();

        return promoCode;
    }

    private String createPromoCodeFile(List<PromoCode> promoCodes){
        String promoCodeFileInformation = "";

        for(PromoCode promoCode : promoCodes){
            promoCodeFileInformation += serializePromoCode(promoCode);
            promoCodeFileInformation += LINE_DELIMITER;
        }
        //Remove last delimiter
        promoCodeFileInformation = promoCodeFileInformation.substring(0, promoCodeFileInformation.length()-LINE_DELIMITER.length());

        return promoCodeFileInformation;
    }

    private List<PromoCode> loadPromoCodesFromFile(String promoCodeFileInformation){
        List<PromoCode> promoCodes = new ArrayList<>();

        List<String> promoCodeFileInformationLines = Arrays.stream(promoCodeFileInformation.split(LINE_DELIMITER)).toList();
        for(String promoCodeString : promoCodeFileInformationLines){
            promoCodes.add(deserializePromoCode(promoCodeString));
        }

        return promoCodes;
    }


}
