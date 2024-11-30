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
@Table(name="professors")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    @OneToMany(mappedBy = "professor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubjectEntity> subjects;




    @ManyToMany
    @JoinTable(
            name = "professor_students",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<StudentEntity>  students;

}

