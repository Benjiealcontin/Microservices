package com.service.StudentService.Repository;

import com.service.StudentService.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    StudentEntity findByid(Long id);
}
