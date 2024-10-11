package com.Movies.On_Screen.Archive.mapper;

public interface Mapper<D,E>{
    public D toDto(E entity);
    public E toEntity(D dto);
    public E updateEntity(E entity,D dto);
}
