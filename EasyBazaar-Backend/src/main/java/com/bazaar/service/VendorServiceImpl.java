package com.bazaar.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bazaar.enums.CategoryType;
import com.bazaar.enums.UserType;
import com.bazaar.exception.PersonalInfoException;
import com.bazaar.exception.ProductException;
import com.bazaar.exception.VendorException;
import com.bazaar.model.CurrentSession;
import com.bazaar.model.PersonalInfo;
import com.bazaar.model.Product;
import com.bazaar.model.Vendor;
import com.bazaar.repository.PersonalInfoDao;
import com.bazaar.repository.ProductDao;
import com.bazaar.repository.SessionDao;
import com.bazaar.repository.VendorDao;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorDao vendorDao;

	@Autowired
	PersonalInfoDao personalInfoDao;

	@Autowired
	SessionDao sessionDao;

	@Autowired
	ProductDao productDao;

	@Override
	public String createVendorAccount(Vendor vendor) throws VendorException, PersonalInfoException, IOException {
		// TODO Auto-generated method stub

		String outPut = "Customer Account is Not created";

		Vendor ven = vendorDao.save(vendor);
		if (ven != null) {

			PersonalInfo personalInfo = ven.getPersonalInfo();
			personalInfo.setVendor(ven);
			personalInfoDao.save(personalInfo);
			outPut = "Vendor Account is created Successfully";

		} else {
			throw new PersonalInfoException("Error while creating vendor account: ");
		}

		return outPut;
	}

	@Override
	public String addProduct(Product product, String key) throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub

		String outPut = "Product is Not added";
		CurrentSession session = sessionDao.findByUuid(key);

		if (session == null) {
			throw new LoginException("Vendor Not logged in ");

		} else {

			if (session.getUser_type().equals(UserType.CUSTOMER) || session.getUser_type().equals(UserType.ADMIN)) {
				throw new LoginException("you are not authorized");

			} else if (product.getCategory() == null || !product.getCategory().equals(CategoryType.MENS)
					&& !product.getCategory().equals(CategoryType.KIDS)
					&& !product.getCategory().equals(CategoryType.WOMANS)
					&& !product.getCategory().equals(CategoryType.GROCERY)
					&& !product.getCategory().equals(CategoryType.ELECTRONICS)) {

				throw new ProductException(
						"Sorry Vendor currently we are having only four type of category mens, womans, kids, electronics");

			}

			Optional<Vendor> vendorOpt = vendorDao.findById(session.getId());
			Vendor vendor = vendorOpt.get();

			double discountAmount = product.getMarketPrice() * (product.getDiscount() / 100.0);
			double finalPrice = product.getMarketPrice() - discountAmount;
			
			// Create a DecimalFormat instance with the desired format pattern
			DecimalFormat decimalFormat = new DecimalFormat("#.00");

			// Format the finalPrice using the DecimalFormat instance
			String formattedPrice = decimalFormat.format(finalPrice);

			// Convert the formatted price back to a double
			double afterDiscountPrice = Double.parseDouble(formattedPrice);

			product.setAfterDiscountPrice(afterDiscountPrice);
			
			product.getVendors().add(vendor);
			vendor.getProducts().add(product);
			productDao.save(product);
//			vendorDao.save(vendor);
			outPut = "Your product Listed Successufully";

		}
		return outPut;
	}

	@Override
	public String updateProduct(Product product, String key) throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub

		String outPut = "Product is Not added";
		CurrentSession session = sessionDao.findByUuid(key);
		if (session == null) {
			throw new LoginException("Vendor Not logged in ");

		} else {

			if (session.getUser_type().equals(UserType.CUSTOMER) || session.getUser_type().equals(UserType.ADMIN)) {
				throw new LoginException("you are not authorized");

			} else {

				Optional<Product> pro = productDao.findById(product.getProductId());

				if (pro.isPresent()) {
					productDao.save(product);
					outPut = "Product is updated successfully";

				} else {
					throw new ProductException("Product not found with this Id" + product.getProductId());
				}

			}
		}
		return outPut;
	}

	@Override
	public List<Product> viewAllProductByVendorId(Integer vendorId) throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub

		Optional<Vendor> vendorOpt = vendorDao.findById(vendorId);
		Vendor vendor = vendorOpt.get();
		
		List<Product> products = vendor.getProducts();

		if (products.isEmpty()) {
			throw new ProductException("Product is currently not available ");
		}
		return products;

	}

	@Override
	public String removeProduct(Integer productId, String key)
			throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub

		String outPut = "Product is Not deleted";
		CurrentSession session = sessionDao.findByUuid(key);
		if (session == null) {
			throw new LoginException("Vendor Not logged in ");

		} else {

			if (session.getUser_type().equals(UserType.CUSTOMER) || session.getUser_type().equals(UserType.ADMIN)) {
				throw new LoginException("you are not authorized");

			} else {

				Optional<Product> pro = productDao.findById(productId);

				if (pro.isPresent()) {
					productDao.delete(pro.get());
					outPut = "Product is deleted successfully";

				} else {
					throw new ProductException("Product not found with this Id" + productId);
				}

			}
		}
		return outPut;
	}

	@Override
	public Product viewProductById(Integer productId) throws ProductException, LoginException, VendorException {
		// TODO Auto-generated method stub

		return productDao.findById(productId)
				.orElseThrow(() -> new ProductException("Product not found with this Id" + productId));
	}

	@Override
	public Vendor getSingalVendor(String key) throws LoginException {
		// TODO Auto-generated method stub

		CurrentSession session = sessionDao.findByUuid(key);

		if (session == null) {
			throw new LoginException("Vendor Not logged In");
		}
		Optional<Vendor> venOpt = vendorDao.findById(session.getId());
		Vendor vendor = venOpt.get();

		return vendor;
	}

}
