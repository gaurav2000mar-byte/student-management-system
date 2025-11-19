package com.sms.repositary;

import com.sms.model.Student;
import com.sms.responseDto.StudentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    Optional<Student> findByEmail(String email);

    List<Student> findByNameContainingIgnoreCase(String name);
   List<Student> findByEmailContainingIgnoreCase(String email);

    List<Student> findByNameAndEmail(String name,String email);

}
