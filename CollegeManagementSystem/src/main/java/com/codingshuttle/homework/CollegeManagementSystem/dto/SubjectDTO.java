package com.codingshuttle.homework.CollegeManagementSystem.dto;


import com.codingshuttle.homework.CollegeManagementSystem.entities.ProfessorEntity;
import com.codingshuttle.homework.CollegeManagementSystem.entities.StudentEntity;
import jakarta.persistence.*;
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
public class SubjectDTO {
    private Long id;

    private String title;

    private ProfessorDTO professor;

    private List<StudentDTO> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectDTO that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
