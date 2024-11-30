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
public class StudentService {

    private final ModelMapper modelMapper;

    private final StudentRepository studentRepository;

    private final ProfessorRepository professorRepository;

    private final SubjectRepository subjectRepository;



    public StudentService(ModelMapper modelMapper, StudentRepository studentRepository, ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }









    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }









    public List<SubjectDTO> findSubjectsByStudentId(Long studentId) {
        existsStudentById(studentId);

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));


        List<SubjectEntity> subjects = student.getSubjects();


        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());

    }
    public List<ProfessorDTO> findProfessorsByStudentId(Long studentId) {
        existsStudentById(studentId);
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));


        List<ProfessorEntity> professors = student.getProfessors();


        return professors.stream()
                .map(professor -> modelMapper.map(professor, ProfessorDTO.class))
                .collect(Collectors.toList());

    }
    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {

        existsStudentById(studentId);
        StudentEntity upateToBeEntity = modelMapper.map(studentDTO,StudentEntity.class);
        upateToBeEntity.setId(studentId);
        return modelMapper.map(studentRepository.save(upateToBeEntity), StudentDTO.class);
    }

    public StudentDTO createNewStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        return modelMapper.map(savedStudentEntity, StudentDTO.class);
    }


    public List<StudentDTO> getAllStudents() {
            return studentRepository.findAll()
                    .stream()
                    .map(studentEntity -> modelMapper.map(studentEntity,StudentDTO.class))
                    .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long studentId) {
        existsStudentById(studentId);
        return modelMapper.map(studentRepository.findById(studentId).get(),StudentDTO.class);

    }

    private void existsStudentById(Long id){
        boolean isExist = studentRepository.existsById(id);
        if(!isExist) throw new ResourceNotFoundException("Student does not exists by Id:"+id);
    }


    public boolean assignSubjectToStudent(Long studentId, Long subjectId) {

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));


        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));


        if (!student.getSubjects().contains(subject)) {
            student.getSubjects().add(subject);

            studentRepository.save(student);

            return true;
        }

        return false;

    }
}


