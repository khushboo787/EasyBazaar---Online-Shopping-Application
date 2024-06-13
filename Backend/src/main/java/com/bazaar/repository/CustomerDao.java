package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.Customer;
 
public interface CustomerDao extends JpaRepository<Customer, Integer>{

	public Customer findByEmail(String email);
}
