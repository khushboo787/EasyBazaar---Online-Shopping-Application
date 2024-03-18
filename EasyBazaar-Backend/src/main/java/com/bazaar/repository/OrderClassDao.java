package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.OrderClass;

public interface OrderClassDao extends JpaRepository<OrderClass, Integer>{

}
