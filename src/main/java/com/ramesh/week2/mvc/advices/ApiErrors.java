package com.ramesh.week2.mvc.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiErrors {
    private HttpStatus status;
    private String message;
    List<String>subErrors;
}
