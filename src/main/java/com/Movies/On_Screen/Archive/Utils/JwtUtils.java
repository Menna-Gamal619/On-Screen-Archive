package com.Movies.On_Screen.Archive.Utils;

import com.Movies.On_Screen.Archive.document.UserEntity;
import com.Movies.On_Screen.Archive.models.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {
    public String generate(UserEntity user){
        HashMap<String,Object> claims= new HashMap<>();
        claims.put("username",user.getName());
        claims.put("userId",user.getId());
        claims.put("roles",user.getRoles());
        return  Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+(24*60*60*1000)))
                .setClaims(claims)
                .compact();

    }

    public Boolean isValid(String token){
        Claims claims= Jwts.parserBuilder().build().parseClaimsJws(token).getBody();
        if (claims.getExpiration().getTime()< System.currentTimeMillis())
            return false;
        if (System.currentTimeMillis()<claims.getIssuedAt().getTime())
            return false;
        return true;
    }

    public TokenInfo extract(String token){
        Claims claims= Jwts.parserBuilder().build().parseClaimsJws(token).getBody();
        return TokenInfo.builder().username(claims.get("username").toString()).userId(claims.get("userId").toString())
                .roles(claims.get("roles").toString()).ExpiredAt(claims.getExpiration()).IssuedAt(claims.getIssuedAt()).build();
    }
}
