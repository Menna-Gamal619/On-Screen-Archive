package com.Movies.On_Screen.Archive.controllers;

import com.Movies.On_Screen.Archive.dtos.MoviesDto;
import com.Movies.On_Screen.Archive.enums.Type;
import com.Movies.On_Screen.Archive.dtos.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.Movies.On_Screen.Archive.services.MoviesService;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MoviesController {
    @Autowired
    MoviesService service;

    @PostMapping
    public String create(@RequestBody MoviesDto dto){
        return service.create(dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
    @GetMapping("/{id}")
    public MoviesDto getById(String id){
        return service.getById(id);
    }
    @GetMapping
    public List<MoviesDto> getAll(){
        return service.getAll();
    }
    @GetMapping("/search")
    public PageResult search(@RequestParam(required = false) String name,@RequestParam(required = false) Type type,
                             @RequestParam(required = false,defaultValue = "0")int page,
                             @RequestParam(required = false,defaultValue = "15")int size){
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.DESC);
        return service.search(name,type,pageable);
    }
}
