package com.bikeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bikeshop.config.SecurityProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class BikeShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeShopApplication.class, args);
    }
}
