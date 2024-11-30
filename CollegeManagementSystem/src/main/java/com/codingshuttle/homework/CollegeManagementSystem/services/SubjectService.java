package com.codingshuttle.homework.CollegeManagementSystem.services;


import com.codingshuttle.homework.CollegeManagementSystem.dto.ProfessorDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.StudentDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.SubjectDTO;
import com.codingshuttle.homework.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.SubjectEntity;
import com.codingshuttle.homework.CollegeManagementSystem.exceptions.ResourceNotFoundException;
import com.codingshuttle.homework.CollegeManagementSystem.repositories.ProfessorRepository;
import com.codingshuttle.homework.CollegeManagementSystem.repositories.StudentRepository;
import com.codingshuttle.homework.CollegeManagementSystem.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final ModelMapper modelMapper;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public SubjectService(ModelMapper modelMapper, SubjectRepository subjectRepository, StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    public List<SubjectDTO> findAllSubjects() {

        return subjectRepository.findAll().stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDTO.class))
                .collect(Collectors.toList());
    }


    public SubjectDTO createNewSubject(SubjectDTO subjectDTO) {

        SubjectEntity saveToBeEntity = modelMapper.map(subjectDTO,SubjectEntity.class);
        return modelMapper.map(subjectRepository.save(saveToBeEntity), SubjectDTO.class);
    }

    public SubjectDTO updateSubject(Long subjectId, SubjectDTO subjectDTO) {
        existsSubjectById(subjectId);
        SubjectEntity upateToBeEntity=modelMapper.map(subjectDTO,SubjectEntity.class);
        upateToBeEntity.setId(subjectId);
        return modelMapper.map(subjectRepository.save(upateToBeEntity),SubjectDTO.class);

    }

    public SubjectDTO findSubjectById(Long subjectId) {

        existsSubjectById(subjectId);

        return modelMapper.map(subjectRepository.findById(subjectId).get(), SubjectDTO.class);

    }

    private void existsSubjectById(Long id){
        boolean exists = subjectRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Subject not found with id: "+id);
    }


    public void deleteSubject(Long subjectId) {
        existsSubjectById(subjectId);
        subjectRepository.deleteById(subjectId);
    }

    public List<StudentDTO> getStudentsBySubject(Long subjectId) {
        existsSubjectById(subjectId);
        List<StudentEntity> studentEntities = subjectRepository.findById(subjectId).get().getStudents().stream().toList();
        return studentEntities.stream()
                .map(studentEntity -> modelMapper.map(studentEntity,StudentDTO.class))
                .collect(Collectors.toList());
    }


    public ProfessorDTO getProfessorsBySubject(Long subjectId) {
        existsSubjectById(subjectId);

       return modelMapper.map(subjectRepository.findById(subjectId).get().getProfessor(),ProfessorDTO.class);

    }

    public Boolean assignStudentToSubject(Long subjectId, Long studentId) {

        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        if (subject == null || student == null) {
            return false;
        }

        subject.getStudents().add(student);
        student.getSubjects().add(subject);
        subjectRepository.save(subject);
        studentRepository.save(student);
        return true;
    }

    public Boolean assignProfessorToSubject(Long subjectId, Long professorId) {
        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found with ID: " + professorId));

        if (!professor.equals(subject.getProfessor())) {
            subject.setProfessor(professor);
            subjectRepository.save(subject);
            return true;
        }
     return false;

    }
}
