package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.AssessmentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRepository
        extends JpaRepository<AssessmentQuestion, Long> {

    List<AssessmentQuestion> findByActiveTrueOrderByOrderNumberAsc();

    List<AssessmentQuestion> findByAssessmentTypeAndActiveTrueOrderByOrderNumberAsc(
            String assessmentType
    );

    List<AssessmentQuestion> findByAssessmentTypeAndCareerFamilyAndActiveTrueOrderByOrderNumberAsc(
            String assessmentType,
            String careerFamily
    );
}