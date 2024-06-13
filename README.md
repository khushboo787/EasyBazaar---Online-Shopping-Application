
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


### ER Diagram

![ER-Diagram_EasyBazaar](https://github.com/khushboo787/EasyBazaar-Online-Shopping-Application/assets/115460837/4393d697-d721-45cf-b63c-0b950d125bc3)


### Swagger UI
![s1](https://github.com/khushboo787/EasyBazaar-Online-Shopping-Application/assets/115460837/1e86e3e4-f9c8-43f1-8fc6-7f7dc0c4aebe)

![s11](https://github.com/khushboo787/EasyBazaar-Online-Shopping-Application/assets/115460837/a8c88be9-7500-4a5f-8057-0adde237faa5)

![s111](https://github.com/khushboo787/EasyBazaar-Online-Shopping-Application/assets/115460837/550e779f-4c95-4e3f-b3d9-3d1e05845de1)



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
