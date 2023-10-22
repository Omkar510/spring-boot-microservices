package com.microservice.employeeservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.employeeservice.dto.APIResponseDto;
import com.microservice.employeeservice.dto.DepartmentDto;
import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.entity.Employee;
import com.microservice.employeeservice.exception.ResourceNotFoundException;
import com.microservice.employeeservice.mapper.AutoEmployeeMapper;
import com.microservice.employeeservice.repository.EmployeeRepository;
import com.microservice.employeeservice.service.APIClient;
import com.microservice.employeeservice.service.EmployeeService;

@Service
//@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;

    // private WebClient webClient;

	@Autowired
    private APIClient apiClient;

    public static final String URL = "http://localhost:8080/api/departments/";

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        // convert EmployeeDto into Employee entity

        // Employee employee = new Employee(employeeDto.getId(),
        // employeeDto.getFirstName(), employeeDto.getLastName(),
        // employeeDto.getEmail());

        Employee employee = AutoEmployeeMapper.MAPPER.convertEmployeeDtoToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        // convert Employee entity into EmployeeDto

        // EmployeeDto savedEmployeeDto = new EmployeeDto(savedEmployee.getId(),
        // savedEmployee.getFirstName(),
        // savedEmployee.getLastName(), savedEmployee.getEmail());

        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.convertEmployeeToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId,
                        "employee_id_not_found".toUpperCase()));

        // ResponseEntity<DepartmentDto> responseEntity = restTemplate
        // .getForEntity(URL.concat(employee.getDepartmentCode()), DepartmentDto.class);

        // DepartmentDto departmentDto = responseEntity.getBody();

        // DepartmentDto departmentDto =
        // webClient.get().uri(URL.concat(employee.getDepartmentCode())).retrieve().bodyToMono(DepartmentDto.class)
        // .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        // EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
        // employee.getFirstName(), employee.getLastName(),
        // employee.getEmail());

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.convertEmployeeToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto);

        return apiResponseDto;
    }

}
