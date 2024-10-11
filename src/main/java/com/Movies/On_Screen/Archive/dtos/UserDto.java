package com.Movies.On_Screen.Archive.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.Movies.On_Screen.Archive.models.Auditable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends Auditable {
    private String id;
    private String name;
    private String password;
    private String email;
    private String roles;

}
