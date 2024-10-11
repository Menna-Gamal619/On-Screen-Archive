package com.Movies.On_Screen.Archive.mapper;

import com.Movies.On_Screen.Archive.document.Movies;
import com.Movies.On_Screen.Archive.dtos.MoviesDto;

public class MoviesMapper extends AbstractMapper<MoviesDto, Movies>{

    public MoviesMapper() {
        super(MoviesDto.class, Movies.class);
    }

    @Override
    public Movies updateEntity(Movies entity, MoviesDto dto) {
        if (dto.getName()!=null && !dto.getName().isEmpty())
            entity.setName(dto.getName());
        if (dto.getType()!=null )
            entity.setType(dto.getType());
        if (dto.getLanguage()!=null && !dto.getLanguage().isEmpty())
            entity.setLanguage(dto.getLanguage());
        if (dto.getCountry()!=null && !dto.getCountry().isEmpty())
            entity.setCountry(dto.getCountry());
        if (dto.getWhereToWatch()!=null )
            entity.setWhereToWatch(dto.getWhereToWatch());
        return entity;
    }
}
