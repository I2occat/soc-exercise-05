package de.unikassel.soc.platform.web.controller;

import de.unikassel.soc.platform.services.SellerService;
import de.unikassel.soc.platform.web.model.CustomerDto;
import de.unikassel.soc.platform.web.model.SellerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SellerControllerTest {

    @Mock
    SellerService sellerService;

    SellerController sellerController;

    static final String BASE_PATH = "/api/v1/seller";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sellerController = new SellerController(sellerService);
    }

    @Test
    void deleteById() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(sellerController).build();
        UUID uuid = UUID.randomUUID();

        SellerDto sellerDto = new SellerDto(uuid, "Test", null);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_PATH+"/"+uuid)
        ).andExpect(status().isOk()).andReturn();

        verify(sellerService, times(1)).deleteById(uuid);
    }
}