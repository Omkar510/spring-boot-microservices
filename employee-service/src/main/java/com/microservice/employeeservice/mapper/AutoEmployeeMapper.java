package com.microservice.employeeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.entity.Employee;

@Mapper
public interface AutoEmployeeMapper {
    
    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);

    Employee convertEmployeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto convertEmployeeToEmployeeDto(Employee employee);
}
