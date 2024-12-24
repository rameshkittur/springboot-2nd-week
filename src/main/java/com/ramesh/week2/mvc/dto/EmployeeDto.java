package com.ramesh.week2.mvc.dto;

import com.ramesh.week2.mvc.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {

    private Long id;
    private String name;

    @NotEmpty(message = "email is required")
    private String email;

    @Positive(message = "Age must be positive")
    private Integer age;

    @NonNull
    private LocalDate dateOfJoining;

    private boolean isActive;

    @EmployeeRoleValidation
    private String role;
}
