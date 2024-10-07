package com.digital.library.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

// import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Book {
  
@SuppressWarnings("deprecation")
@Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")    
private UUID id;
private String name;
private String author;
private String isbn;
private Double price;
private String description;

@Enumerated(EnumType.STRING) 
private Category category;


public enum Category{
    FICTION,
    NON_FICTION
}

    
}
