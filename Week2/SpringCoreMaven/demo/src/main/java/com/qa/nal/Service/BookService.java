package com.qa.nal.Service;

import com.qa.nal.Repository.BookRepository;

public class BookService {

private BookRepository bookRepository;

public void setBookRepository(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}
public void performService() {
    bookRepository.doSomething(); 
}
}

