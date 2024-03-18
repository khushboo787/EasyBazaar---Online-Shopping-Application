package com.bazaar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.bazaar.model")
public class EasyBazaarApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyBazaarApplication.class, args);
    }
}
