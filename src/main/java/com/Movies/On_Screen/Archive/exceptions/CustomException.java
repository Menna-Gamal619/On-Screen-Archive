package com.Movies.On_Screen.Archive.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private String message;
    private HttpStatus status;
}
