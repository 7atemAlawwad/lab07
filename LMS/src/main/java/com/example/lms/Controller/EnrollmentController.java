package com.example.lms.Controller;

import com.example.lms.Api.ApiResponse;
import com.example.lms.Model.Enrollment;
import com.example.lms.Service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService es;

    @GetMapping("/get")
    public ResponseEntity<?> getAll(){ return ResponseEntity.status(200).body(es.getAll()); }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Enrollment e, Errors er){
        if(er.hasErrors())
            return ResponseEntity.status(400).body(er.getFieldError().getDefaultMessage());
        es.add(e);
        return ResponseEntity.status(200).body(new ApiResponse("Enrollment added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@Valid @RequestBody Enrollment e, Errors er){
        if(er.hasErrors())
            return ResponseEntity.status(400).body(er.getFieldError().getDefaultMessage());
        if(es.update(id,e))
            return ResponseEntity.status(200).body(new ApiResponse("Enrollment updated"));
        return ResponseEntity.status(400).body(new ApiResponse("Enrollment not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        if(es.delete(id))
            return ResponseEntity.status(200).body(new ApiResponse("Enrollment deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("Enrollment not found"));
    }

    @GetMapping("/student/{sid}")
    public ResponseEntity<?> byStudent(@PathVariable String sid){
        return ResponseEntity.status(200).body(es.byStudent(sid));
    }

    @GetMapping("/course/{cid}")
    public ResponseEntity<?> byCourse(@PathVariable String cid) {
        return ResponseEntity.status(200).body(es.byCourse(cid));
    }

    @GetMapping("/course/{cid}/count")
    public ResponseEntity<?> count(@PathVariable String cid){
        return ResponseEntity.status(200).body(es.countByCourse(cid));
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<?> grade(@PathVariable String id,@RequestParam Double grade){
        if(es.setGrade(id,grade))
            return ResponseEntity.status(200).body(new ApiResponse("Grade set"));
        return ResponseEntity.status(400).body(new ApiResponse("Enrollment not found"));
    }
}

