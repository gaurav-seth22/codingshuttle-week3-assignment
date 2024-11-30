package com.codingshuttle.homework.CollegeManagementSystem.dto;

import com.codingshuttle.homework.CollegeManagementSystem.entities.AdmissionRecordEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.SubjectEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    private String name;

    @JsonIgnore
    private List<ProfessorDTO> professors;

    @JsonIgnore
    private List<SubjectDTO>  subjects;

    @JsonIgnore
    private AdmissionRecordDTO admissionRecordDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
