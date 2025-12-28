package com.example.SpringWebTutMod2.repositries;

import com.example.SpringWebTutMod2.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<EmployeeEntity,Long> {
}
