package com.service.StudentService.Service;

import com.service.DepartmentService.Entity.DepartmentEntity;
import com.service.DepartmentService.Response.DepartmentResponse;
import com.service.StudentService.Entity.StudentEntity;
import com.service.StudentService.Repository.StudentRepository;
import com.service.StudentService.Request.StudentRequest;
import com.service.StudentService.Response.MessageResponse;
import com.service.StudentService.Response.StudentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    //Add Student
    public MessageResponse AddStudent(StudentRequest studentRequest){
        StudentEntity studentEntity = StudentEntity.builder()
                .email(studentRequest.getEmail())
                .departmentId(studentRequest.getDepartmentId())
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .build();
        studentRepository.save(studentEntity);
        return new MessageResponse("Student Successfully Added!");
    }

    //Get all Student
    public List<StudentResponse> getAllEmployee() {
        try {
            List<StudentEntity> studentEntities = studentRepository.findAll();
            List<StudentResponse> studentResponses = new ArrayList<>();
            for (StudentEntity studentEntity : studentEntities) {
                StudentResponse studentResponse = modelMapper.map(studentEntity, StudentResponse.class);
                DepartmentResponse departmentResponse = webClientBuilder.build().get()
                        .uri("http://Department-Service/api/department/department")
                        .retrieve()
                        .bodyToMono(DepartmentResponse.class)
                        .block();
                studentResponse.setDepartments(departmentResponse.getDepartments());
                studentResponses.add(studentResponse);
            }
            return studentResponses;

        } catch (Exception e) {
            // Log the error or do something else with it
            throw new RuntimeException("An error occurred while fetching Employee: " + e.getMessage(), e);
        }
    }


    //Microservices with department
    //Get Request
    public StudentResponse getUser2(Long userId) {
        StudentEntity studentEntity = studentRepository.findByid(userId);
        StudentResponse studentResponse = modelMapper.map(studentEntity, StudentResponse.class);

        DepartmentEntity departmentEntity = webClientBuilder.build().get()
                .uri("http://Department-Service/api/department/" + studentEntity.getDepartmentId())
                .retrieve()
                .bodyToMono(DepartmentEntity.class)
                .block();

        List<DepartmentEntity> departments = new ArrayList<>();
        departments.add(departmentEntity);
        studentResponse.setDepartments(departments);

        return studentResponse;
    }

}
