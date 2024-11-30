package com.codingshuttle.homework.CollegeManagementSystem.repositories;

import com.codingshuttle.homework.CollegeManagementSystem.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
