
# EasyBazaar - Online Shopping Application    
EasyBazaar is an online shopping platform designed to connect customers with local vendors, offering a seamless shopping experience for users while empowering vendors to expand their reach in 
 the digital marketplace. This document provides an overview of the project structure, modules, and features.


### Tech Stack
 
- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- HTML
- CSS
- JavaScript  


### Modules
- Customer Module
- Admin Module
- Vendor Module  
- Shipper Module

### Features


      Customer Module : 
      - Allows customers to create accounts and login.
      - Provides search functionality to browse products by category.
      - Supports sorting and filtering of products by price.
      - Enables customers to view individual product pages, add items to the cart, and make purchases.
      - Includes features for tracking order status, viewing order history, and making payments.
      - Future enhancements include the ability to provide feedback.


      Vendor Module : 
      - Provides a dashboard for vendors to manage their accounts and products.
      - Allows vendors to add new products, manage quantities, and oversee order management.
      - Includes features for tracking sales, managing orders, and analyzing sales data.
      - Future enhancements include subscription management and feedback monitoring.

      
      Shipper Module : 
      - Enables shippers to update order statuses and handle refund requests.

      
      Admin Module : 
      - Provides an admin dashboard for managing customers, vendors, and feedback.
      - Allows administrators to monitor company revenue and analyze sales data.
      - Future enhancements include revenue tracking and feedback management.





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


 




                                      Thank you for choosing EasyBazaar for your online shopping needs. Happy shopping! 🛍️
