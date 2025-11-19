package com.sms.controller;

import com.sms.requestDto.TeacherDto;
import com.sms.model.Teacher;
import com.sms.responseDto.TeacherResponseDto;
import com.sms.service.TeacherServiceImplementation;
import com.sms.service.interfaces.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {


    private final  TeacherServiceImplementation service;

    @PostMapping("/create")
    public ResponseEntity<TeacherResponseDto> createTeacher(@Valid @RequestBody TeacherDto dto){
        return ResponseEntity.ok(service.createTeacher(dto));
    }
    @GetMapping("/find/teacher/name/{name}")
    public ResponseEntity<List<TeacherResponseDto>> findByTeacherName(@PathVariable String name){
        return ResponseEntity.ok(service.findByTeacherName(name));
    }

    @GetMapping("/find/all/teacher")
    public ResponseEntity<List<TeacherResponseDto>> findAllTeacher(){
        return ResponseEntity.ok(service.findAllTeacher());
    }

    @GetMapping("/find/Specialization/{specialization}")
    public ResponseEntity<List<TeacherResponseDto>> findByTeacherSpecialization(@PathVariable String specialization){
        return ResponseEntity.ok(service.findByTeacherSpecialization(specialization));
    }

    @PutMapping("/update/{teacherId}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable long teacherId, @Valid @RequestBody TeacherDto dto){
        return ResponseEntity.ok(service.updateTeacher(dto,teacherId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable long id){
        return ResponseEntity.ok(service.deleteTeacherById(id));
    }

    @GetMapping("/find/Specialization/name")
    public ResponseEntity<List<TeacherResponseDto>> findBySpecializationAndName(
            @RequestParam String specialization,
            @RequestParam String name
    ){
        return ResponseEntity.ok(service.findBySpecializationAndName(specialization, name));
    }
}
