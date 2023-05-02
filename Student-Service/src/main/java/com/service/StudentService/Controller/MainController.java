package com.service.StudentService.Controller;

import com.service.StudentService.Request.StudentRequest;
import com.service.StudentService.Response.StudentResponse;
import com.service.StudentService.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
public class MainController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<?> createEmployee(@RequestBody StudentRequest studentRequest) {
        try {
            return new ResponseEntity<>(studentService.AddStudent(studentRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //FindAll Employee
    @GetMapping("/student")
    public ResponseEntity<?> student() {
        try {
            return new ResponseEntity<>(studentService.getAllEmployee(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    //Find Employee by Id
    @GetMapping("{id}")
    public ResponseEntity<StudentResponse> getUser(@PathVariable("id") Long userId){
        StudentResponse studentResponse = studentService.getUser2(userId);
        return ResponseEntity.ok(studentResponse);
    }
}
