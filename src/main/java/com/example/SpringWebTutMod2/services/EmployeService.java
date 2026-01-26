package com.example.SpringWebTutMod2.services;

import com.example.SpringWebTutMod2.dto.EmployeeDTO;
import com.example.SpringWebTutMod2.entities.EmployeeEntity;
import com.example.SpringWebTutMod2.exceptions.ResourceNotFoundException;
import com.example.SpringWebTutMod2.repositries.EmployeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeService {
    private  final EmployeRepository employeRepository;
    private  final ModelMapper modelMapper;

    public EmployeService(EmployeRepository employeRepository,ModelMapper modelMapper) {
        this.employeRepository = employeRepository;
        this.modelMapper=modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity=employeRepository.findById(id).orElse(null);
        if(employeeEntity==null)
            return null;
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

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        EmployeeEntity entity =
                modelMapper.map(employee, EmployeeEntity.class);
        EmployeeEntity savedEntity =
                employeRepository.save(entity);

         return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(id);
        employeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updatePartialEmployeeData(Long employeeId, Map<String, Object> employee) {
        boolean isExist=isExistEmployee(employeeId);
        EmployeeEntity employeeEntity=employeRepository.findById(employeeId).orElse(null);
       for(Map.Entry<String,Object>entry:employee.entrySet())
       {

            Field fieldToBeUpdated=ReflectionUtils.getRequiredField(EmployeeEntity.class,entry.getKey());
           fieldToBeUpdated.setAccessible(true);
           ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,entry.getValue());
       }
       employeeEntity.setId(employeeId);
       employeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);

    }

    public boolean deleteEmployeeById(Long id) {
        boolean isExist = isExistEmployee(id);
            employeRepository.deleteById(id);
            return isExist;

    }
    public boolean isExistEmployee(Long id)
    {
        boolean isExist = employeRepository.existsById(id);
        if (!isExist){
            throw new ResourceNotFoundException("employee does not exist");
        }
        return isExist;
    }
}
