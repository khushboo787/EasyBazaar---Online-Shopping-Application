package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{

}
