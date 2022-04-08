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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    
    private String deviceType;
    
    private String deviceModel;
    
    ///Change this back to userPromoCodes
    @ManyToMany(fetch = FetchType.EAGER, 
            cascade = CascadeType.ALL)
    private List<PromoCode> userPromoCodes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public List<PromoCode> getUserPromoCodes() {
        return userPromoCodes;
    }

    public void setUserPromoCodes(List<PromoCode> userPromoCodes) {
        this.userPromoCodes = userPromoCodes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.userName);
        hash = 79 * hash + Objects.hashCode(this.deviceType);
        hash = 79 * hash + Objects.hashCode(this.deviceModel);
        hash = 79 * hash + Objects.hashCode(this.userPromoCodes);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.deviceType, other.deviceType)) {
            return false;
        }
        if (!Objects.equals(this.deviceModel, other.deviceModel)) {
            return false;
        }
        if (!Objects.equals(new ArrayList(this.userPromoCodes), new ArrayList(other.userPromoCodes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", deviceType=" + deviceType + ", deviceModel=" + deviceModel + ", userPromoCodes=" + userPromoCodes + '}';
    }
    
    
    
    
}
