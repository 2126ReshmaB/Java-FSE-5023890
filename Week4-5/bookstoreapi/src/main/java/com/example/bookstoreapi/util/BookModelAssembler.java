package com.example.bookstoreapi.util;

import com.example.bookstoreapi.controller.BookController;
import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.entity.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookModelAssembler {

    public EntityModel<BookDTO> toModel(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setVersion(book.getVersion());

        return EntityModel.of(bookDTO,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
    }
}