package com.service.DepartmentService.Service;

import com.service.DepartmentService.Entity.DepartmentEntity;
import com.service.DepartmentService.Repository.DepartmentRepository;
import com.service.DepartmentService.Request.DepartmentRequest;
import com.service.DepartmentService.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    //Add department
    public MessageResponse AddDepartment(DepartmentRequest departmentRequest){
            DepartmentEntity departmentEntity = DepartmentEntity.builder()
                    .departmentCode(departmentRequest.getDepartmentCode())
                    .departmentAddress(departmentRequest.getDepartmentAddress())
                    .departmentName(departmentRequest.getDepartmentName())
                    .build();
            departmentRepository.save(departmentEntity);

        return new MessageResponse("Department created successfully!");
    }

    //Find all
    public List<DepartmentEntity> getDepartmentList(){
        try {
            return departmentRepository.findAll();
        } catch (Exception e) {
            // Log the error or do something else with it
            throw new RuntimeException("An error occurred while fetching Department: " + e.getMessage(), e);
        }
    }

    //Find department by ID
    public DepartmentEntity getDepartmentById(Long id) {
        try {
            return departmentRepository.findByid(id);
        } catch (Exception e) {
            // Log the error or do something else with it
            throw new RuntimeException("An error occurred while fetching Department: " + e.getMessage(),
                    e);
        }

    }
}
