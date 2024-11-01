package com.digital.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.digital.library.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class SpringSecurityconfig {
    private JwtUtil jwtUtil;
    private JwtRequestFilter jwtRequestFilter;
    

    public SpringSecurityconfig(JwtUtil jwtUtil, JwtRequestFilter jwtRequestFilter) {
        this.jwtUtil = jwtUtil;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter(
            authenticationManagerBean(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil);

       jwtAuthenticationFilter.setFilterProcessesUrl("/api/user/login/v1");
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth->auth.requestMatchers("/book/**").hasAnyRole("MEMBER","LIBRARIAN")
                             .requestMatchers("/member/**").hasRole("MEMBER")
                             .requestMatchers("/api/user/**").permitAll()  
                             .anyRequest().authenticated())
                             .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                             .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();                    
    }
      
    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    //    throws Exception {
    //     return authenticationConfiguration.getAuthenticationManager();
    //    }
       @Bean
       public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
           return authenticationConfiguration.getAuthenticationManager();
       }
   
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    
}
