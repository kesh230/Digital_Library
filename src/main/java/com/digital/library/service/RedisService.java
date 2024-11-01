// package com.digital.library.service;

// import java.util.List;
// import java.util.Optional;
// import java.util.concurrent.TimeUnit;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Service;

// import com.digital.library.entity.Book;

// // @Service
// public class RedisService {


//     private final RedisTemplate<String,Object> redisTemplate;
    
    
//     @Autowired
//     public RedisService(RedisTemplate<String, Object> redisTemplate) {
//         this.redisTemplate = redisTemplate;
//     }
    
//     public void addtoCache(String key,List<?> value){
//         this.redisTemplate.opsForValue().set(key,value,10,TimeUnit.MINUTES);
//     }
//     public Book getCache(String key) {
//         try {
//             Book cachedBook = (Book) this.redisTemplate.opsForValue().get(key);
//             if (cachedBook == null) {
//                 throw new Exception("No cache found for key: " + key);
//             }
//             return cachedBook;
//         } catch (Exception e) {
//             // Log and handle exception as appropriate
//             System.err.println("Error retrieving cache for key " + key + ": " + e.getMessage());
//             return null; // or rethrow the exception based on your use case
//         }
//     }
//     public void deleteCache(String key){
//         this.redisTemplate.delete(key);
//     }



    
// }
