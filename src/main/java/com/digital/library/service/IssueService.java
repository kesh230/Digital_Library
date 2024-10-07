package com.digital.library.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.library.entity.Book;
import com.digital.library.entity.IssueDTO;
import com.digital.library.entity.IssueData;
import com.digital.library.entity.IssueStatus;
import com.digital.library.entity.Member;
import com.digital.library.repository.IssueDataRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssueService {
    
     
    
    private final IssueDataRepository IssueDataRepository;
    private final BookService book_service;
  
    @Autowired
    public IssueService(IssueDataRepository IssueDataRepository, BookService book_service) {
        this.IssueDataRepository = IssueDataRepository;
        this.book_service = book_service;
        
    }

    
     public IssueData addIssueData(IssueData issueData){
        log.info("book is issuing");
        issueData.calculateAmountPaid();
        issueData.calculateExpirationDate();
        IssueData issueData1=this.IssueDataRepository.save(issueData);
        log.info("book is issued with book id {} and member id",issueData1.getBook().getId(),
        issueData1.getMember().getId());
        return issueData1;
    }
    public IssueData addIssueData(IssueDTO issueDTO) {
         Book book=this.book_service.getbybookid(issueDTO.getBookid());
         Member member=this.book_service.getMemberById(issueDTO.getMemberid());
         IssueData issueData=IssueData.builder()
                             .book(book)
                             .member(member)
                             .build();
                return this.addIssueData(issueData);

    }

    public IssueData getIssueDatabyMemberId(UUID member_id){
      IssueData issueData=this.IssueDataRepository.findByMember_id(member_id);
      return issueData;
    }

    public IssueData checkIssueStatus(UUID member){
        IssueData issueData=this.IssueDataRepository.findByMember_id(member);
        if(issueData.getExpirationDate().isBefore(Instant.now())){
            issueData.setStatus(IssueStatus.EXPIRED);
        }
        else
          {
            issueData.setStatus(IssueStatus.ISSUED);
          }
        return this.IssueDataRepository.save(issueData);
    }
    
}
