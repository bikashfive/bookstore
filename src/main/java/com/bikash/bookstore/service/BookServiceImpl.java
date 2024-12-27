package com.bikash.bookstore.service;


import com.bikash.bookstore.entity.Book;
import com.bikash.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long bookId) {
        return  bookRepository.findById(bookId).get();

    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        Book exitingBook = getBookById(book.getId());
        exitingBook.setTitle(book.getTitle());
        exitingBook.setAuthor(book.getAuthor());
        exitingBook.setDescription(book.getDescription());
        Book updatedBookDetails = bookRepository.save(exitingBook);
        return updatedBookDetails;
    }

    @Override
    public void deleteBook(Long bookId) {
        getBookById(bookId);
        bookRepository.deleteById(bookId);

    }
}
