package com.service.DepartmentService.Response;

import com.service.DepartmentService.Entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private List<DepartmentEntity> departments;
}
