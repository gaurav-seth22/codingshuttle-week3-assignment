package com.codingshuttle.homework.CollegeManagementSystem.controllers;

import com.codingshuttle.homework.CollegeManagementSystem.dto.ProfessorDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.StudentDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.SubjectDTO;
import com.codingshuttle.homework.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.SubjectEntity;
import com.codingshuttle.homework.CollegeManagementSystem.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping()
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        StudentDTO studentDTO=studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO student=studentService.createNewStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId,@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentService.updateStudent(studentId,studentDTO));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/professors")
    public ResponseEntity<List<ProfessorDTO>> getProfessorsToStudent(@PathVariable Long studentId){

        return  ResponseEntity.ok(studentService.findProfessorsByStudentId(studentId));
    }

    @GetMapping("/{studentId}/subjects")
    public ResponseEntity<List<SubjectDTO>> getSubjectsToStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.findSubjectsByStudentId(studentId));
    }

    @PutMapping("/{studentId}/assignsubject/{subjectId}")
    public ResponseEntity<Boolean> assignSubjectToStudent(@PathVariable Long studentId, @PathVariable Long subjectId){
        boolean isSubAssigned=studentService.assignSubjectToStudent(studentId,subjectId);
        return ResponseEntity.ok(isSubAssigned);
    }


}

