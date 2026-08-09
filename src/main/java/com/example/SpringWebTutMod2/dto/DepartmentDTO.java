package com.example.SpringWebTutMod2.dto;

import com.example.SpringWebTutMod2.annotations.DepartmentNameEvenLengthValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    @NotBlank(message = "Department name can not be null")
    @DepartmentNameEvenLengthValidation
    private String departmentName;
    @AssertTrue(message = "Department should be active")
    private boolean isActive;
    @FutureOrPresent(message = "Creation date can not be past")
    @NotNull(message = "Creation date is required")
    private LocalDate createdAt;
}
