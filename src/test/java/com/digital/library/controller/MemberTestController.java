package com.digital.library.controller;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digital.library.LibraryApplication;
import com.digital.library.entity.Member;
import com.digital.library.entity.Member.Subscription;
import com.digital.library.service.BookService;

@SpringBootTest(classes=LibraryApplication.class)
public class MemberTestController {
    
    @MockBean
    private BookService bookService;

    private MemberController memberController;
    
    @Autowired
    public MemberTestController(MemberController memberController) {
        this.memberController = memberController;
    }

    static Member member=Member.builder()
                        .email("test@gmail.com")
                        .id(UUID.randomUUID())
                        .firstName("test")
                        .lastName("jack")
                        .mobileNo(90909L)
                        .subscription(Subscription.ACTIVE)
                        .build();

    // @Test
    // void addmember(){
    //     Mockito.when(this.bookService.addmember(member)).thenReturn(member);
    //     // ResponseEntity<Member> response= memberController.addmember(member);
    //     Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    //     Assertions.assertEquals(member,response.getBody());
    //     }
}
