package com.digital.library.service;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digital.library.LibraryApplication;
import com.digital.library.entity.Member;
import com.digital.library.entity.Member.Subscription;
import com.digital.library.repository.Member_repository;

@SpringBootTest(classes=LibraryApplication.class)
public class MemberServiceTest {
    
    @MockBean
    private Member_repository member_repository;
    
    
    private BookService bookService;
    
    @Autowired
    public MemberServiceTest(BookService bookService) {
        this.bookService = bookService;
    }

    static Member member =Member.builder()
                                .firstName("test")
                                .lastName("notTEST")
                                .id(UUID.randomUUID())
                                .email("y@gmail.com")
                                .mobileNo(90876567L)
                                .subscription(Subscription.ACTIVE)
                                .build();

    @Test
    void addmember(){
        Mockito.when(this.member_repository.save(member)).thenReturn(member);
        Member member1 = bookService.addmember(member);
        Assertions.assertEquals(member1,member);
    }

    @Test
    void getmemberbyid(){
    Mockito.when(this.member_repository.findById(member.getId())).thenReturn(Optional.of(member));
    Member member1=bookService.getMemberById(member.getId());
    Assertions.assertEquals(member1,member);

    
    }
}
