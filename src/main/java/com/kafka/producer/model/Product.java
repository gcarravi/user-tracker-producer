package com.kafka.producer.model;

import com.kafka.producer.enums.Color;
import com.kafka.producer.enums.DesignType;
import com.kafka.producer.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
    private Color color;
    private ProductType productType;
    private DesignType designType;
}
