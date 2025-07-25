package com.example.lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "id is required")
    @Pattern(regexp = "^[0-9]+$", message = "id must contain digits only")
    private String id;

    @NotEmpty(message = "title is required")
    @Size(min = 3, max = 100, message = "title must be 3-100 characters")
    private String title;

    @NotEmpty(message = "category is required")
    @Pattern(regexp = "^(programming|design|math|science|language)$", message = "category must be one of: programming, design, math, science, language")
    private String category;

    @Min(value = 1, message = "capacity must be at least 1")
    private int capacity;

    private boolean closed;
}
