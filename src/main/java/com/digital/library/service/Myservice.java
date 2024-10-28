package com.digital.library.service;

import org.springframework.stereotype.Service;

@Service
public class Myservice {

    public double divide(int a,int b) throws Exception{
        if(b==0){
         throw new Exception("Some problem");
        }
        if(a>=20){
        return (double)a/b;
    }
    return 0D;

}
    
}
