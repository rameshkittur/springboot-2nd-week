package com.ramesh.week2.mvc.services;

import com.ramesh.week2.mvc.dto.EmployeeDto;
import com.ramesh.week2.mvc.entities.EmployeeEntity;
import com.ramesh.week2.mvc.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto addEmployee(EmployeeDto inputDto) {
        EmployeeEntity saveEntity = modelMapper.map(inputDto,EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(saveEntity);
        return modelMapper.map(savedEntity,EmployeeDto.class);
    }

    @Override
    public EmployeeDto partialUpdate(Long employeeId, Map<String, Object> updates) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Not found any record"));
        updates.forEach((field,value)->{
            Field fieldToBeUpdate = ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdate,employee,value);
        });
        return modelMapper.map(employee,EmployeeDto.class);
    }
}
