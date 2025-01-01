package service;

import com.bikash.bookstore.entity.Book;
import com.bikash.bookstore.repository.BookRepository;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    @Mock
    private BookRepository bookRepository;

    @Mock
    private Book book;

    @BeforeEach
    void initEach(){
        MockitoAnnotations.initMocks(this);
        book= new Book("Sita-Ram","Tulsi-Das","rama life details");
    }

    @Test
    void createBookTest() {
        when(bookRepository.save(any())).thenReturn(book);
        Book book1 = bookServiceImpl.createBook(book);
        Assertions.assertNotNull(book1);
        Assertions.assertEquals(book1,book);
    }

    @Test
    void getBookByIdTest(){
        when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
        Book bookById = bookServiceImpl.getBookById(1l);
        Assertions.assertNotNull(bookById);
        Assertions.assertEquals(book,bookById);
    }

    @Test
    void getAllBookTest(){
        when(bookRepository.findAll()).thenReturn(List.of(book));
        List<Book> allBook = bookServiceImpl.getAllBook();
        Assertions.assertNotNull(allBook);
        Assertions.assertEquals(List.of(book),allBook);

    }


    @Test
    void updateBookTest(){
        book.setId(1l);
        getBookByIdTest();
        when(bookRepository.save(Mockito.any())).thenReturn(book);
        Book updateBook = bookServiceImpl.updateBook(book);

        Assertions.assertNotNull(updateBook);
        Assertions.assertEquals(book,updateBook);

    }

    @Test
    void deleteBookTest(){
        book.setId(1l);
        getBookByIdTest();

        Mockito.doNothing().when(bookRepository).deleteById(Mockito.anyLong());
        bookServiceImpl.deleteBook(1l);

    }

}
