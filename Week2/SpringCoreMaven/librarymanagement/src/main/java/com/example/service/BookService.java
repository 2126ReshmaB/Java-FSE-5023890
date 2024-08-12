package com.example.service;

// import com.example.repository.BookRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public void addBook(String bookName) {
        System.out.println("Book added: " + bookName);
    }

    public void deleteBook(String bookName) {
        System.out.println("Book deleted: " + bookName);
    }
    // Constructor injection
    // public BookService(BookRepository bookRepository) {
    //     this.bookRepository = bookRepository;
    // }

    // // Setter injection
    // public void setBookRepository(BookRepository bookRepository) {
    //     this.bookRepository = bookRepository;
    // }

    // public void performService() {
    //     System.out.println("BookService is performing an operation...");
    //     bookRepository.saveBook();
    // }

//     @Autowired
//     public BookService(BookRepository bookRepository) {
//         this.bookRepository = bookRepository;
//     }

//     // Example method that uses BookRepository
//     public void performService() {
//         System.out.println("Service started...");
//         bookRepository.someRepositoryMethod();
//         System.out.println("Service completed.");
//     }
 }


