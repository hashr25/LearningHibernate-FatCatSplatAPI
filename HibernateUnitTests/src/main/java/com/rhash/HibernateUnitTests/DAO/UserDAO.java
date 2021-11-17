/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.HibernateUnitTests.DAO;

import com.rhash.HibernateUnitTests.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author capta
 */
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
