package com.example.SpringWebTutMod2.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Builder
@Data
public class ApiError {
    private String message;
    private HttpStatus status;
}
