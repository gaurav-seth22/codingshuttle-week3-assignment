package com.codingshuttle.homework.CollegeManagementSystem.controllers;

import com.codingshuttle.homework.CollegeManagementSystem.dto.AdmissionRecordDTO;
import com.codingshuttle.homework.CollegeManagementSystem.services.AdmissionRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/admissions")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;


    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping
    public ResponseEntity<List<AdmissionRecordDTO>> getAllAdmissions(){
        return ResponseEntity.ok(admissionRecordService.getAllAdmissions());
    }

    @PostMapping
    public ResponseEntity<AdmissionRecordDTO> createNewAdmission(@RequestBody AdmissionRecordDTO admissionRecordDTO){
        AdmissionRecordDTO admission= admissionRecordService.createNewAdmission(admissionRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(admission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionRecordDTO> getAdmissionRecordById(@PathVariable Long id){
        return ResponseEntity.ok(admissionRecordService.getAdmissionRecordById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionRecordDTO> updateAdmission(@PathVariable Long id,
                                                                 @RequestBody AdmissionRecordDTO admissionRecord) {
        AdmissionRecordDTO admissionRecordDTO = admissionRecordService.updateAdmission(id, admissionRecord);
        return ResponseEntity.ok(admissionRecordDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmission(@PathVariable Long id){
        admissionRecordService.deleteAdmission(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/student/{id}")
    public ResponseEntity<AdmissionRecordDTO>  getAdmissionRecordByStudentId(@PathVariable Long id){
        return ResponseEntity.ok(admissionRecordService.getAdmissionRecordByStudentId(id));
    }



}

