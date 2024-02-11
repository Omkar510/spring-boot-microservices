package com.microservice.organizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.organizationservice.dto.OrganizationDto;
import com.microservice.organizationservice.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
    name = "Organization Service - Organization Controller",
    description = "Organization Controller Exposes REST APIs for Organization Service"
)
@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Operation(
        summary = "Save Organization REST API",
        description = "Save Organization REST API is used to save organization object in the database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    // Build Save Organization REST API
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        return new ResponseEntity<OrganizationDto>(organizationService.saveOrganization(organizationDto),
                HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get Organization REST API",
        description = "Get Organization REST API is used to get a single organization object from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    // Build Get Organization By Code
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode) {
        return new ResponseEntity<OrganizationDto>(organizationService.getOrganizationByCode(organizationCode),
                HttpStatus.OK);
    }

}
