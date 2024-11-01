package com.digital.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digital.library.entity.Member;
import com.digital.library.repository.Member_repository;

@Service
public class UserDetailService implements UserDetailsService {

    private Member_repository member_repository;

  @Autowired
  public UserDetailService(Member_repository member_repository) {
        this.member_repository = member_repository;
    }



  @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Member> member=this.member_repository.getMemberByEmail(username);
       if(member.isEmpty()){
        throw new UsernameNotFoundException("User does not exit");
       }
       Member m=member.get();
       return User.builder().username(m.getEmail())
                  .password(m.getPassword()).build();
    }
    
}
