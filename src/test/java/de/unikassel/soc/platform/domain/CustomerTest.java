package de.unikassel.soc.platform.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void getName() {
        customer.setName("Test");
        assertEquals("Test", customer.getName());
    }

    @Test
    void getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        customer.setProducts(products);
        assertSame(products, customer.getProducts());
    }

}