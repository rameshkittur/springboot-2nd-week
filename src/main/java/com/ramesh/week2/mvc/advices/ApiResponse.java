package com.ramesh.week2.mvc.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiResponse<T>{
    @JsonFormat(pattern = "hh:mm:ss dd/MM/yyyy")
    private LocalDateTime timeStamp;
    private ApiErrors error;
    private T data;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(ApiErrors error) {
        this();
        this.error = error;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }
}
