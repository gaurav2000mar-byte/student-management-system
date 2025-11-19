package com.sms.service;

import com.sms.model.Course;
import com.sms.repositary.CourseRepo;
import com.sms.requestDto.TeacherDto;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Teacher;
import com.sms.repositary.TeacherRepo;
import com.sms.responseDto.TeacherResponseDto;
import com.sms.service.interfaces.TeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImplementation implements TeacherService {


    private final  TeacherRepo teacherRepo;

    private final CourseRepo courseRepo;

    public TeacherResponseDto mapEntityToDto(Teacher teacher){
        TeacherResponseDto dto=new TeacherResponseDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setSpecilization(teacher.getSpecialization());
        dto.setCoursesNames(teacher.getCourses().stream()
                .map(Course::getName)
                .toList());
        return dto;
    }


    public Teacher mapDtoToEntity(TeacherDto dto,Teacher teacher){
        teacher.setName(dto.getName());
        teacher.setSpecialization(dto.getSpecialization());
       List<Course> courses= courseRepo.findAllById(dto.getCoursesId());
       teacher.setCourses(courses);
        return teacher;
    }
    @Override
    public TeacherResponseDto createTeacher(TeacherDto dto) {
        Teacher teacher=  mapDtoToEntity(dto,new Teacher());
        teacherRepo.save(teacher);

        return mapEntityToDto(teacher);
    }

    @Override
    public List<TeacherResponseDto> findByTeacherName(String teacherName) {
       List<Teacher> teacher= teacherRepo.findByNameContainingIgnoreCase(teacherName);
        return teacher.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public List<TeacherResponseDto> findAllTeacher() {
      List<Teacher> teacher=  teacherRepo.findAll();

        return teacher.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public List<TeacherResponseDto> findByTeacherSpecialization(String Specialization) {
        List<Teacher> teacher=   teacherRepo.findBySpecialization(Specialization);
        return teacher.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public TeacherResponseDto updateTeacher(TeacherDto dto, long teacherId) {
        Teacher existed= teacherRepo.findById(teacherId).orElseThrow(()->{
            throw new ResourceNotFoundException("Teacher not existed with Id : "+teacherId);
        });
        Teacher teacher=  mapDtoToEntity(dto,existed);
        teacherRepo.save(teacher);
        return this.mapEntityToDto(teacher);
    }

    @Override
    @Transactional
    public String deleteTeacherById(long teacherId) {
        Teacher existed=  teacherRepo.findById(teacherId).orElseThrow(()->{
            throw new ResourceNotFoundException("Teacher not existed with Id : "+teacherId);
        });
        existed.getCourses().forEach((course->{
            course.getTeachers().remove(existed);
                }
                ));
        teacherRepo.delete(existed);
        return "Teacher Deleted Sucessfully";
    }

    @Override
    public List<TeacherResponseDto> findBySpecializationAndName(String specialization, String name) {
        List<Teacher> teacher=  teacherRepo.findBySpecializationAndName(specialization, name);
        return teacher.stream()
                .map(this::mapEntityToDto)
                .toList();
    }
}
