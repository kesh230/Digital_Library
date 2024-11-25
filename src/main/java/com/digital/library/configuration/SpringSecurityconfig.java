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

import com.digital.library.service.CustomOAuth2UserService;
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


    // Role based authentication
    // ADMIN-->Access every end points after login.
    // MEMBER-->Members can only access "/book" endpoint.
    // LIBRARIAN-->They can access all members or "/member" end point.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth->auth.requestMatchers("/book/**","/member/**").hasAnyRole("ADMIN")
                             .requestMatchers("/book/**").hasAnyRole("MEMBER","LIBRARIAN")
                             .requestMatchers("/member/**").hasRole("LIBRARIAN")
                             .requestMatchers("/api/user/**","/auth/**","/oauth2/**").permitAll()  
                             .anyRequest().authenticated())
                             .oauth2Login(oauth2 -> oauth2
                             .loginPage("/oauth2/authorization/github")
                             .defaultSuccessUrl("/", true) 
                             .userInfoEndpoint(userInfo -> userInfo.userService(this.customOAuth2UserService())))
                             .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();                    
    }
      
    
       @Bean
       public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
           return authenticationConfiguration.getAuthenticationManager();
       }

    //  For Implement OAuth2 login using github
   
       @Bean
       public CustomOAuth2UserService customOAuth2UserService() {
           return new CustomOAuth2UserService();
       }
       
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    
}
