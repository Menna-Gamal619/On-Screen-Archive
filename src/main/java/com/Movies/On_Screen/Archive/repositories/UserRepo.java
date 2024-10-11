package com.Movies.On_Screen.Archive.repositories;

import com.Movies.On_Screen.Archive.document.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntity,String>{
    UserEntity findByUsername(@NotEmpty String username);
}
