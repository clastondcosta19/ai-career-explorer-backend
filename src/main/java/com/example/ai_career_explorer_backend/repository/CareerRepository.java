package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.Career;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {

    List<Career> findByTitleContainingIgnoreCase(String title);

    List<Career> findByDomainIgnoreCase(String domain);

    List<Career> findByCareerFamilyIgnoreCase(String careerFamily);

    List<Career> findByRoleIgnoreCase(String role);

    List<Career> findByDomainIgnoreCaseAndCareerFamilyIgnoreCase(
            String domain,
            String careerFamily
    );

    @Override
    @EntityGraph(attributePaths = {"educationPrograms", "requiredSkills"})
    Optional<Career> findById(Long id);
}