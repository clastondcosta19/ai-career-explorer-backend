package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.CareerAnalysis;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CareerAnalysisRepository
        extends JpaRepository<CareerAnalysis, Long> {

    Optional<CareerAnalysis> findByUserIdAndCareerId(
            Long userId,
            Long careerId
    );

    List<CareerAnalysis> findByUserId(
            Long userId
    );

    void deleteByUserIdAndCareerId(
            Long userId,
            Long careerId
    );
}