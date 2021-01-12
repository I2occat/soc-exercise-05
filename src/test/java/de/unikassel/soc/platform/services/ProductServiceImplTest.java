package de.unikassel.soc.platform.services;

import de.unikassel.soc.platform.domain.Product;
import de.unikassel.soc.platform.repos.ProductRepo;
import de.unikassel.soc.platform.web.mappers.CustomerMapper;
import de.unikassel.soc.platform.web.mappers.ProductMapper;
import de.unikassel.soc.platform.web.mappers.ProductMapperImpl;
import de.unikassel.soc.platform.web.model.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    static ProductMapper mapper;
    ProductServiceImpl productService;

    @Mock
    ProductRepo repo;

    @BeforeAll
    static void beforeAll() { mapper = new ProductMapperImpl(); }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(repo);
    }

    @Test
    void getProductsByPriceBetween() {
        UUID uuid1 = UUID.randomUUID(),
            uuid2 = UUID.randomUUID(),
            uuid3 = UUID.randomUUID();
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(uuid1, "Test1", "Desc1", 0.5, "Euro"));
        productList.add(new Product(uuid2, "Test2", "Desc2", 2.5, "Euro"));
        productList.add(new Product(uuid3, "Test3", "Desc3", 5.5, "Euro"));

        when(repo.findByPriceBetween(1.0, 6.0)).thenReturn(productList.subList(1,3));
        List<ProductDto> productsByPriceBetween = productService.getProductsByPriceBetween(1.0, 6.0);
        assertEquals(2, productsByPriceBetween.size());
        assertEquals(uuid2, productsByPriceBetween.get(0).getId());
        assertEquals(uuid3, productsByPriceBetween.get(1).getId());
        verify(repo, times(1)).findByPriceBetween(1.0, 6.0);


    }
}