package com.packt.cardatabase;

import java.util.Arrays;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(
            CardatabaseApplication.class
    );

    private final CarRepository repository;
    private final OwnerRepository orepository;
    private final AppUserRepository urepository;

    public CardatabaseApplication(CarRepository repository, OwnerRepository orepository, AppUserRepository urepository) {
        this.repository = repository;
        this.orepository = orepository;
        this.urepository = urepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
        logger.info("Application started");
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner("John", "Johnson");
        Owner owner2 = new Owner("Mary", "Robinson");
        orepository.saveAll(Arrays.asList(owner1, owner2));

        repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
        repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner2));
        repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2));

        for (Car car : repository.findAll()) {
            logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
        }

        // id = 'user', password = 'user'
        urepository.save(new AppUser("user",
                "$2y$10$f5QmHrghX7uluaH82Aa8qOiVb6EwkxOMYTWvqREwnsktqhudrdj/6", "USER"));
        // id = 'admin', password = 'admin'
        urepository.save(new AppUser("admin",
                "$2y$10$uoZ7EENF/giyCF/B9/0Z/.rLf7/i39SdNBhsUMwGYgpZETHtb01g6", "ADMIN"));
    }
}
