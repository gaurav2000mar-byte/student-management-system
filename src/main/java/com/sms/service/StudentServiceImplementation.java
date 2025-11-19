package com.sms.service;

import com.sms.model.Course;
import com.sms.repositary.CourseRepo;
import com.sms.requestDto.StudentDto;
import com.sms.exception.BadRequestException;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Student;
import com.sms.repositary.StudentRepo;
import com.sms.responseDto.StudentResponseDto;
import com.sms.service.interfaces.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {


   private final StudentRepo studentRepo;


    private final CourseRepo courseRepo;

    public StudentResponseDto mapEntityToDto(Student student){
        StudentResponseDto dto=new StudentResponseDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        dto.setCourseName(student.getCourses().stream()
                .map(Course::getName)
                .toList());

        return dto;
    }

    public Student mapDtoToEntity(StudentDto dto,Student student){
        student.setAge(dto.getAge());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        List<Course> courses= courseRepo.findAllById(dto.getCourseId());
        student.setCourses(courses);
        return  student;
    }

    @Override
    public StudentResponseDto createStudent(StudentDto studentDto) {
        studentRepo.findByEmail(studentDto.getEmail()).ifPresent(s->{
            throw new BadRequestException("Student is present with this Email Id");
        });


        Student student=  mapDtoToEntity(studentDto,new Student());
        studentRepo.save(student);

        return mapEntityToDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(long stdId, StudentDto studentDto) {
        Student student=  studentRepo.findById(stdId).orElseThrow(()->{
            throw new ResourceNotFoundException("Student with Id not existed");
        });
        Student updateStudent= mapDtoToEntity(studentDto,student);
        studentRepo.save(updateStudent);
        return mapEntityToDto(updateStudent);
    }

    @Override
    public StudentResponseDto getStudentById(long stdId) {
        Student student=  studentRepo.findById(stdId).orElseThrow(()->{
            throw new ResourceNotFoundException("Student with Id not existed");
        });
        return mapEntityToDto(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudent() {

          List<Student> student=  studentRepo.findAll();
          return   student.stream()
                 .map(this::mapEntityToDto)
                 .toList();
    }

    @Override
    @Transactional
    public String deleteStudent(long stdId) {
        Student student=  studentRepo.findById(stdId).orElseThrow(()->{
            throw new ResourceNotFoundException("Student with Id not existed");
        });
        student.getCourses().forEach(course -> {
            course.getStudents().remove(student);
        });
        studentRepo.delete(student);
        return "Student Deleted Sucessfully";
    }

    @Override
    public List<StudentResponseDto> searchByName(String name) {
        List<Student> student=  studentRepo.findByNameContainingIgnoreCase(name);
        return   student.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public List<StudentResponseDto> searchByEmail(String email) {
        List<Student> student=  studentRepo.findByEmailContainingIgnoreCase(email);
        return   student.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public Page<StudentResponseDto> getStudentPage(int page, int noOfRows, String sortBy) {
        PageRequest pagable=   PageRequest.of(page,noOfRows, Sort.by(sortBy));
        Page<Student> students=  studentRepo.findAll(pagable);
        return  students.map(this::mapEntityToDto);

    }

    @Override
    public List<StudentResponseDto> searchByNameAndEmail(String name, String email) {
        List<Student> student=  studentRepo.findByNameAndEmail(name,email);
        return   student.stream()
                .map(this::mapEntityToDto)
                .toList();
    }
}
