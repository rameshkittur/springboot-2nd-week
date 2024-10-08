package com.ramesh.week2.mvc.services;

import com.ramesh.week2.mvc.dto.EmployeeDto;

import java.util.Map;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeDto inputDto);

    EmployeeDto partialUpdate(Long employeeId, Map<String, Object> updates);

}
