/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.DAO;

import com.rhash.FatCatSplatHibernateAPI.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author capta
 */
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
