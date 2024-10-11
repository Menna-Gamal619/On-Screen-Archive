package com.Movies.On_Screen.Archive.services;

import com.Movies.On_Screen.Archive.dtos.MoviesDto;
import com.Movies.On_Screen.Archive.enums.Type;
import com.Movies.On_Screen.Archive.document.Movies;
import com.Movies.On_Screen.Archive.dtos.PageResult;
import com.Movies.On_Screen.Archive.exceptions.CustomException;
import com.Movies.On_Screen.Archive.mapper.MoviesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.Movies.On_Screen.Archive.repositories.MovieRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoviesService {
    @Autowired
    MongoTemplate template;
    @Autowired
    MoviesMapper mapper;
    @Autowired
    MovieRepo repo;

    public String create(MoviesDto dto) {
        Query query= new Query();
        query.addCriteria(Criteria.where("name").regex(dto.getName(),"i"));
        if (template.exists(query, Movies.class))
            throw new CustomException("this movie already exists", HttpStatus.NOT_FOUND);
        return template.save(mapper.toEntity(dto).getId());
    }

    public void delete(String id) {
        Query query= new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        template.remove(query);
    }

    public MoviesDto getById(String id) {
       Optional<Movies> movies=repo.findById(id);
       if (movies.isPresent())
           return new MoviesDto();
       throw new CustomException("this movie not found",HttpStatus.NOT_FOUND);
    }

    public List<MoviesDto> getAll() {
        List<Movies> movies=repo.findAll();
        List<MoviesDto> dtos=new ArrayList<>();
        for (Movies movie:movies){
            dtos.add(mapper.toDto(movie));
        }
        return dtos;
    }

    public PageResult search(String name, Type type, Pageable pageable) {
        Query query= new Query();
        if (name!=null)
            query.addCriteria(Criteria.where("name").regex(name,"i"));
        if (type!=null)
            query.addCriteria(Criteria.where("type").is(type));
        Long count= template.count(query,Movies.class);
        List<MoviesDto> dtos =template.find(query.with(pageable),Movies.class).stream().map(movies -> {
           return mapper.toDto(movies);
        }).collect(Collectors.toList());

        return PageResult.builder().items(dtos).count(count).build();
    }
}
