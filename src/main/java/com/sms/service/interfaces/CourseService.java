package com.sms.service.interfaces;

import com.sms.requestDto.CourseDto;
import com.sms.model.Course;
import com.sms.responseDto.CourseResponseDto;

import java.util.List;

public interface CourseService {

       CourseResponseDto createCourse(CourseDto dto);
       CourseResponseDto findByCourseName(String courseName);
       List<CourseResponseDto> findAllCourses();
       CourseResponseDto findByCourseCode(String courseCode);
       CourseResponseDto updateCourse(CourseDto dto,long courseId);
       String deleteCourseById(long courseId);



}
