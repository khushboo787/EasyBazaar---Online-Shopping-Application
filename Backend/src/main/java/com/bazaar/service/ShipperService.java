package com.bazaar.service;

import javax.security.auth.login.LoginException;

import com.bazaar.exception.PaymentException;
import com.bazaar.exception.ShipperException;
import com.bazaar.exception.VendorException;
import com.bazaar.model.OrderClass;
import com.bazaar.model.Shipper;

public interface ShipperService {
	
	public OrderClass assignOrderToshipper(Shipper shipper, Integer orderId,Integer paymentId,String key) throws ShipperException,
	LoginException,VendorException,PaymentException;

}
