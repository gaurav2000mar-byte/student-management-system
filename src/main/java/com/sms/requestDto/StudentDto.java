package com.sms.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @PositiveOrZero(message = "Age cannot be Negative")
    private int age;

    @Email(message = "Invalid Email")
    private String email;

    private List<Long> courseId;


}
