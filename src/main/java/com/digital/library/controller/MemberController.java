package com.digital.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.library.entity.Member;
import com.digital.library.service.BookService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private BookService bookService;
    
    @GetMapping("/get")
    public ResponseEntity<List<Member>> getMember(){
        List<Member> members=this.bookService.getMember();
        return ResponseEntity.ok(members);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable UUID id){
        this.bookService.deleteMemberbyId(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
