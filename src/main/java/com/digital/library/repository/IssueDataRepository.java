package com.digital.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.library.entity.IssueData;


@Repository
public interface IssueDataRepository extends JpaRepository<IssueData,UUID> {
      
      IssueData findByMember_id(UUID member_id);

}
