package com.sms.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDto {

    private long id;
    private String courseName;
    private String courseCode;
    private List<String> studentName;
    private List<String> tecaherNames;

}
