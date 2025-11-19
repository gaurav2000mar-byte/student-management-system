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
public class TeacherResponseDto {

    private long id;
    private String name;
    private String specilization;
    private List<String> coursesNames;
}
