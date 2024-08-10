package com.qa.nal;

/**
 * Hello world!
 *
 */
import com.qa.nal.Service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        BookService bookService = (BookService) context.getBean("bookService");

        // Call the service method to test the dependency injection
        bookService.performService();
    }
}