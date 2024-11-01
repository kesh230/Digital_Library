package com.digital.library.service;

// import java.lang.foreign.Linker;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digital.library.entity.Book;
import com.digital.library.entity.Member;
import com.digital.library.repository.Book_repository;
import com.digital.library.repository.Member_repository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {
    
    @Autowired
    private final Book_repository book_repository;
    
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final Member_repository member_repository;

    public BookService(Book_repository book_repository, Member_repository member_repository, PasswordEncoder passwordEncoder) {
        this.book_repository = book_repository;
        this.member_repository = member_repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Book addBook(Book book){
     return this.book_repository.save(book);
    }

    public List<Book> getBook(){
        return  this.book_repository.findAll();
    }
  
    public Book getbybookid(UUID id){
        Optional<Book> book=this.book_repository.findById(id);
        return book.orElse(null);

    }
    public void deletebyid(UUID id){
        this.book_repository.deleteById(id);
    }
    public Member addmember(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return this.member_repository.save(member);
    }
    public List<Member> getMember(){
        return this.member_repository.findAll();
    }
    public void deleteMemberbyId(UUID id){
        this.member_repository.deleteById(id);
    }
    // public Member updateMember(Member member){
    //     Optional<Member> member1=this.member_repository.getMemberByEmail(member.getEmail());
    //     member1.setFirstName(member.getFirstName());
    //     member1.setLastName(member.getLastName());
    //     member1.setEmail(member.getEmail());
    //     member1.setMobileNo(member.getMobileNo());
    //     member1.setSubscription(member.getSubscription());
    //     member1.

    //     // member1.builder().firstName(member.getFirstName())
    //     //                  .lastName(member.getLastName())
    //     //                  .email(member.getEmail())
    //     //                  .mobileNo(member.getMobileNo())
    //     //                  .Subscription(member.getSubscription()).build();
    //     return this.member_repository.save(member1);
    // }

    public Member getMemberById(UUID memberid) {
        Optional<Member> member=this.member_repository.findById(memberid);
        return member.orElse(null);

}
}
