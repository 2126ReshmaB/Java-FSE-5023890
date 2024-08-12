package com.example.service;

import com.example.repository.BookRepository;

public class BookService {
  private BookRepository bookRepository;

    // Setter method for dependency injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Example method that uses BookRepository
    public void performService() {
        System.out.println("Service started...");
        bookRepository.someRepositoryMethod();
        System.out.println("Service completed.");
    }
}
