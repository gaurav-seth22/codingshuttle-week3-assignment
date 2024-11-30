package com.codingshuttle.homework.CollegeManagementSystem.services;

import com.codingshuttle.homework.CollegeManagementSystem.dto.AdmissionRecordDTO;
import com.codingshuttle.homework.CollegeManagementSystem.entities.AdmissionRecordEntity;
import com.codingshuttle.homework.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.homework.CollegeManagementSystem.repositories.AdmissionRecordRepository;
import com.codingshuttle.homework.CollegeManagementSystem.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionRecordService {

    private final ModelMapper modelMapper;
    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionRecordService(ModelMapper modelMapper, AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
        this.modelMapper = modelMapper;
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
    }

    public AdmissionRecordDTO createNewAdmission(AdmissionRecordDTO admissionRecordDTO) {
        AdmissionRecordEntity saveToBeEntity = modelMapper.map(admissionRecordDTO,AdmissionRecordEntity.class);
        return modelMapper.map(admissionRecordRepository.save(saveToBeEntity),AdmissionRecordDTO.class);
    }


    public AdmissionRecordDTO getAdmissionRecordById(Long id) {
        existsAdmissionRecordById(id);
        return admissionRecordRepository.findById(id).map(admissionRecordEntity -> modelMapper.map(admissionRecordEntity,AdmissionRecordDTO.class)).get();
    }


    private void existsAdmissionRecordById(Long id){
        boolean exists = admissionRecordRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Admission record not found with id: "+id);
    }

    public AdmissionRecordDTO updateAdmission(Long id, AdmissionRecordDTO admissionRecord) {
        existsAdmissionRecordById(id);
        AdmissionRecordEntity upateToBeEntity = modelMapper.map(admissionRecord,AdmissionRecordEntity.class);
        upateToBeEntity.setId(id);
        return modelMapper.map(admissionRecordRepository.save(upateToBeEntity),AdmissionRecordDTO.class);

    }

    public List<AdmissionRecordDTO> getAllAdmissions() {
        List<AdmissionRecordEntity> admissionRecordEntities = admissionRecordRepository.findAll();
        return admissionRecordEntities
                .stream()
                .map(admissionRecordEntity -> modelMapper.map(admissionRecordEntity,AdmissionRecordDTO.class))
                .collect(Collectors.toList());
    }


    public void deleteAdmission(Long id) {
        existsAdmissionRecordById(id);
        admissionRecordRepository.deleteById(id);
    }


    public AdmissionRecordDTO getAdmissionRecordByStudentId(Long id) {
        AdmissionRecordEntity admissionRecordEntity = admissionRecordRepository.findByStudentId(id)
                .orElseThrow(() -> new RuntimeException("No admission record found for student ID: " + id));

        return modelMapper.map(admissionRecordEntity, AdmissionRecordDTO.class);
    }
}