package com.Movies.On_Screen.Archive.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
