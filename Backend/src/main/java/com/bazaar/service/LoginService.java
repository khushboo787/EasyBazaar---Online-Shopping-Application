package com.bazaar.service;

import javax.security.auth.login.LoginException;

import com.bazaar.model.Login;
import com.bazaar.model.LoginResponse;

public interface LoginService {

	public LoginResponse loginUser(Login credential) throws LoginException;
	public String logoutUser(String key) throws LoginException;
}
