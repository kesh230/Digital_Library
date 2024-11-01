package com.digital.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digital.library.entity.LoginDto;
import com.digital.library.entity.Member;
import com.digital.library.service.AuthService;
import com.digital.library.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/api/user")
public class AuthController {

    private BookService bookService;

    private AuthService authservice;


    
    @Autowired
    public AuthController(BookService bookService,AuthService authservice) {
        this.bookService = bookService;
        this.authservice=authservice;
    }

  @PostMapping("/add")
    public ResponseEntity<Member> addmember(@RequestBody Member member){
        this.bookService.addmember(member);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody LoginDto loginDto){
        try {
             Member member=this.authservice.login(loginDto);
             log.info("user is logged in");
             return new ResponseEntity<>(member,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }

    }
    
}
