package com.digital.library.entity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Entity
public class IssueData {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    
    @OneToOne
    @NotNull
    @JsonIncludeProperties({"id","firstName","mobileNo"})
    private Member member;
    
    @OneToOne
    @NotNull
    @JsonIncludeProperties({"id","name","author","price"})
    private Book book;
    
    @Builder.Default
    private Instant createdAt=Instant.now();
    
    private Instant expirationDate;

    private double amountPaid;
    
    // @JsonProperty
    // public UUID getMemberid(){
    //     return this.member.getId();
    // }
    // @JsonProperty
    // public UUID getBookid(){
    //     return this.book.getId();
    // }
    
    @NotNull
    @Builder.Default
    private IssueStatus status =IssueStatus.ISSUED;

    public Instant calculateExpirationDate(){
        this.expirationDate=this.createdAt.plus(15,ChronoUnit.DAYS);
        return this.expirationDate;
    }
    
    public double calculateAmountPaid(){
       this.amountPaid=this.book.getPrice()*0.05D;
       return this.amountPaid;
    }
}
