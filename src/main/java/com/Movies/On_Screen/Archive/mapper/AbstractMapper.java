package com.Movies.On_Screen.Archive.mapper;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<D,E> implements Mapper<D,E>{

    @Autowired
    @Setter
    ModelMapper modelMapper;
    private Class<D> dClass;
    private Class<E> eClass;

    public AbstractMapper(Class<D> dClas,Class<E> eClas){
        this.dClass=dClas;
        this.eClass=eClas;
    }
    @PostConstruct
    public void init(){
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
      modelMapper.getConfiguration().setAmbiguityIgnored(true);
      modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    public D toDto(E entity) {
        return modelMapper.map(entity,dClass);
    }

    @Override
    public E toEntity(D dto) {
        return modelMapper.map(dto,eClass);
    }
}

