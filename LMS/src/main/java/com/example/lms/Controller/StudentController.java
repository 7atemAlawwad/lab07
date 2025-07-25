package com.example.lms.Controller;

import com.example.lms.Api.ApiResponse;
import com.example.lms.Model.Student;
import com.example.lms.Service.CourseService;
import com.example.lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService ss; private final CourseService cs;

    @GetMapping("/get")
    public ResponseEntity<?> getAll(){ return ResponseEntity.status(200).body(ss.getAll()); }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Student s, Errors e){
        if(e.hasErrors())
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        ss.add(s);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@Valid @RequestBody Student s,Errors e){
        if(e.hasErrors())
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        if(ss.update(id,s))
            return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        if(ss.delete(id))
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<?> courses(@PathVariable String id){
        if(ss.getById(id)==null)
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(ss.coursesOf(id,cs));
    }

    @GetMapping("/{id}/count-courses")
    public ResponseEntity<?> countCourses(@PathVariable String id){
        if(ss.getById(id)==null)
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(ss.countCourses(id));
    }

    @DeleteMapping("/{sid}/drop/{cid}")
    public ResponseEntity<?> drop(@PathVariable String sid,@PathVariable String cid){
        if(ss.drop(sid,cid))
            return ResponseEntity.status(200).body(new ApiResponse("Dropped"));
        return ResponseEntity.status(400).body(new ApiResponse("Enrollment not found"));
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<?> suspend(@PathVariable String id){
        if(ss.suspend(id))
            return ResponseEntity.status(200).body(new ApiResponse("Suspended & unenrolled"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }
}
