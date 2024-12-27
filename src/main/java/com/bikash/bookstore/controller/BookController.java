package com.bikash.bookstore.controller;

import com.bikash.bookstore.entity.Book;
import com.bikash.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
        Book bookDetails = bookService.createBook(book);
        return new ResponseEntity<>(bookDetails, HttpStatus.CREATED);
    }

    @GetMapping("/book/{id}")
    public Book getBookDetails(@PathVariable("id") Long bookId){
        return bookService.getBookById(bookId);
    }
 @GetMapping("/getBooks")
    public List<Book> getAllBookDetails(){
        return bookService.getAllBook();
    }

    @PutMapping("/update/{id}")
    public Book updateBookDetails(@Valid @PathVariable("id") Long bookId,@RequestBody Book book){
        book.setId(bookId);
        return bookService.updateBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookDetails(@PathVariable("id") Long bookId){
        return new ResponseEntity<>("Book was deleted successfully",HttpStatus.OK);
    }
}
