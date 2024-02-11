package com.microservice.departmentservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.departmentservice.entity.Department;
import com.microservice.departmentservice.exception.ResourceNotFoundException;
import com.microservice.departmentservice.mapper.AutoDepartmentMapper;
import com.microservice.departmentservice.repository.DepartmentRepository;
import com.microservice.departmentservice.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

        @Autowired
        private DepartmentRepository departmentRepository;

        @Override
        public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

                // log.info("----- Save Department -----");

                // convert departmentDto to departmentEntity

                // log.info("convert departmentDto to departmentEntity");

                // Department department = new Department(departmentDto.getId(),
                // departmentDto.getDepartmentName(),
                // departmentDto.getDepartmentDescription(), departmentDto.getDepartmentCode());

                Department department = AutoDepartmentMapper.MAPPER.convertDepartmentDtoToDepartment(departmentDto);

                // log.info("save department data into department table");

                Department savedDepartment = departmentRepository.save(department);

                // convert departmentEntity to departmentDto

                // log.info("convert departmentEntity to departmentDto");

                // DepartmentDto savedDepartmentDto = new DepartmentDto(savedDepartment.getId(),
                // savedDepartment.getDepartmentName(),
                // savedDepartment.getDepartmentDescription(),
                // savedDepartment.getDepartmentCode());

                DepartmentDto savedDepartmentDto = AutoDepartmentMapper.MAPPER
                                .convertDapartmentToDepartmentDto(savedDepartment);

                // log.info("----- Save Department -----");

                return savedDepartmentDto;
        }

        @Override
        public DepartmentDto getDepartmentByCode(String departmentCode) {

                Department department = departmentRepository.findByDepartmentCode(departmentCode)
                                .orElseThrow(() -> new ResourceNotFoundException("department", "departmentCode",
                                                departmentCode, "department_code_not_found".toUpperCase()));

                // DepartmentDto departmentDto = new DepartmentDto(department.getId(),
                // department.getDepartmentName(),
                // department.getDepartmentDescription(), department.getDepartmentCode());

                DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.convertDapartmentToDepartmentDto(department);

                return departmentDto;
        }

}
