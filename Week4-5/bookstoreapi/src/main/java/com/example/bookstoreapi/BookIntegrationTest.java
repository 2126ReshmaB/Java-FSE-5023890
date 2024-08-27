package main.java.com.example.bookstoreapi;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.service.BookService;
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
public class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testCreateAndGetBook() throws Exception {
        Book book = new Book();
        book.setTitle("Integration Test Book");
        book.setAuthor("Test Author");

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Integration Test Book\", \"author\":\"Test Author\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Integration Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Initial Title");
        book.setAuthor("Initial Author");
        book = bookRepository.save(book);

        mockMvc.perform(put("/books/{id}", book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\", \"author\":\"Updated Author\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/books/{id}", book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book();
        book.setTitle("To be deleted");
        book.setAuthor("Test Author");
        book = bookRepository.save(book);

        mockMvc.perform(delete("/books/{id}", book.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/{id}", book.getId()))
                .andExpect(status().isNotFound());
    }
}
