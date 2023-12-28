package com.microservice.employeeservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.employeeservice.dto.APIResponseDto;
import com.microservice.employeeservice.dto.DepartmentDto;
import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.dto.OrganizationDto;
import com.microservice.employeeservice.entity.Employee;
import com.microservice.employeeservice.exception.ResourceNotFoundException;
import com.microservice.employeeservice.mapper.AutoEmployeeMapper;
import com.microservice.employeeservice.repository.EmployeeRepository;
import com.microservice.employeeservice.service.APIClient;
import com.microservice.employeeservice.service.EmployeeService;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
// @AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private APIClient apiClient;

    public static final String ORGANIZATION_URL = "http://localhost:8083/api/organizations/";

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

    // @CircuitBreaker(name = "${spring.application.name}", fallbackMethod =
    // "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        log.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId,
                        "employee_id_not_found".toUpperCase()));

        // ResponseEntity<DepartmentDto> responseEntity = restTemplate
        // .getForEntity(URL.concat(employee.getDepartmentCode()), DepartmentDto.class);

        // DepartmentDto departmentDto = responseEntity.getBody();

        OrganizationDto organizationDto = webClient.get().uri(ORGANIZATION_URL.concat(employee.getOrganizationCode())).retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        // EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
        // employee.getFirstName(), employee.getLastName(),
        // employee.getEmail());

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.convertEmployeeToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        log.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", employeeId,
                        "employee_id_not_found".toUpperCase()));

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationCode("XYZ_ORG");
        organizationDto.setOrganizationName("XYZ");
        organizationDto.setOrganizationDescription("XYZ Organiation Description");

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Reasearch and Development Department");

        EmployeeDto employeeDto = AutoEmployeeMapper.MAPPER.convertEmployeeToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

        return apiResponseDto;

    }
}
