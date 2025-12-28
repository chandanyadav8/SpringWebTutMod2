package com.example.SpringWebTutMod2.services;

import com.example.SpringWebTutMod2.dto.EmployeeDTO;
import com.example.SpringWebTutMod2.entities.EmployeeEntity;
import com.example.SpringWebTutMod2.repositries.EmployeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeService {
    private  final EmployeRepository employeRepository;
    private  final ModelMapper modelMapper;

    public EmployeService(EmployeRepository employeRepository,ModelMapper modelMapper) {
        this.employeRepository = employeRepository;
        this.modelMapper=modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity=employeRepository.getById(id);
        EmployeeDTO employeeDTO=modelMapper.map(employeeEntity,EmployeeDTO.class);
        return  employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity>employeeEntities=employeRepository.findAll();
        List<EmployeeDTO>employeeDTOS=new ArrayList<>();
        for (var empent: employeeEntities)
          employeeDTOS.add(modelMapper.map(empent,EmployeeDTO.class));
        return employeeDTOS;
    }

    public void createEmployee(EmployeeDTO employee) {
        employeRepository.save(modelMapper.map(employee,EmployeeEntity.class));
    }
}
