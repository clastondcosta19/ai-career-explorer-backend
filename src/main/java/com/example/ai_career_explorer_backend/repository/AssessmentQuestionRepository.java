package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.AssessmentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentQuestionRepository
        extends JpaRepository<AssessmentQuestion, Long> {
}