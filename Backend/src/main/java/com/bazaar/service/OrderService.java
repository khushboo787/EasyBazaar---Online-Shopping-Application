package com.bazaar.service;


import javax.security.auth.login.LoginException;

import com.bazaar.exception.CartException;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.OrderClassException;
import com.bazaar.exception.ProductException;
import com.bazaar.model.OrderClass;

public interface OrderService {
	
	public OrderClass giveYourOrder(OrderClass order,Integer customerId)throws LoginException, CustomerException, CartException, ProductException,OrderClassException;

	public OrderClass cancelYourOrder(Integer orderId,String key)throws LoginException, CustomerException, CartException, ProductException,OrderClassException;
}
