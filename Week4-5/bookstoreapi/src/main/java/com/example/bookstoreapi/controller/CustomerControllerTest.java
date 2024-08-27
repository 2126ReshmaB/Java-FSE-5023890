package main.java.com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Customer;
import com.example.bookstoreapi.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Test Customer");
        customer.setEmail("test@example.com");
    }

    @Test
    public void testCreateCustomer() throws Exception {
        given(customerService.createCustomer(customer)).willReturn(customer);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Customer"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        given(customerService.getCustomerById(1L)).willReturn(customer);

        mockMvc.perform(get("/customers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Customer"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        given(customerService.updateCustomer(1L, customer)).willReturn(customer);

        mockMvc.perform(put("/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Customer"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
