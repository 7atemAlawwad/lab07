package com.example.lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Enrollment {

    @NotEmpty(message = "id is required")
    @Pattern(regexp = "^[0-9]+$", message = "id must contain digits only")
    private String id;

    @NotEmpty(message = "studentId is required")
    @Pattern(regexp = "^[0-9]+$", message = "studentId must contain digits only")
    private String studentId;

    @NotEmpty(message = "courseId is required")
    @Pattern(regexp = "^[0-9]+$", message = "courseId must contain digits only")
    private String courseId;

    @DecimalMin(value = "0.0", message = "grade cannot be negative")
    @DecimalMax(value = "100.0", message = "grade cannot exceed 100")
    private Double grade; // may be null initially

    private LocalDateTime enrolledAt = LocalDateTime.now();
}
