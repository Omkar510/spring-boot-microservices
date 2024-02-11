package com.microservice.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.employeeservice.dto.APIResponseDto;
import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Employee Service - Employee Controller",
    description = "Employee Controller Exposes REST APIs for Employee Service"
)
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    
	@Autowired
    private EmployeeService employeeService;

    @Operation(
        summary = "Save Employee REST API",
        description = "Save Employee REST API is used to save employee object into the database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    // Build Save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<EmployeeDto>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get Employee REST API",
        description = "Get Employee REST API is used to get employee object from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    // Build Get Employee REST API
    @GetMapping("{employee-id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("employee-id") Long employeeId) {
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<APIResponseDto>(apiResponseDto, HttpStatus.OK);
    }
}
