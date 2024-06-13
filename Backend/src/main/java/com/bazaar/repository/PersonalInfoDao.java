package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.Customer;
import com.bazaar.model.PersonalInfo;
import com.bazaar.model.Vendor;

public interface PersonalInfoDao extends JpaRepository<PersonalInfo, Integer>{

	public Customer findByEmail(String email);
	public Vendor findVendorByEmail(String email);
}
