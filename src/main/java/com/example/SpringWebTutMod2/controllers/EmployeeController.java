package com.example.SpringWebTutMod2.controllers;

import com.example.SpringWebTutMod2.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    //    another way
    @GetMapping("/{employeeId}")
    public String getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return "Hi my id is " + id;
    }

    @GetMapping
    public String getAllEmployee(@RequestParam(required = false) Integer age)
    {
        return "hi my age is "+age;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employee)
    {
        employee.setId(100L);
        return employee;
    }
}
