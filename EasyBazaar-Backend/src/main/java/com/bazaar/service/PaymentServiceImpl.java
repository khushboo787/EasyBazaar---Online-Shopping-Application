package com.bazaar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazaar.dto.ProductDTO;
import com.bazaar.enums.PaymentMethod;
import com.bazaar.enums.PaymentStatus;
import com.bazaar.exception.CustomerException;
import com.bazaar.exception.OrderClassException;
import com.bazaar.exception.PaymentException;
import com.bazaar.model.Cart;
import com.bazaar.model.OrderClass;
import com.bazaar.model.Payment;
import com.bazaar.model.Product;
import com.bazaar.repository.CartDao;
import com.bazaar.repository.OrderClassDao;
import com.bazaar.repository.PaymentDao;
import com.bazaar.repository.ProductDao;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private OrderClassDao orderDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private CartDao cartDao; 
	
	@Autowired
	private ProductDao productDao;
	
	@Transactional
	@Override
	public Payment giveYourPayment(Payment payment, Integer orderId)
			throws PaymentException, CustomerException, OrderClassException {
		// TODO Auto-generated method stub
		
		Payment outPut = null;
		Optional<OrderClass> orderOpt = orderDao.findById(orderId);
		OrderClass order = orderOpt.get();
		
		if(order == null ) {
			throw new OrderClassException("Order not found ");
		}
		
		// I am taking this cart so that after payment I can empty customer cart -> there should not any product;
		Cart cart= order.getCustomer().getCart();
		
		
//		I need to update my product table also 
		List<ProductDTO> products = cart.getProducts();

		// Iterate over the list of products and update each product in the Product table
		for (ProductDTO product : products) {
		    int productId = product.getProductId();
		    int quantity = product.getQuantity();
		    productDao.updateProductQuantityById(productId, quantity);
		}

		
		payment.setPaymentCreatedAtDate(LocalDate.now());
		payment.setPaymentAmount(order.getTotalAmount());
		payment.setOrders(order);
		
//		checking if customer is giving payment trough debit cart  and credit cart payment status will be successfully
		if(payment.getMethod().equals(PaymentMethod.CASH_ON_DELIVERY)) {
			payment.setPaymentStatus(PaymentStatus.UNSUCCESSFULLY);
			
		}else {
			payment.setPaymentStatus(PaymentStatus.SUCCESSFULLY);
		}
//		orderDao.save(order);
		outPut  = paymentDao.save(payment);
		cartDao.delete(cart);
		
		return outPut;
	}

	@Override
	public Payment cancelPayment(Integer customerId, Integer paymentId) throws PaymentException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
