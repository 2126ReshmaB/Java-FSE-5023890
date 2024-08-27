package com.example.bookstoreapi.service;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDTO(book);
    }

    public BookDTO createBook(@Valid BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public BookDTO updateBook(Long id, @Valid BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
