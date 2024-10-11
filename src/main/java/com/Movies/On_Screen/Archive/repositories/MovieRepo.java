package com.Movies.On_Screen.Archive.repositories;

import com.Movies.On_Screen.Archive.document.Movies;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepo extends MongoRepository<Movies,String> {
}
