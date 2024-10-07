package com.digital.library.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digital.library.entity.IssueDTO;
import com.digital.library.entity.IssueData;
import com.digital.library.service.IssueService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/issue")
@Slf4j
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/add")
    public ResponseEntity<IssueData> addIssuedata(@RequestBody IssueDTO issueDTO){
          IssueData issueD=this.issueService.addIssueData(issueDTO);
         return ResponseEntity.ok(issueD);
    }

    @GetMapping("/get/{member_id}")
    public ResponseEntity<IssueData> getIssueData(@PathVariable UUID member_id){
        IssueData issueData=this.issueService.getIssueDatabyMemberId(member_id);
        return ResponseEntity.ok(issueData);
    }

    @PutMapping("/change/{member_id}")
    public ResponseEntity<IssueData> updateStatus(@PathVariable UUID member_id){
        IssueData issueData=this.issueService.checkIssueStatus(member_id);
        return ResponseEntity.ok(issueData);

    }

}
