package com.service.DepartmentService.Controller;

import com.service.DepartmentService.Entity.DepartmentEntity;
import com.service.DepartmentService.Request.DepartmentRequest;
import com.service.DepartmentService.Response.DepartmentResponse;
import com.service.DepartmentService.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class MainController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<?> createdepartment(@RequestBody DepartmentRequest departmentRequest) {
        try {
            return new ResponseEntity<>(departmentService.AddDepartment(departmentRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/department")
    public ResponseEntity<DepartmentResponse> departmentList() {
        try {
            List<DepartmentEntity> departments = departmentService.getDepartmentList();
            DepartmentResponse response = new DepartmentResponse();
            response.setDepartments(departments);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentEntity department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }
}
