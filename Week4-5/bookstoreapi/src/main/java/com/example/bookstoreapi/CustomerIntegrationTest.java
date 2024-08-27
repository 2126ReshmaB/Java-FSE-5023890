package main.java.com.example.bookstoreapi;

import com.example.bookstoreapi.entity.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import com.example.bookstoreapi.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testCreateAndGetCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Integration Test Customer");
        customer.setEmail("test@example.com");

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Integration Test Customer\", \"email\":\"test@example.com\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test Customer"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Initial Name");
        customer.setEmail("initial@example.com");
        customer = customerRepository.save(customer);

        mockMvc.perform(put("/customers/{id}", customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Name\", \"email\":\"updated@example.com\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("To be deleted");
        customer.setEmail("test@example.com");
        customer = customerRepository.save(customer);

        mockMvc.perform(delete("/customers/{id}", customer.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andExpect(status().isNotFound());
    }
}
