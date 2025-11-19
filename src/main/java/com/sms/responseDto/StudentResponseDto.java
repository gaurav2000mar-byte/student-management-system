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
public class StudentResponseDto {

    private long id;
    private String name;
    private String email;
    private int age;
    private List<String> courseName;
}
