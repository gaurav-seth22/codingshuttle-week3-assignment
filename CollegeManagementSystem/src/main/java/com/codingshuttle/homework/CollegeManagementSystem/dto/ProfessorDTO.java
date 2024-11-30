package com.codingshuttle.homework.CollegeManagementSystem.dto;

import com.codingshuttle.homework.CollegeManagementSystem.entities.StudentEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.SubjectEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class ProfessorDTO {

    private Long id;
    private String title;

    @JsonIgnore
    private List<SubjectDTO> subjects;

    private List<StudentDTO>  students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessorDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
