package com.bazaar.service;


import com.bazaar.exception.CartException;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.PersonalInfoException;
import com.bazaar.exception.ProductException;
import com.bazaar.model.Cart;
import com.bazaar.model.Customer;

public interface CustomerService {
	
	public String createCustomerAccount(Customer customer) throws CustomerException,PersonalInfoException;
	
	public Cart addProductsToCart(Integer productId,Integer customerId,Integer quantity) throws CartException,ProductException,CustomerException;
	
	public Customer updateCustomer(Customer customer,String key)throws CustomerException;
	
	public Cart deleteProductFromCart(Integer productId,Integer customerId,String key) throws CartException,ProductException,CustomerException;
	
	public Cart getCartDetails(Integer customerId) throws CartException,ProductException,CustomerException;
}
