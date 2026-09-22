package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.StudentRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRoadmapRepository
        extends JpaRepository<StudentRoadmap, Long> {

    List<StudentRoadmap> findByUserId(Long userId);

    List<StudentRoadmap> findByUserIdAndStatus(
            Long userId,
            String status
    );

    Optional<StudentRoadmap> findByUserIdAndCareerId(
            Long userId,
            Long careerId
    );
}