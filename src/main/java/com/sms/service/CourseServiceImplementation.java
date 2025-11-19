package com.sms.service;

import com.sms.model.Student;
import com.sms.model.Teacher;
import com.sms.repositary.StudentRepo;
import com.sms.repositary.TeacherRepo;
import com.sms.requestDto.CourseDto;
import com.sms.exception.BadRequestException;
import com.sms.exception.ResourceNotFoundException;
import com.sms.model.Course;
import com.sms.repositary.CourseRepo;
import com.sms.responseDto.CourseResponseDto;
import com.sms.service.interfaces.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {


   private final CourseRepo courseRepo;


    private final StudentRepo studentRepo;


    private final TeacherRepo teacherRepo;


    public CourseResponseDto mapEntityToDto(Course course){
        CourseResponseDto dto=new CourseResponseDto();
        dto.setId(course.getId());
        dto.setCourseName(course.getName());
        dto.setCourseCode(course.getCourseCode());
        List<String> studentList= course.getStudents()
                .stream()
                .map(Student::getName)
                .toList();

        List<String> teacherList=  course.getTeachers()
                .stream()
                .map(Teacher::getName)
                .toList();
        dto.setStudentName(studentList);
        dto.setTecaherNames(teacherList);
        return dto;
    }

    public Course mapDtoToEntity(CourseDto dto,Course course){
        course.setName(dto.getName());
        course.setCourseCode(dto.getCourseCode());
        List<Student>  student=studentRepo.findAllById(dto.getStudentIds());
        List<Teacher> teachers= teacherRepo.findAllById(dto.getTeachersIds());
        course.setTeachers(teachers);
        course.setStudents(student);
        return course;
    }


    @Override
    public CourseResponseDto createCourse(CourseDto dto) {
        courseRepo.findByName(dto.getName()).ifPresent(exist->{
            throw new BadRequestException("Course already exist");
        });
        Course course=  mapDtoToEntity(dto,new Course());
        courseRepo.save(course);
        return mapEntityToDto(course);
    }

    @Override
    public CourseResponseDto findByCourseName(String courseName) {
     Course course=   courseRepo.findByNameContainingIgnoreCase(courseName);
        return this.mapEntityToDto(course);
    }

    @Override
    public List<CourseResponseDto> findAllCourses() {
      List<Course> courses=  courseRepo.findAll();
        return courses.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public CourseResponseDto findByCourseCode(String courseCode) {
        Course course=     courseRepo.findByCourseCode(courseCode);
        return this.mapEntityToDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(CourseDto dto, long courseId) {
        Course existing=  courseRepo.findById(courseId).orElseThrow(()->{
            throw new ResourceNotFoundException("Course not existed");
        });
        Course course=  mapDtoToEntity(dto,existing);
        courseRepo.save(course);
        return this.mapEntityToDto(course);
    }

    @Override
    @Transactional
    public String deleteCourseById(long courseId) {
        Course existing=  courseRepo.findById(courseId).orElseThrow(()->{
            throw new ResourceNotFoundException("Course not existed with this Id : "+courseId);
        });
        existing.getTeachers().forEach(teacher -> {
            teacher.getCourses().remove(existing);
        });
        existing.getStudents().forEach(student -> {
            student.getCourses().remove(existing);
        });
        courseRepo.delete(existing);
        return "Course Deleted Sucessfully";
    }
}
