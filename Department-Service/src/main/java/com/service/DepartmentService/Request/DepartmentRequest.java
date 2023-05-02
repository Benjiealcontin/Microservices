package com.service.DepartmentService.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
    private String departmentAddress;
    private String departmentCode;
    private String departmentName;
}
