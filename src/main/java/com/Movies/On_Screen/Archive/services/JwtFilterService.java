package com.Movies.On_Screen.Archive.services;

import com.Movies.On_Screen.Archive.Utils.JwtUtils;
import com.Movies.On_Screen.Archive.exceptions.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.Movies.On_Screen.Archive.models.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilterService extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    CustomUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorization= request.getHeader("Authorization");

        if (authorization !=null && authorization.startsWith("Bearer ")){
            String token=authorization.substring(7);

            if (!jwtUtils.isValid(token)){
                throw new CustomException("invalid token", HttpStatus.UNAUTHORIZED);
            }

            TokenInfo tokenInfo=jwtUtils.extract(token);
            if (userDetailService.isValid(tokenInfo)){
                throw new CustomException("invalid user",HttpStatus.UNAUTHORIZED);
            }
            filterChain.doFilter(request,response);
        }
    }
}
