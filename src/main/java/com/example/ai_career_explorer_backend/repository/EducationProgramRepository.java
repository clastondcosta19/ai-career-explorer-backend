package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.EducationProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, Long> {

    List<EducationProgram> findByLevelIgnoreCase(String level);

    List<EducationProgram> findByFieldIgnoreCase(String field);

    List<EducationProgram> findByEntryLevelIgnoreCase(String entryLevel);

    List<EducationProgram> findByNameContainingIgnoreCase(String name);
}