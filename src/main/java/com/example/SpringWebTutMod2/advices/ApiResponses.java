package com.example.SpringWebTutMod2.advices;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ApiResponses<T> {
    private LocalDateTime timeStamp;
    private T data;
    private ApiError apiError;
    ApiResponses(){
        timeStamp= LocalDateTime.now();
    }
    ApiResponses (T data)
    {
        this();
        this.data=data;
    }
    ApiResponses(ApiError apiError)
    {
        this();
        this.apiError=apiError;
    }

}
