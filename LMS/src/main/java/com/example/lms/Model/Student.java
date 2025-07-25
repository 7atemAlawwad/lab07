package com.example.lms.Model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class Student {

    @NotEmpty(message = "id is required")
    @Pattern(regexp = "^[0-9]+$", message = "id must contain digits only")
    private String id;

    @NotEmpty(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be 3-50 characters")
    private String name;

    @NotEmpty(message = "email is required")
    @Email(message = "email must be valid")
    private String email;

    @DecimalMin(value = "0.0", message = "gpa cannot be negative")
    @DecimalMax(value = "5.0", message = "gpa cannot exceed 5.0")
    private double gpa;

    private boolean active = true;
}
