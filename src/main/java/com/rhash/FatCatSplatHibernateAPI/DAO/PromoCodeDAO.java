/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhash.FatCatSplatHibernateAPI.DAO;

import com.rhash.FatCatSplatHibernateAPI.DTO.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author capta
 */
@Repository
public interface PromoCodeDAO extends JpaRepository<PromoCode, Integer> {
    PromoCode findByPromoCode(String promoCode);
}
