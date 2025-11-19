package com.sms.service.interfaces;

import com.sms.requestDto.StudentDto;
import com.sms.model.Student;
import com.sms.responseDto.StudentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentDto studentDto);
    StudentResponseDto updateStudent(long stdId,StudentDto studentDto);
    StudentResponseDto getStudentById(long stdId);
    List<StudentResponseDto> getAllStudent();
    String deleteStudent(long stdId);
    List<StudentResponseDto> searchByName(String name);
    List<StudentResponseDto> searchByEmail(String email);
    Page<StudentResponseDto> getStudentPage(int page,int noOfRows,String sortBy);
    List<StudentResponseDto> searchByNameAndEmail(String name,String email);
}
