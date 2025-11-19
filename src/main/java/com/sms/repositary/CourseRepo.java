package com.sms.repositary;

import com.sms.model.Course;
import com.sms.responseDto.CourseResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {


    Optional<Course> findByName(String name);

    Course findByNameContainingIgnoreCase(String name);
    Course findByCourseCode(String courseCode);
}
