package com.ramesh.week2.mvc.controller;

import com.ramesh.week2.mvc.dto.EmployeeDto;
import com.ramesh.week2.mvc.entities.EmployeeEntity;
import com.ramesh.week2.mvc.exception.ResourceNotFoundException;
import com.ramesh.week2.mvc.repositories.EmployeeRepository;
import com.ramesh.week2.mvc.services.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
       return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Element not found with the id : "+id));
    }



    @GetMapping
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping("/add")
    public EmployeeDto addEmployee(@RequestBody @Valid EmployeeDto inputDto){
            return employeeService.addEmployee(inputDto);
    }

    @PatchMapping("{employeeId}")
    public EmployeeDto partialUpdate(@PathVariable Long employeeId, @RequestBody Map<String,Object>updates){
        return employeeService.partialUpdate(employeeId,updates);
    }


}
