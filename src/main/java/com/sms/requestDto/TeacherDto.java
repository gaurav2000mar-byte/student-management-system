package com.sms.requestDto;

import com.sms.model.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {

    @NotBlank(message = "Teacher name cannot be empty")
    private String name;
    @NotBlank(message = "specialization is must to give")
    private String specialization;

    private List<Long> coursesId;
}
