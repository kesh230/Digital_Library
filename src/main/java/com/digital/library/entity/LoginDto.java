package com.digital.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    
    
    private String email;
    private String password;


    
}
