package de.unikassel.soc.platform.web.controller;

import de.unikassel.soc.platform.services.ProductService;
import de.unikassel.soc.platform.web.model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {

    @Mock
    ProductService productService;

    ProductController productController;

    static final String BASE_PATH = "/api/v1/product";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    void getProductsByPriceRange() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        List<ProductDto> productDtoList= new ArrayList<>();
        productDtoList.add(new ProductDto(UUID.randomUUID(), "Test1", "Test", 0.5, "Eur"));
        productDtoList.add(new ProductDto(UUID.randomUUID(), "Test2", "Test", 2.5, "Eur"));
        when(productService.getProductsByPriceBetween(0.0,3.0)).thenReturn(productDtoList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH+"/")
                .param("from", "0.0")
                .param("to", "3.0")
            ).andExpect(status().isOk()).andReturn();

        String resultStr  = result.getResponse().getContentAsString();
        assertTrue(resultStr.contains(productDtoList.get(0).getId().toString()));
        assertTrue(resultStr.contains(productDtoList.get(1).getId().toString()));


    }
}