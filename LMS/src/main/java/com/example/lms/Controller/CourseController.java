package com.example.lms.Controller;

import com.example.lms.Api.ApiResponse;
import com.example.lms.Model.Course;
import com.example.lms.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService cs;

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(cs.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Course c, Errors e) {
        if (e.hasErrors())
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        cs.add(c);
        return ResponseEntity.status(200).body(new ApiResponse("Course added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody Course c, Errors e) {
        if (e.hasErrors())
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        if (cs.update(id, c))
            return ResponseEntity.status(200).body(new ApiResponse("Course updated"));
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (cs.delete(id))
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<?> students(@PathVariable String id) {
        if (!cs.exists(id))
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        return ResponseEntity.status(200).body(cs.studentsIn(id));
    }

    @GetMapping("/{id}/avg-grade")
    public ResponseEntity<?> avgGrade(@PathVariable String id) {
        if (!cs.exists(id))
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        return ResponseEntity.status(200).body(cs.avgGrade(id));
    }

    @PutMapping("/{id}/open")
    public ResponseEntity<?> open(@PathVariable String id) {
        if (cs.open(id))
            return ResponseEntity.status(200).body(new ApiResponse("Course opened"));
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }

    @GetMapping("/{id}/capacity-remain")
    public ResponseEntity<?> capacity(@PathVariable String id) {
        int remain = cs.remaining(id);
        if (remain == -1)
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        return ResponseEntity.status(200).body(remain);
    }
}
