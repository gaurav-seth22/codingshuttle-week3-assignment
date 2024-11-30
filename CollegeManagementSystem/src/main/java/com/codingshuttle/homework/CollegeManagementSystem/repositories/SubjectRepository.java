package com.codingshuttle.homework.CollegeManagementSystem.repositories;

import com.codingshuttle.homework.CollegeManagementSystem.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {
}
