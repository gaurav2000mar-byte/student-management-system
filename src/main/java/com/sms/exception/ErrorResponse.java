package com.sms.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime dateTime;
    private String mesaage;
    private String error;
    private String path;
}
