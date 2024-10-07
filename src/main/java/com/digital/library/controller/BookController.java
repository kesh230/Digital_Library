package com.digital.library.controller;

import java.util.List;
import java.util.UUID;

// import org.hibernate.mapping.List;

// import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.library.entity.Book;
import com.digital.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    public BookService bookService;
    
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
           this.bookService.addBook(book);
            return ResponseEntity.ok(book);
    }
    @GetMapping("/get-books")
    public ResponseEntity<List<Book>> getBook(){
        List<Book> book=this.bookService.getBook();
        return ResponseEntity.ok(book);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Book> getbybookid(@PathVariable UUID id){
        Book getbook=this.bookService.getbybookid(id);
        return ResponseEntity.ok(getbook);
    }
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> deletebyid(@PathVariable UUID id){
        this.bookService.deletebyid(id);
        return ResponseEntity.ok("user deleted successfully");
    }
    
}