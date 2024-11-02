package com.digital.library.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

 
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private Long mobileNo;
    private String password;
    private String role;

    @Email
    private String email;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Subscription subscription=Subscription.INACTIVE;
    
    public enum Subscription{
        ACTIVE,
        INACTIVE
    }

}
