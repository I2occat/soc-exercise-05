package de.unikassel.soc.platform.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    Seller seller;

    @BeforeEach
    void setUp() { seller = new Seller(); }

    @Test
    void getName() {
        String name = "Test";
        seller.setName(name);
        assertEquals(name, seller.getName());
    }

    @Test
    void getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        seller.setProducts(products);
        assertSame(products, seller.getProducts());
    }
}