package com.Movies.On_Screen.Archive.services;

import com.Movies.On_Screen.Archive.Utils.JwtUtils;
import com.Movies.On_Screen.Archive.dtos.Credentials;
import com.Movies.On_Screen.Archive.repositories.UserRepo;
import com.Movies.On_Screen.Archive.document.UserEntity;
import com.Movies.On_Screen.Archive.exceptions.CustomException;
import com.Movies.On_Screen.Archive.models.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    MongoTemplate template;
    @Autowired
    UserRepo repo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username));
        UserEntity user=template.findOne(query, UserEntity.class);
        return User.withUsername(user.getName()).roles(user.getRoles()).password(user.getPassword()).build();

    }
    public Boolean isValid (TokenInfo tokenInfo){
        Query query=new Query();
        query.addCriteria(Criteria.where("UserName").is(tokenInfo.getUsername()));
        query.addCriteria(Criteria.where("UserId").is(tokenInfo.getUserId()));
        query.addCriteria(Criteria.where("Roles").is(tokenInfo.getRoles()));
        return template.exists(query,UserEntity.class);
    }
    public String login(Credentials credentials){
        UserEntity user;
        try {
            user=repo.findByUsername(credentials.getUsername());
        }catch (Exception ex){
            throw new CustomException("credentials invalid", HttpStatus.UNAUTHORIZED);
        }
        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword())){
            throw new CustomException("credentials invalid", HttpStatus.UNAUTHORIZED);
        }
        return jwtUtils.generate(user);

    }
}
