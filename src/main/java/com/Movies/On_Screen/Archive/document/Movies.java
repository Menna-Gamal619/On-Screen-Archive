package com.Movies.On_Screen.Archive.document;

import com.Movies.On_Screen.Archive.enums.Type;
import com.Movies.On_Screen.Archive.enums.WhereToWatch;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Movies {
    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Type type;
    @Max(10)
    private Integer rate;
    @NotEmpty
    private WhereToWatch whereToWatch;
    @NotEmpty
    private int yearProduced;
    @NotEmpty
    private String language;
    @NotEmpty
    private String country;
    private String roles;
}
