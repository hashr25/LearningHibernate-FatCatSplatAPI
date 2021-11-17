/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.HibernateUnitTests.DTO;

import java.util.Objects;

/**
 *
 * @author rhash
 */
public class GetUserRequest {
    private String userName;
    private String securityToken;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.userName);
        hash = 97 * hash + Objects.hashCode(this.securityToken);
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
        final GetUserRequest other = (GetUserRequest) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.securityToken, other.securityToken)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GetUserRequest{" + "userName=" + userName + ", securityToken=" + securityToken + '}';
    }
    
    
}
