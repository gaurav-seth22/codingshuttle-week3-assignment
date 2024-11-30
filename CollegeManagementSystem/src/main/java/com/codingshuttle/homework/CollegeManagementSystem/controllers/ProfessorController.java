package com.codingshuttle.homework.CollegeManagementSystem.controllers;


import com.codingshuttle.homework.CollegeManagementSystem.dto.ProfessorDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.StudentDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.SubjectDTO;
import com.codingshuttle.homework.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.homework.CollegeManagementSystem.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAllProfessors(){
        List<ProfessorDTO> professorDTOS=professorService.findAllProfessors();
        return ResponseEntity.ok(professorDTOS);
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable Long professorId){
        ProfessorDTO professorDTO =professorService.getProfessorById(professorId);
        return ResponseEntity.ok(professorDTO);
    }

    @PutMapping("/{professorId}")
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable Long professorId,@RequestBody ProfessorDTO professorDTO ){

        return ResponseEntity.ok(professorService.updateProfessor(professorId,professorDTO));

    }


    @PostMapping
    public ResponseEntity<ProfessorDTO> createNewProfessor(@RequestBody ProfessorDTO professorDTO){

        ProfessorDTO professor= professorService.createNewProfessor(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long professorId){
        professorService.deleteProfessor(professorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getProfessorStudents(@PathVariable Long id){
        return ResponseEntity.ok(professorService.findStudents(id));
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<SubjectDTO>> getProfessorSubjects(@PathVariable Long id){
        return ResponseEntity.ok(professorService.findSubjects(id));
    }

    @PutMapping("/{professorId}/assignsubject/{subjectId}")
    public ResponseEntity<Boolean> assignSubjectToProfessor(@PathVariable Long professorId,@PathVariable Long subjectId){
        boolean isSubAssigned=professorService.assignSubjectToProfessor(professorId,subjectId);
        return ResponseEntity.ok(isSubAssigned);
    }

    @PutMapping("/{professorId}/assignStudent/{studentId}")
    public ResponseEntity<ProfessorDTO> assignStudentToProfessor(@PathVariable Long professorId,@PathVariable Long studentId){
        return ResponseEntity.ok(professorService.assignStudentToProfessor(professorId,studentId));
    }

}

