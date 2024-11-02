package com.digital.library.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.digital.library.entity.LoginDto;
import com.digital.library.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

@Override
public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response){
    try {
 LoginDto loginDto=new ObjectMapper().readValue(request.getInputStream(),LoginDto.class);
 UsernamePasswordAuthenticationToken authenticationToken =
             new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
      return authenticationManager.authenticate(authenticationToken);       
    } 
    catch (Exception e) {
        throw new RuntimeException("Login failed due to incorrect username and password");
    }
}
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                 FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String jwtToken = jwtUtil.generateToken(user);

        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + jwtToken + "\"}");
    }
}
