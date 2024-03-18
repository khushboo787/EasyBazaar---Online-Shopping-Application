package com.bazaar.service;

import com.bazaar.exception.CustomerException;
import com.bazaar.exception.OrderClassException;
import com.bazaar.exception.PaymentException;
import com.bazaar.model.Payment;

public interface PaymentService {


	public Payment giveYourPayment(Payment payment,Integer orderId) throws PaymentException,CustomerException,OrderClassException;
	
	public Payment cancelPayment(Integer customerId,Integer paymentId) throws PaymentException,CustomerException;
}
