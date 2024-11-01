package com.digital.library.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.library.entity.Member;
// import java.util.List;


@Repository
public interface  Member_repository extends JpaRepository<Member,UUID> {
    public Optional<Member> getMemberByEmail(String email);
 
}
