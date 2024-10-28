package com.digital.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digital.library.LibraryApplication;
import com.digital.library.entity.Book;
import com.digital.library.entity.Book.Category;
import com.digital.library.repository.Book_repository;

@SpringBootTest(classes = LibraryApplication.class)
public class BookServiceTest {
    
    private final BookService bookService;
    
    @Autowired
    public BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }
    @MockBean
    private Book_repository book_repository;

     static Book book=Book.builder()
                          .id(UUID.randomUUID())
                          .name("test")
                          .author("test_auth")
                          .category(Category.FICTION)
                          .isbn("test").price(90.6)
                          .description("description")
                          .build();
    

    @Test
    void getallbooks(){
        List<Book> booklist=List.of(book,book.withPrice(5000.90),book.withPrice(9000.00));
        Mockito.when(this.book_repository.findAll()).thenReturn(booklist);
        List<Book> fetchbook=bookService.getBook();
        Assertions.assertEquals(booklist.size(),fetchbook.size());
}
    @Test
    void getbookbyid(){
        Mockito.when(this.book_repository.findById(book.getId())).thenReturn(Optional.of(book));
        Book book1=bookService.getbybookid(book.getId());
        Assertions.assertEquals(book,book1);
 }
    @Test
    void getnullwhenBookIsNotPresent(){
      UUID id=UUID.randomUUID();
      Mockito.when(this.book_repository.findById(id)).thenReturn(Optional.empty());
      Book book1=this.bookService.getbybookid(id);
      Assertions.assertNull(book1);
    }

}
