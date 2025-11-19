package com.sms.repositary;

import com.sms.model.Teacher;
import com.sms.responseDto.TeacherResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {

    List<Teacher> findByNameContainingIgnoreCase(String name);
    List<Teacher> findBySpecialization(String specialization);

    List<Teacher> findBySpecializationAndName(String specialization,String name);

}
