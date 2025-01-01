package com.bikash.bookstore.controller;

import com.bikash.bookstore.entity.Book;
import com.bikash.bookstore.repository.BookRepository;
import com.bikash.bookstore.service.BookService;
import com.bikash.bookstore.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
    private BookServiceImpl bookServiceImpl;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private Book book;
    @Mock
    private ResponseEntity<Book> responseEntity;
    @Mock
    private ResponseEntity<String> responseEntityString;

    @BeforeEach
    void initEach(){
        MockitoAnnotations.initMocks(this);
        book= new Book("Sita-Ram","Tulsi-Das","rama life details");
    }

    @Test
    void createBookTest(){
        when(bookService.createBook(Mockito.any())).thenReturn(book);

        ResponseEntity<Book> bookResponseEntity = bookController.createBook(book);

        Assertions.assertNotNull(bookResponseEntity.getBody());
        Assertions.assertEquals(book,bookResponseEntity.getBody());

    }
    @Test
    void getBookDetailsTest(){
        when(bookService.getBookById(Mockito.anyLong())).thenReturn(book);
        Book bookDetails = bookController.getBookDetails(1l);

        Assertions.assertNotNull(bookDetails);
        Assertions.assertEquals(book,bookDetails);


    }

    @Test
    void getAllBookDetailsTest(){
        when(bookService.getAllBook()).thenReturn(List.of(book));
        List<Book> allBookDetails = bookController.getAllBookDetails();

        Assertions.assertNotNull(allBookDetails);
        Assertions.assertEquals(List.of(book),allBookDetails);

    }

    @Test
    void updateBookDetailsTest(){
        getBookDetailsTest();
        when(bookService.updateBook(Mockito.any())).thenReturn(book);
        Book book1 = bookController.updateBookDetails(1l, book);

        Assertions.assertNotNull(book1);
        Assertions.assertEquals(book,book1);

    }

    @Test
    void deleteBookDetailsTest(){

       Mockito.doNothing().when(bookService).deleteBook(Mockito.anyLong());
        ResponseEntity<String> stringResponseEntity = bookController.deleteBookDetails(1l);
        Assertions.assertNotNull(stringResponseEntity);
        Assertions.assertEquals("Book was deleted successfully",stringResponseEntity.getBody());

    }

}
