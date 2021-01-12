package de.unikassel.soc.platform.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unikassel.soc.platform.services.CustomerService;
import de.unikassel.soc.platform.web.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    CustomerController customerController;

    static final String BASE_PATH = "/api/v1/customer";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    void getCustomer() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        UUID uuid = UUID.randomUUID();
        CustomerDto customerDto = new CustomerDto(uuid, "Test", null);
        when(customerService.getCustomerById(uuid)).thenReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/" + uuid))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerNotFound() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new MvcExceptionHandler())
                .build();
        UUID uuid = UUID.randomUUID();
        when(customerService.getCustomerById(uuid)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + uuid))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCustomersByName() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        String name = "Test 1";
        List<CustomerDto> customerDtos = new ArrayList<>();
        CustomerDto customerDto1 = new CustomerDto(UUID.randomUUID(), name, null);
        customerDtos.add(customerDto1);

        when(customerService.getCustomersByName(name)).thenReturn(customerDtos);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH+"/").param("name", name))
                .andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        CustomerDto customerDto = new CustomerDto(UUID.randomUUID(), "Test", null);
        when(customerService.saveNewCustomer(customerDto)).thenReturn(customerDto);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/customer")
                        .content(asJsonString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}