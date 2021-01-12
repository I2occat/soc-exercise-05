package de.unikassel.soc.platform.repos;

import de.unikassel.soc.platform.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepoIntegration {

    @Autowired
    ProductRepo productRepo;

    @Test
    @Transactional
    void findByPriceBetween() {
        productRepo.saveAll(List.of(
                new Product(UUID.randomUUID(), "Test 1", "Test", 1.0, "Eur"),
                new Product(UUID.randomUUID(), "Test 2", "Test", 2.0, "Eur"),
                new Product(UUID.randomUUID(), "Test 3", "Test", 3.0, "Eur"),
                new Product(UUID.randomUUID(), "Test 4", "Test", 4.0, "Eur")
        ));

        List<Product> products = productRepo.findByPriceBetween(1.5,3.5);
        assertEquals(2, products.size());
    }
    @Test
    @Transactional
    void findByPriceBetweenEmpty() {
        List<Product> products = productRepo.findByPriceBetween(1.5,3.5);
        assertEquals(0, products.size());
    }

}