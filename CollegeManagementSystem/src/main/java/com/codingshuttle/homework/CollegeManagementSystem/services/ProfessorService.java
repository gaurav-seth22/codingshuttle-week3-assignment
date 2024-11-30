package com.codingshuttle.homework.CollegeManagementSystem.services;

import com.codingshuttle.homework.CollegeManagementSystem.dto.AdmissionRecordDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.ProfessorDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.StudentDTO;
import com.codingshuttle.homework.CollegeManagementSystem.dto.SubjectDTO;
import com.codingshuttle.homework.CollegeManagementSystem.entities.AdmissionRecordEntity;
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
public class ProfessorService {

    private final ModelMapper modelMapper;

    private final ProfessorRepository professorRepository;

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public ProfessorService(ModelMapper modelMapper, ProfessorRepository professorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.modelMapper = modelMapper;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }


    public List<ProfessorDTO> findAllProfessors() {

        return professorRepository.findAll()
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, ProfessorDTO.class))
                .collect(Collectors.toList());
    }

    public ProfessorDTO getProfessorById(Long professorId) {
        existsProfessorById(professorId);
        return modelMapper.map(professorRepository.findById(professorId).get(), ProfessorDTO.class);

    }

    public ProfessorDTO updateProfessor(Long professorId, ProfessorDTO professorDTO) {
        existsProfessorById(professorId);
        ProfessorEntity upateToBeEntity = modelMapper.map(professorDTO,ProfessorEntity.class);
        upateToBeEntity.setId(professorId);
        return modelMapper.map(professorRepository.save(upateToBeEntity), ProfessorDTO.class);


    }

    private void existsProfessorById(Long id){
        boolean exists = professorRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Professor not found with id: "+id);
    }


    public ProfessorDTO createNewProfessor(ProfessorDTO professorDTO) {
        ProfessorEntity saveToBeEntity = modelMapper.map(professorDTO,ProfessorEntity.class);
        return modelMapper.map(professorRepository.save(saveToBeEntity),ProfessorDTO.class);
    }

    public void deleteProfessor(Long professorId) {
        existsProfessorById(professorId);
        professorRepository.deleteById(professorId);
    }

    public List<StudentDTO> findStudents(Long id) {
        existsProfessorById(id);

        ProfessorEntity professorEntity = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found with ID: " + id));

        List<StudentEntity> studentEntities = professorEntity.getStudents();

        return studentEntities.stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDTO.class))
                .toList();


    }


    public List<SubjectDTO> findSubjects(Long id) {
        existsProfessorById(id);

        ProfessorEntity professorEntity = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found with ID: " + id));

        List<SubjectEntity> subjectEntities = professorEntity.getSubjects();

        return subjectEntities.stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDTO.class))
                .toList();

    }

    public boolean assignSubjectToProfessor(Long professorId, Long subjectId) {

        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found with ID: " + professorId));


        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));


        subject.setProfessor(professor);


        subjectRepository.save(subject);

        return true;

    }

    public ProfessorDTO assignStudentToProfessor(Long professorId, Long studentId) {

        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("professor not found by Id: " + professorId));


        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found by Id: " + studentId));


        if (!professor.getStudents().contains(student)) {
            professor.getStudents().add(student);
        }

        // Step 4: Save the updated professor entity (assuming professorRepository is autowired)
        professorRepository.save(professor);

        // Step 5: Convert the updated professor entity to DTO
        ProfessorDTO professorDto = modelMapper.map(professor, ProfessorDTO.class);

        return professorDto;
    }
}
