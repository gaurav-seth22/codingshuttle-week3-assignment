package com.codingshuttle.homework.CollegeManagementSystem.dto;

import com.codingshuttle.homework.CollegeManagementSystem.entities.StudentEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionRecordDTO {

    private Long id;

    private Integer fees;

    private StudentDTO student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdmissionRecordDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
