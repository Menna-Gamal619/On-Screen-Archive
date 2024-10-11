package com.Movies.On_Screen.Archive.mapper;

import com.Movies.On_Screen.Archive.document.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<UserDto, UserEntity>{
    public UserMapper() {
        super(UserDto.class, UserEntity.class);
    }




    @Override
    public UserEntity updateEntity(UserEntity entity, UserDto dto) {
        return null;


    }
}
