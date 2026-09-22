package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.StudentResponse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentResponseRepository
        extends JpaRepository<StudentResponse, Long> {

    Optional<StudentResponse> findByUserIdAndQuestionId(
            Long userId,
            Long questionId);

    List<StudentResponse> findByUserId(Long userId);
}