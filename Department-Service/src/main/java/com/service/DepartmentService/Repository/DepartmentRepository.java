package com.service.DepartmentService.Repository;

import com.service.DepartmentService.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    DepartmentEntity findByid(long id);
}
