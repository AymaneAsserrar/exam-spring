package com.bikeshop.config;

import com.bikeshop.entity.Accessory;
import com.bikeshop.entity.Bike;
import com.bikeshop.repository.AccessoryRepository;
import com.bikeshop.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

        @Bean
        CommandLineRunner initDatabase(BikeRepository bikeRepository, AccessoryRepository accessoryRepository) {
                return args -> {
                        log.info("Initializing database with sample bikes...");

                        var bikes = List.of(
                                        Bike.builder()
                                                        .name("Mountain Explorer Pro")
                                                        .category("Mountain")
                                                        .description("High-performance mountain bike for extreme trails")
                                                        .price(new BigDecimal("1299.99"))
                                                        .build(),
                                        Bike.builder()
                                                        .name("Urban Commuter Elite")
                                                        .category("City")
                                                        .description("Comfortable city bike perfect for daily commutes")
                                                        .price(new BigDecimal("799.99"))
                                                        .build(),
                                        Bike.builder()
                                                        .name("Road Racer Carbon")
                                                        .category("Road")
                                                        .description("Lightweight carbon frame road bike for competitive racing")
                                                        .price(new BigDecimal("2499.99"))
                                                        .build(),
                                        Bike.builder()
                                                        .name("Kids Adventure 20")
                                                        .category("Kids")
                                                        .description("Safe and fun bike for children aged 6-10")
                                                        .price(new BigDecimal("299.99"))
                                                        .build(),
                                        Bike.builder()
                                                        .name("Electric City Glide")
                                                        .category("Electric")
                                                        .description("E-bike with 50km range, perfect for eco-friendly travel")
                                                        .price(new BigDecimal("1899.99"))
                                                        .build(),
                                        Bike.builder()
                                                        .name("BMX Freestyle X")
                                                        .category("BMX")
                                                        .description("Durable BMX bike for tricks and skatepark riding")
                                                        .price(new BigDecimal("449.99"))
                                                        .build(),
                                        Bike.builder()
                                                        .name("Touring Adventure Plus")
                                                        .category("Touring")
                                                        .description("Long-distance touring bike with comfortable geometry")
                                                        .price(new BigDecimal("1599.99"))
                                                        .build(),
                                        Bike.builder()
                                                        .name("Gravel Master GT")
                                                        .category("Gravel")
                                                        .description("Versatile gravel bike for mixed terrain adventures")
                                                        .price(new BigDecimal("1799.99"))
                                                        .build());

                        bikeRepository.saveAll(bikes);
                        log.info("Database initialized with {} bikes", bikes.size());

                        log.info("Initializing database with sample accessories...");

                        var accessories = List.of(
                                        Accessory.builder()
                                                        .name("Pro Cycling Helmet")
                                                        .description("Lightweight aerodynamic helmet with MIPS protection")
                                                        .price(new BigDecimal("149.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("LED Front Light")
                                                        .description("Powerful 1000 lumen USB rechargeable front light")
                                                        .price(new BigDecimal("59.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("LED Rear Light")
                                                        .description("Bright rear safety light with multiple modes")
                                                        .price(new BigDecimal("29.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("U-Lock Heavy Duty")
                                                        .description("High-security hardened steel U-lock")
                                                        .price(new BigDecimal("79.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("Bike Pump with Gauge")
                                                        .description("Floor pump with pressure gauge for all valve types")
                                                        .price(new BigDecimal("44.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("Cycling Gloves")
                                                        .description("Padded gel gloves for comfort and grip")
                                                        .price(new BigDecimal("34.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("Water Bottle Cage")
                                                        .description("Lightweight aluminum bottle cage")
                                                        .price(new BigDecimal("14.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("Saddle Bag")
                                                        .description("Waterproof under-seat storage bag")
                                                        .price(new BigDecimal("24.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("Bike Computer")
                                                        .description("Wireless cycling computer with speed, distance, and time")
                                                        .price(new BigDecimal("89.99"))
                                                        .build(),
                                        Accessory.builder()
                                                        .name("Repair Kit")
                                                        .description("Complete tire repair kit with tools and patches")
                                                        .price(new BigDecimal("19.99"))
                                                        .build());

                        accessoryRepository.saveAll(accessories);
                        log.info("Database initialized with {} accessories", accessories.size());
                };
        }
}
