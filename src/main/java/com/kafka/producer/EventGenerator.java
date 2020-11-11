package com.kafka.producer;

import com.github.javafaker.Faker;
import com.kafka.producer.enums.Color;
import com.kafka.producer.enums.DesignType;
import com.kafka.producer.enums.ProductType;
import com.kafka.producer.enums.UserId;
import com.kafka.producer.model.Event;
import com.kafka.producer.model.Product;
import com.kafka.producer.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class EventGenerator {
    private Faker faker = new Faker();

    public Event generateEvent(){
        return Event.builder()
                .user(generateUser())
                .product(generateProduct())
                .build();
    }

    private User generateUser(){
        Random random = new Random();
        long key = Math.abs(random.nextLong());

        return User.builder()
                .userId(key)
                .userName(faker.name().name())
                .dateOfBirth(faker.date().birthday())
                .build();
    }

    private Product generateProduct(){
        return Product.builder()
                .color(faker.options().option(Color.class))
                .designType(faker.options().option(DesignType.class))
                .productType(faker.options().option(ProductType.class))
                .build();
    }
}
