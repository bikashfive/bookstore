package com.bikash.bookstore.service;

import com.bikash.bookstore.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book createBook(Book book);
    Book getBookById(Long bookId);

    List<Book> getAllBook();
    Book updateBook(Book book);

    void deleteBook(Long bookId);
}
