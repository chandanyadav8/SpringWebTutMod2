package com.example.SpringWebTutMod2.controllers;

import com.example.SpringWebTutMod2.dto.DepartmentDTO;
import com.example.SpringWebTutMod2.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    final DepartmentService departmentService;
    DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>>getAllDepartment()
    {
        List<DepartmentDTO> departmentDTO=departmentService.getAllDepartment();
        return new ResponseEntity<>(departmentDTO,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO>getDepartmentById(@PathVariable(name="id")  Long departmentId)
    {
        DepartmentDTO departmentDTO=departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(departmentDTO,HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO>createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO)
    {
        DepartmentDTO departmentDTO1=departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(departmentDTO1, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteDepartment(@PathVariable Long id)
    {
        String isDeleted=departmentService.deleteDepartment(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
