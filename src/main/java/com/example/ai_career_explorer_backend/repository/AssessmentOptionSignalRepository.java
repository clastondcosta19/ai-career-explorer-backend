package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.AssessmentOptionSignal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentOptionSignalRepository
        extends JpaRepository<AssessmentOptionSignal, Long> {

    List<AssessmentOptionSignal> findByQuestionId(Long questionId);

    List<AssessmentOptionSignal> findByQuestionIdAndSelectedOption(
            Long questionId,
            String selectedOption);

    List<AssessmentOptionSignal> findBySkillId(Long skillId);
}