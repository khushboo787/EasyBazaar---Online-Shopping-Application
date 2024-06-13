package com.bazaar.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.bazaar.exception.AdminException;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.VendorException;
import com.bazaar.model.Customer;
import com.bazaar.model.Vendor;

public interface AdminService {
	
	public List<Customer> viewAllCustomer(String key) throws CustomerException,LoginException,AdminException;
	
	public List<Vendor> viewAllVendor(String key) throws VendorException,LoginException,AdminException;
	
}
