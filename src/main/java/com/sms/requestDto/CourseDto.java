package com.sms.requestDto;

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
public class CourseDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Course Code is mandatory")
    private String courseCode;

    private List<Long> studentIds;

    private List<Long> teachersIds;


}
