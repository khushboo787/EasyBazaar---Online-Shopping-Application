package com.bazaar.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazaar.enums.CategoryType;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.ProductException;
import com.bazaar.model.CurrentSession;
import com.bazaar.model.Product;
import com.bazaar.repository.ProductDao;
import com.bazaar.repository.SessionDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	SessionDao sessionDao;

	@Autowired
	ProductDao productDao;

	@Override
	public Product getSingalProduct(String key, Integer productId) throws LoginException, ProductException {
		// TODO Auto-generated method stub

		CurrentSession session = sessionDao.findByUuid(key);

		if (session == null) {
			throw new LoginException("customer Not logged In");
		}

		if (productId == null) {
			throw new ProductException("Product not found with this Id");
		}

		Optional<Product> productOpt = productDao.findById(productId);
		Product product = productOpt.get();

		return product;
	}

	@Override
	public List<Product> viewAllProduct() throws ProductException, LoginException, CustomerException {
		// TODO Auto-generated method stub

		List<Product> products = productDao.findAll();

		if (products.isEmpty()) {
			throw new ProductException("Product is currently not available ");
		}
		return products;
	}

	@Override
	public List<Product> getProductByCategory(CategoryType category, String key)
			throws ProductException, LoginException, CustomerException {
		// TODO Auto-generated method stub
		
		CurrentSession session = sessionDao.findByUuid(key);
		
		if(session == null ) {
			throw new LoginException("Customer not logged in");
		}
		
		List<Product> products = productDao.findByCategory(category);
		return products;
	}

}
