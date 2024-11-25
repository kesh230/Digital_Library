package com.digital.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.library.entity.AuthResponse;
import com.digital.library.entity.LoginDto;
import com.digital.library.entity.Member;
import com.digital.library.service.BookService;
import com.digital.library.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final BookService bookService;

    @Autowired
    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, BookService bookService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.bookService = bookService;
    }

  @PostMapping("/add")
    public ResponseEntity<Member> addmember(@RequestBody Member member){
        this.bookService.addmember(member);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
    
}
