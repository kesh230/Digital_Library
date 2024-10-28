package com.digital.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digital.library.LibraryApplication;

@SpringBootTest(classes=LibraryApplication.class)
public class Book_repositoryTest {

    public Book_repositoryTest(com.digital.library.repository.Book_repository book_repository) {
        this.book_repository = book_repository;
    }
    
    @Autowired
    private final Book_repository book_repository;


    

}
