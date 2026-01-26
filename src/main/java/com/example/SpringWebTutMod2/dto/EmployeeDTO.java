package com.example.SpringWebTutMod2.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.juli.logging.Log;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Name can not be null or empty")
    @Size(min=2,max = 10, message = "the length of name should be greater than 2 and less than 10")
    private String name;
    @Max(value = 80,message = "age can not be greater than 80")
    @Min(value = 17,message = "age should be greater than 17")
    private Integer age;
    @PastOrPresent(message = "Date of joining cannot be in the future")
    private LocalDate dateOfJoining;
    @NotBlank(message = "email can not be blank")
    @Email(message = "Please enter valid email")
    private String email;
    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;
    @NotBlank
    @Pattern(regexp = "^(USER|ADMIN)$",message = "Role can be only User or  Admin")
    private String role;
    @NotNull(message = "Salary can not be null")
    @Positive(message = "Salary can be negative")
    @Digits(integer = 6,fraction = 2,message = "The salary can be in the form of XXXXXX.YY")
    @DecimalMax(value="100000.99")
    @DecimalMin(value="100.50")
    private Double salary;
}
