package com.patientmanagement.patientservice.dto;

import com.patientmanagement.patientservice.dto.validators.CreatePatientValidatorGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Date of birth is mandatory")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidatorGroup.class, message = "Registered date is mandatory")
    private String registeredDate;
}
