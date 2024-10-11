package com.Movies.On_Screen.Archive.dtos;

import com.Movies.On_Screen.Archive.enums.Type;
import com.Movies.On_Screen.Archive.enums.WhereToWatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviesDto {
    private String id;
    private String name;
    private Type type;
    private Integer rate;
    private WhereToWatch whereToWatch;
    private int yearProduced;
    private String language;
    private String country;
    private String roles;



}
