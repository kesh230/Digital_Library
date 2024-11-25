package com.digital.library.configuration;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.digital.library.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter{

private final JwtUtil jwtUtil;
  
public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

@Override
protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)
throws ServletException,IOException{
    String authorizationHeader=request.getHeader("Authorization");
    String token=null;
    String username=null;
    
    if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
        token=authorizationHeader.substring(7);
        username=jwtUtil.extractUsername(token);
    }
    if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null 
             && jwtUtil.validateToken(token,username)){
                               UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(username,null,
                                  this.jwtUtil.getRolesFromToken(token));
         authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
         SecurityContextHolder.getContext().setAuthentication(authenticationToken);                               
        }
        chain.doFilter(request, response);
}
 
    
}
