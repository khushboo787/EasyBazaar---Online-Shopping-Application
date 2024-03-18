# EasyBazaar - Online Shopping Application
EasyBazaar is an online shopping platform designed to connect customers with local vendors, offering a seamless shopping experience for users while empowering vendors to expand their reach in 
 the digital marketplace. This document provides an overview of the project structure, modules, and features.


### Tech Stack
Backend: Java, Spring Framework, Spring Boot, Hibernate, MySQL
Frontend: HTML, CSS, JavaScript  


### Modules
1. Customer Module
2. Admin Module
3. Vendor Module  
4. Shipper Module

### Features

    1. Customer Module

      Allows customers to create accounts and login.
      Provides search functionality to browse products by category.
      Supports sorting and filtering of products by price.
      Enables customers to view individual product pages, add items to the cart, and make purchases.
      Includes features for tracking order status, viewing order history, and making payments.
      Future enhancements include the ability to provide feedback.


   2. Vendor Module
   
      Provides a dashboard for vendors to manage their accounts and products.
      Allows vendors to add new products, manage quantities, and oversee order management.
      Includes features for tracking sales, managing orders, and analyzing sales data.
      Future enhancements include subscription management and feedback monitoring.

  3. Shipper Module
     
     Enables shippers to update order statuses and handle refund requests.


   5. Admin Module
      
    Provides an admin dashboard for managing customers, vendors, and feedback.
    Allows administrators to monitor company revenue and analyze sales data.
    Future enhancements include revenue tracking and feedback management.

### Installation & Run

Before running the API server, you should update the database config inside the application.properties file.

Update the port number, username and password as per your local database config.

    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/dbbazaar;
    
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    
    spring.datasource.username=root
    
    spring.datasource.password=root
### API Root 

https://localhost:8888/

http://localhost:8888/swagger-ui.html


### EasyBazaar API Module Endpoints

##### Customer Module
POST /customer/login:
Description: Log in a customer using their mobile number and password.
GET /customer/products:
Description: Retrieve a list of products available for purchase.
POST /customer/cart/add:
Description: Add a product to the customer's shopping cart.
GET /customer/cart:
Description: View the contents of the customer's shopping cart.
POST /customer/cart/checkout:
Description: Process the checkout for the items in the shopping cart.
GET /customer/orders:
Description: Retrieve a list of orders placed by the customer.
GET /customer/orders/{order_id}:
Description: Retrieve details of a specific order by its ID.
POST /customer/feedback:
Description: Submit feedback or reviews for products or services.
Vendor Module
POST /vendor/login:
Description: Log in a vendor using their credentials.
GET /vendor/products:
Description: Retrieve a list of products managed by the vendor.
POST /vendor/products/add:
Description: Add a new product to the vendor's inventory.
PUT /vendor/products/{product_id}:
Description: Update details of a specific product.
DELETE /vendor/products/{product_id}:
Description: Delete a product from the vendor's inventory.
GET /vendor/orders:
Description: Retrieve a list of orders placed for the vendor's products.
GET /vendor/orders/{order_id}:
Description: Retrieve details of a specific order by its ID.
Admin Module
POST /admin/login:
Description: Log in as an administrator using admin credentials.
GET /admin/customers:
Description: Retrieve a list of all registered customers.
GET /admin/vendors:
Description: Retrieve a list of all registered vendors.
GET /admin/orders:
Description: Retrieve a list of all orders placed on the platform.
PUT /admin/orders/{order_id}/status:
Description: Update the status of a specific order (e.g., processing, shipped, delivered).
GET /admin/revenue:
Description: Retrieve revenue statistics for the platform.
POST /admin/promotions:
Description: Create promotional offers or discounts for products.
  ### Thank you for choosing EasyBazaar for your online shopping needs. Happy shopping! 🛍️
