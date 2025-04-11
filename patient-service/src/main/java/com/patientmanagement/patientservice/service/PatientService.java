package com.patientmanagement.patientservice.service;

import com.patientmanagement.patientservice.dto.PatientRequestDTO;
import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.exception.EmailAlreadyExistException;
import com.patientmanagement.patientservice.exception.PatientNotFoundException;
import com.patientmanagement.patientservice.mapper.PatientMapper;
import com.patientmanagement.patientservice.model.Patient;
import com.patientmanagement.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    //Create Patient
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistException("A Patient with this email " + "already exist" + patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO)
        );
        return PatientMapper.toDTO(newPatient);
    }

    //Update existing patient
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient is not found with this id:" + id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistException("A Patient with this email " + "already exist" + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    //Delete Patient
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
