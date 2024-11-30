package com.codingshuttle.homework.CollegeManagementSystem.entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="subjects")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="professor_id",referencedColumnName = "id")
    private ProfessorEntity professor;

    @ManyToMany(mappedBy = "subjects")
    private List<StudentEntity>  students;
}
