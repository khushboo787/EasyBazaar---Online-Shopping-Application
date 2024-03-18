package com.bazaar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazaar.dto.OrderDTO;
import com.bazaar.dto.ProductDTO;
import com.bazaar.enums.OrderStatus;
import com.bazaar.enums.UserType;
import com.bazaar.exception.CartException;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.OrderClassException;
import com.bazaar.exception.ProductException;
import com.bazaar.model.Cart;
import com.bazaar.model.CurrentSession;
import com.bazaar.model.Customer;
import com.bazaar.model.OrderClass;
import com.bazaar.repository.CustomerDao;
import com.bazaar.repository.OrderClassDao;
import com.bazaar.repository.ProductDao;
import com.bazaar.repository.SessionDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private OrderClassDao orderDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private ProductDao productDao;

	@Override
	public OrderClass giveYourOrder(OrderClass order, Integer customerId)
			throws LoginException, CustomerException, CartException, ProductException, OrderClassException {
		// TODO Auto-generated method stub

		Optional<Customer> customerOpt = customerDao.findById(customerId);
		Customer customer = customerOpt.get();

		if(customer.getCart().getProducts().isEmpty()) {
			throw new CartException("Your cart is empty please add product in your cart");
		}
		order.setDeliveryDate(LocalDate.now().plusDays(1));
		order.setOrderCreatedAt(LocalDate.now());
		order.setOrderStatus(OrderStatus.NOTSHIPPED);
		order.setTotalAmount(customer.getCart().getTotalPrice());
		customer.getOrderClass().add(order);
		order.setCustomer(customer);
		return orderDao.save(order);

	}

	@Override
	public OrderClass cancelYourOrder(Integer orderId, String key)
			throws LoginException, CustomerException, CartException, ProductException, OrderClassException {
		// TODO Auto-generated method stub
		
		CurrentSession user = sessionDao.findByUuid(key);
		
		if(user == null ) {
			throw new LoginException("please enter right session key");
		}
		
		if(user.equals(UserType.ADMIN) && user.equals(UserType.VENDOR)) {
			throw new LoginException("You are not authorized to cancel order");
		}
		
		Optional<OrderClass> orderOpt = orderDao.findById(orderId);
		if(orderOpt.isEmpty()) {
			throw new OrderClassException("Order not found with this order id "+orderId);
		}
		
        OrderClass orders = orderOpt.get();
        Customer customer = orders.getCustomer();
		
		customer.getOrderClass().remove(orders);
		customerDao.save(customer);
		orderDao.delete(orders);
		
		return orders;
	}

}
