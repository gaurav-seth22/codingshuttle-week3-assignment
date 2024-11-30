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
@Table(name="students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;



    @ManyToMany(mappedBy = "students",fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    private List<ProfessorEntity> professors;


    @ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @JoinTable(
            name="students_subjects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<SubjectEntity>  subjects;

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AdmissionRecordEntity admissionRecordEntity;

}

