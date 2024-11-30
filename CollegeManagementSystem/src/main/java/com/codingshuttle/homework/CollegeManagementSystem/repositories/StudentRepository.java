package com.codingshuttle.homework.CollegeManagementSystem.repositories;

import com.codingshuttle.homework.CollegeManagementSystem.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}
