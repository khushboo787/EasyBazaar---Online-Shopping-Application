package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer>{

}
