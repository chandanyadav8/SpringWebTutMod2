package com.example.SpringWebTutMod2.services;

import com.example.SpringWebTutMod2.dto.DepartmentDTO;
import com.example.SpringWebTutMod2.entities.DepartmentEntity;
import com.example.SpringWebTutMod2.exceptions.ResourceNotFoundException;
import com.example.SpringWebTutMod2.repositries.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DepartmentRepository departmentRepository;
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity= modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentRepository.save(departmentEntity);
        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    public DepartmentDTO getDepartmentById(Long departmentId) {
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
        if (!departmentEntity.isPresent())
            throw new ResourceNotFoundException("Department not present with id: "+departmentId);
        return modelMapper.map(departmentEntity, DepartmentDTO.class);

    }

    public List<DepartmentDTO> getAllDepartment() {

        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();

        if (departmentEntities.isEmpty()) {
            throw new ResourceNotFoundException("No Department present");
        }

        return departmentEntities.stream()
                .map(entity -> modelMapper.map(entity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public String deleteDepartment(Long id) {
        isDepartmentExist(id);
        departmentRepository.deleteById(id);
        return "Department deleted with id: "+id;
    }
    public void isDepartmentExist(Long id)
    {
        boolean isExist=departmentRepository.findById(id).isPresent();
        if(!isExist)
            throw new ResourceNotFoundException("Department with id: "+id +"Does not exist");
    }
}
