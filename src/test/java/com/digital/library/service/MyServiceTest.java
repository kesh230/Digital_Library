package com.digital.library.service;

 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.digital.library.LibraryApplication;

@SpringBootTest(classes=LibraryApplication.class)
public class MyServiceTest {
   
    @Autowired
    Myservice service;


    @Test
    void dividetwonumbers() throws Exception{
     int a=20,b=5;
     double result=service.divide(a, b);
     Assertions.assertEquals(4.0, result);
        }

    @Test
    void divide_with_zero(){
     int a=20,b=0;
     Assertions.assertThrows(Exception.class, ()->{
        this.service.divide(a, b);
     });
        }
    
}
