package com.bazaar.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazaar.enums.UserType;
import com.bazaar.model.CurrentSession;
import com.bazaar.model.Customer;
import com.bazaar.model.Login;
import com.bazaar.model.LoginResponse;
import com.bazaar.model.Vendor;
import com.bazaar.repository.CustomerDao;
import com.bazaar.repository.PersonalInfoDao;
import com.bazaar.repository.SessionDao;
import com.bazaar.repository.VendorDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	PersonalInfoDao personalDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	VendorDao vendorDao;
	
	@Autowired
    SessionDao sessionDao;
	
	@Override
	public LoginResponse loginUser(Login credential) throws LoginException {
		// TODO Auto-generated method stub
		
		CurrentSession currentSession;
		
		if( credential.getUser_type() != null && UserType.CUSTOMER.equals(credential.getUser_type())) {
			
			Customer existingCustomer = customerDao.findByEmail(credential.getEmail());
			
			if(existingCustomer == null ) {
				
				throw new LoginException("Please Enter a valid CREDENTIALS");
				
			}else {
				
				Integer userId = existingCustomer.getCustomerId();
				Optional<CurrentSession> validCustomerSessionOpt = sessionDao.findById(existingCustomer.getCustomerId());
				if(validCustomerSessionOpt.isPresent()) {
					
					throw new LoginException("Customer already Logged In with this Email");
				}
				
				if(existingCustomer.getPassword().equals(credential.getPassword())) {
					
					String key = RandomString.make(6);
					currentSession = new CurrentSession(existingCustomer.getCustomerId(),key,LocalDateTime.now(),credential.getUser_type());
					sessionDao.save(currentSession);
					return new LoginResponse("Customer Logged in Successfully Welcome to shopeasy ",key,userId);
					
				}else {
					throw new LoginException("Wrong Password");
				}
				
			}
			
			
		}else if(credential.getUser_type().equals(UserType.VENDOR)) {
			
			
			Vendor existingVendor = vendorDao.findByPersonalInfoEmail(credential.getEmail());
			if(existingVendor == null ) {
				
				throw new LoginException("Please Enter a valid CREDENTIALS");
				
			}else {
				
				Integer userId = existingVendor.getVendorId();
				Optional<CurrentSession> validVendorSessionOpt = sessionDao.findById(existingVendor.getVendorId());
				if(validVendorSessionOpt.isPresent()) {
					
					throw new LoginException("Vendor already Logged In with this Email");
				}
				
				if(existingVendor.getPersonalInfo().getPassword().equals(credential.getPassword())) {
					
					String key = RandomString.make(6);
					currentSession = new CurrentSession(existingVendor.getVendorId(),key,LocalDateTime.now(),credential.getUser_type());
					sessionDao.save(currentSession);
					return new LoginResponse("Vendor Logged in Successfully Welcome to shopeasy ",key,userId);
					
				}else {
					throw new LoginException("Wrong Password");
				}
				
			}
			
			
		}else {
			
			
			if(!credential.getEmail().equals("admin@gmail.com") ) {
				
				throw new LoginException("Please Enter a valid CREDENTIALS");
				
			}else {
				
				
				Optional<CurrentSession> validAdminSessionOpt = sessionDao.findById(0);
				if(validAdminSessionOpt.isPresent()) {
					
					throw new LoginException("Admin already Logged In with this Email");
				}
				
				if("admin".equals(credential.getPassword())) {
					
					String key = RandomString.make(6);
					currentSession = new CurrentSession(0,key,LocalDateTime.now(),credential.getUser_type());
					sessionDao.save(currentSession);
					return new LoginResponse("Hello Admin Welcome again to shopeasy ",key,0);
					
				}else {
					throw new LoginException("Wrong Password");
				}
				
			}
		}

	}

	@Override
	public String logoutUser(String key) throws LoginException {
		// TODO Auto-generated method stub
		
		CurrentSession validSession = sessionDao.findByUuid(key);
		
		if( validSession == null ) {
			throw new LoginException("Please Provide Valid Key");
		}
		
		sessionDao.delete(validSession);
		return "Logout Successfully";
	}

}
