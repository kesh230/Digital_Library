package com.digital.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.library.entity.Book;

@Repository
public interface  Book_repository extends JpaRepository<Book,UUID> {

    // public Book getBookById(UUID bookid);
    
}
