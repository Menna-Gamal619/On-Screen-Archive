package com.Movies.On_Screen.Archive.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auditable {
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private LocalDateTime modifiedAt;
    @JsonIgnore
    private String modifiedBy;
}
