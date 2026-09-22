package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.EducationProgram;
import com.example.ai_career_explorer_backend.repository.EducationProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationProgramService {

    private final EducationProgramRepository educationProgramRepository;

    public EducationProgramService(
            EducationProgramRepository educationProgramRepository) {
        this.educationProgramRepository = educationProgramRepository;
    }

    public List<EducationProgram> getAllPrograms() {
        return educationProgramRepository.findAll();
    }

    public EducationProgram getProgramById(Long id) {
        return educationProgramRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Education program not found"));
    }

    public List<EducationProgram> searchPrograms(String name) {
        return educationProgramRepository
                .findByNameContainingIgnoreCase(name);
    }

    public List<EducationProgram> getProgramsByLevel(String level) {
        return educationProgramRepository
                .findByLevelIgnoreCase(level);
    }

    public List<EducationProgram> getProgramsByField(String field) {
        return educationProgramRepository
                .findByFieldIgnoreCase(field);
    }

    public List<EducationProgram> getProgramsByEntryLevel(String entryLevel) {
        return educationProgramRepository
                .findByEntryLevelIgnoreCase(entryLevel);
    }

    public EducationProgram saveProgram(EducationProgram program) {
        return educationProgramRepository.save(program);
    }
}