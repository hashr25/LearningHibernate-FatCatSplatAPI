/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.DTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author capta
 */
@Entity
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String promoCode;
    
    private String description;
    
    private String currencyType;
    
    private int currencyGiven;
    
//    @ManyToMany(mappedBy = "userPromoCodes",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//    private List<User> users = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public int getCurrencyGiven() {
        return currencyGiven;
    }

    public void setCurrencyGiven(int currencyGiven) {
        this.currencyGiven = currencyGiven;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.promoCode);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.currencyType);
        hash = 29 * hash + this.currencyGiven;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PromoCode other = (PromoCode) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.currencyGiven != other.currencyGiven) {
            return false;
        }
        if (!Objects.equals(this.promoCode, other.promoCode)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.currencyType, other.currencyType)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "PromoCode{" + "id=" + id + ", promoCode=" + promoCode + ", description=" + description + ", currencyType=" + currencyType + ", currencyGiven=" + currencyGiven + '}';
    }
    
    
}
