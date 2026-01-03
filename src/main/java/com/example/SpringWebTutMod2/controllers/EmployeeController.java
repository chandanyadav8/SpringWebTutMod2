package com.example.SpringWebTutMod2.controllers;

import com.example.SpringWebTutMod2.dto.EmployeeDTO;
import com.example.SpringWebTutMod2.services.EmployeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
//@RequestMapping(path="/employees")
//or
@RequestMapping("/employees")
public class EmployeeController {
//    @GetMapping("/employees/{employeeId}")
//    We can just remove /employees from getmapping we can define it on top of class

//    @GetMapping("/{employeeId}")
//    public String getEmployeeById(@PathVariable Long employeeId)
//    {
//        return "Hi my id is " +employeeId;
//    }

    private final EmployeService employeService;
    EmployeeController(EmployeService employeService)
    {
        this.employeService=employeService;
    }
    //    another way
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        EmployeeDTO employeeDTO= employeService.getEmployeeById(id);
        if (employeeDTO==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);

    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false) Integer age)
    {
        List<EmployeeDTO>employeeDTOList= employeService.getAllEmployee();
        return ResponseEntity.ok(employeeDTOList);
    }


    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employee)
    {
        employeService.createEmployee(employee);
        return new ResponseEntity<>(employee.getName()+" employee is created",HttpStatus.CREATED);
    }
    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,
                                     @PathVariable(name = "employeeId") Long id)
    {
        EmployeeDTO employeeDTO1= employeService.updateEmployeeById(id,employeeDTO);
        return ResponseEntity.ok(employeeDTO1);

    }
    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeData(@RequestBody Map<String, Object> employee,
                                                 @PathVariable Long employeeId)
    {
        EmployeeDTO employeeDTO=employeService.updatePartialEmployeeData(employeeId,employee);
        if (employeeDTO==null)
            return ResponseEntity.notFound().build();
                return ResponseEntity.ok(employeeDTO);
    }
    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId)
    {
        boolean isDeleted=employeService.deleteEmployeeById(employeeId);
        if(!isDeleted)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(isDeleted);
    }
}
