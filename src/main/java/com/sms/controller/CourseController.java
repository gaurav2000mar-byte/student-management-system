package com.sms.controller;

import com.sms.requestDto.CourseDto;
import com.sms.model.Course;
import com.sms.responseDto.CourseResponseDto;
import com.sms.service.CourseServiceImplementation;
import com.sms.service.interfaces.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {


    private final CourseServiceImplementation service;

    @PostMapping("/create")
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseDto dto){
        return ResponseEntity.ok(service.createCourse(dto));
    }
    @GetMapping("/find/course/name/{name}")
    public ResponseEntity<CourseResponseDto> findByCourseName(@PathVariable String name){
        return ResponseEntity.ok(service.findByCourseName(name));
    }

    @GetMapping("/find/all/course")
    public ResponseEntity<List<CourseResponseDto>> findAllCourse(){
        return ResponseEntity.ok(service.findAllCourses());
    }

    @GetMapping("/find/CourseCode/{code}")
    public ResponseEntity<CourseResponseDto> findByCourseCode(@PathVariable String code){
        return ResponseEntity.ok(service.findByCourseCode(code));
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable long courseId, @Valid @RequestBody CourseDto dto){
        return ResponseEntity.ok(service.updateCourse(dto,courseId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable long id){
        return ResponseEntity.ok(service.deleteCourseById(id));
    }


}

