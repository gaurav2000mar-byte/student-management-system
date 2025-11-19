package com.sms.service.interfaces;

import com.sms.requestDto.TeacherDto;
import com.sms.model.Teacher;
import com.sms.responseDto.TeacherResponseDto;

import java.util.List;

public interface TeacherService {


    TeacherResponseDto createTeacher(TeacherDto dto);
    List<TeacherResponseDto> findByTeacherName(String teacherName);
    List<TeacherResponseDto> findAllTeacher();
    List<TeacherResponseDto> findByTeacherSpecialization(String Specialization);
    TeacherResponseDto updateTeacher(TeacherDto dto,long teacherId);
    String deleteTeacherById(long teacherId);

    List<TeacherResponseDto> findBySpecializationAndName(String specialization,String name);

}
