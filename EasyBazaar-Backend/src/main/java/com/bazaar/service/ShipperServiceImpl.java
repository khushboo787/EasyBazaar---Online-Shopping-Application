package com.bazaar.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazaar.enums.PaymentStatus;
import com.bazaar.exception.PaymentException;
import com.bazaar.exception.ShipperException;
import com.bazaar.exception.VendorException;
import com.bazaar.model.Cart;
import com.bazaar.model.OrderClass;
import com.bazaar.model.Payment;
import com.bazaar.model.Shipper;
import com.bazaar.repository.OrderClassDao;
import com.bazaar.repository.PaymentDao;
import com.bazaar.repository.ShipperDao;

@Service
public class ShipperServiceImpl implements ShipperService {

	@Autowired
	private OrderClassDao orderDao;

	@Autowired
	private ShipperDao shipperDao;

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public OrderClass assignOrderToshipper(Shipper shipper, Integer orderId, Integer paymentId, String key)
			throws ShipperException, LoginException, VendorException, PaymentException {
		// TODO Auto-generated method stub

		Optional<OrderClass> orderOpt = orderDao.findById(orderId);
		Optional<Payment> paymentOpt = paymentDao.findById(paymentId);
		
		OrderClass outPut = null;

		Payment payment = paymentOpt.get();
		OrderClass order = orderOpt.get();

		if (payment.getPaymentStatus().equals(PaymentStatus.UNSUCCESSFULLY)) {
			throw new PaymentException(
					"Customer has not given payment till now, sorry vendor, waiting for customer to give payment.");
		}
		
		if (order.getShipper() != null) {
	        throw new ShipperException("For this order, a Shipper is already assigned.");
	    }

		Shipper newShipper = order.getShipper();
		if (newShipper == null) {
			newShipper = shipperDao.save(shipper);
			order.setShipper(newShipper);
		} else {
			newShipper = shipperDao.save(newShipper);
		}

		List<OrderClass> orderList = newShipper.getOrderClass();
		orderList.add(order);
		newShipper.setOrderClass(orderList);

		outPut = orderDao.save(order);
		return outPut;
	}

}
