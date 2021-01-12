package de.unikassel.soc.platform.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product product;

    @BeforeEach
    void setUp() { product = new Product(); }

    @Test
    void getPrice() {
        Double price = (Double) 1.2;
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }

    @Test
    void getDescription() {
        String description = "Test";
        product.setDescription(description);
        assertEquals(description, product.getDescription());
    }
}