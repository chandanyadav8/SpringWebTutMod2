package com.example.SpringWebTutMod2.controllers;

import com.example.SpringWebTutMod2.dto.EmployeeDTO;
import com.example.SpringWebTutMod2.entities.EmployeeEntity;
import com.example.SpringWebTutMod2.repositries.EmployeRepository;
import com.example.SpringWebTutMod2.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false) Integer age)
    {
        return employeService.getAllEmployee();
    }


    @PostMapping
    public String createEmployee(@RequestBody EmployeeDTO employee)
    {
        employeService.createEmployee(employee);
        return employee.getName()+" employee is created";
    }
}
