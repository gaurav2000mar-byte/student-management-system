package com.sms.controller;

import com.sms.requestDto.StudentDto;
import com.sms.exception.BadRequestException;
import com.sms.model.Student;
import com.sms.responseDto.StudentResponseDto;
import com.sms.service.StudentServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {


    private final  StudentServiceImplementation service;

    @PostMapping("/create")
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody @Valid StudentDto dto){
        StudentResponseDto student=   service.createStudent(dto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/all/student")
    public ResponseEntity<List<StudentResponseDto>> getAllStudent(){
        return ResponseEntity.ok(service.getAllStudent());
    }

    @GetMapping("/get/student/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable long id){
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @PutMapping("/update/student/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable long id,@RequestBody @Valid StudentDto dto){
        StudentResponseDto student=   service.updateStudent(id,dto);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/search/student")
    public ResponseEntity<List<StudentResponseDto>> searchStudent(
            @RequestParam(required = false) String name,
            @RequestParam(required = false)String email){

       if(name!=null && email!=null){
           return ResponseEntity.ok(service.searchByNameAndEmail(name, email));
       } else if (name!=null) {
           return ResponseEntity.ok(service.searchByName(name));
       }else if (email!=null) {
           return ResponseEntity.ok(service.searchByEmail(email));
       }

        throw new BadRequestException("Please provide name or email to search");

    }


    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponseDto>> searchByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3")int size,
            @RequestParam(defaultValue = "id")String sortBy
    ){
       return ResponseEntity.ok(service.getStudentPage(page, size, sortBy));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        return ResponseEntity.ok("Deleted Sucessfully");
    }


}
