package com.digital.library.service;

 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digital.library.entity.LoginDto;
import com.digital.library.entity.Member;
import com.digital.library.repository.Member_repository;

@Service
public class AuthService {
    
    private Member_repository member_repository;
    private PasswordEncoder passwordEncoder;
    
    public AuthService(Member_repository member_repository,PasswordEncoder passwordEncoder) {
        this.member_repository = member_repository;
        this.passwordEncoder=passwordEncoder;
    }
    

    
    public Member login(LoginDto loginDto) throws Exception{
      Optional<Member> mem=this.member_repository.getMemberByEmail(loginDto.getEmail());
      if(mem.isEmpty()){
        throw new UsernameNotFoundException(String.format("member not found with username %s", 
                               loginDto.getEmail()));
      }
      Member member=mem.get();
      if(!this.passwordEncoder.matches(loginDto.getPassword(),member.getPassword())){
         throw new Exception("Password is incorrect");
      }
       return member;
    }
}
