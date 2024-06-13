package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.Address;

public interface AddressDao extends JpaRepository<Address, Integer>{

}
