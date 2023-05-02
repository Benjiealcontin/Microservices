package com.service.StudentService.Response;

import com.service.DepartmentService.Entity.DepartmentEntity;
import com.service.DepartmentService.Response.DepartmentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<DepartmentEntity> departments;
}
