package com.microservice.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
    description = "DepartmentDto Model Information"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    
    private Long id;

    @Schema(
        description = "Department Name"
    )
    private String departmentName;

    @Schema(
        description = "Department Description"
    )
    private String departmentDescription;

    @Schema(
        description = "Department Code (Unqiue)"
    )
    private String departmentCode;
}
