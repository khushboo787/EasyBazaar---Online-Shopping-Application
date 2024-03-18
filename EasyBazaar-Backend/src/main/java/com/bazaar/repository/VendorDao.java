package com.bazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaar.model.Vendor;

public interface VendorDao extends JpaRepository<Vendor, Integer>{

	public Vendor findByPersonalInfoEmail(String email);
}
