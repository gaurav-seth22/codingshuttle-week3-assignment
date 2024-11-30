package com.codingshuttle.homework.CollegeManagementSystem.repositories;

import com.codingshuttle.homework.CollegeManagementSystem.entities.AdmissionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecordEntity,Long> {
    Optional<AdmissionRecordEntity> findByStudentId(Long studentId);
}
