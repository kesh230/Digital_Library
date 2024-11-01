package com.digital.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    // @PutMapping("/update")
    // public ResponseEntity<?> updateMember(@RequestBody Member member){
    //     Member member1=this.bookService.updateMember(member);
    //     return ResponseEntity.ok(member1);
    // }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable UUID id){
        this.bookService.deleteMemberbyId(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
