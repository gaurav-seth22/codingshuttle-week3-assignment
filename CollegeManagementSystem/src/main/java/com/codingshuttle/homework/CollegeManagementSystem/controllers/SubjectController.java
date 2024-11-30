package com.codingshuttle.homework.CollegeManagementSystem.controllers;

import com.codingshuttle.homework.CollegeManagementSystem.dto.ProfessorDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.StudentDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.SubjectDTO;
import com.codingshuttle.homework.CollegeManagementSystem.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.findAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createNewSubject(@RequestBody SubjectDTO subjectDTO){
        SubjectDTO subject = subjectService.createNewSubject(subjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(subject);
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long subjectId){

        SubjectDTO subject = subjectService.findSubjectById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long subjectId,@RequestBody SubjectDTO subjectDTO){
        return ResponseEntity.ok(subjectService.updateSubject(subjectId,subjectDTO));

    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{subjectId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsBySubject(@PathVariable Long subjectId){
        return  ResponseEntity.ok(subjectService.getStudentsBySubject(subjectId));
    }

    @GetMapping("/{subjectId}/professors")
    public ResponseEntity<ProfessorDTO> getProfessorsBySubject(@PathVariable Long subjectId){
        return  ResponseEntity.ok(subjectService.getProfessorsBySubject(subjectId));
    }

    @PutMapping("/{subjectId}/assignstudent/{studentId}")
    public ResponseEntity<Boolean> assignStudentToSubject(@PathVariable Long subjectId
            ,@PathVariable Long studentId)
    {

        return ResponseEntity.ok(subjectService.assignStudentToSubject(subjectId,studentId));
    }

    @PutMapping("/{subjectId}/assignprofessor/{professorId}")
    public ResponseEntity<Boolean> assignProfessorToSubject(@PathVariable Long subjectId,
                                                            @PathVariable Long professorId){

        return ResponseEntity.ok(subjectService.assignProfessorToSubject(subjectId,professorId));
    }

}
