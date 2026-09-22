package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.EducationProgram;
import com.example.ai_career_explorer_backend.entity.Pathway;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.EducationProgramRepository;
import com.example.ai_career_explorer_backend.repository.PathwayRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathwayService {

    private final PathwayRepository pathwayRepository;
    private final CareerRepository careerRepository;
    private final EducationProgramRepository educationProgramRepository;

    public PathwayService(
            PathwayRepository pathwayRepository,
            CareerRepository careerRepository,
            EducationProgramRepository educationProgramRepository) {

        this.pathwayRepository = pathwayRepository;
        this.careerRepository = careerRepository;
        this.educationProgramRepository = educationProgramRepository;
    }

    public List<Pathway> getAllPathways() {
        return pathwayRepository.findAll();
    }

    public Pathway getPathwayById(Long id) {

        return pathwayRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pathway not found"));
    }

    public List<Pathway> searchPathways(String name) {

        return pathwayRepository
                .findByNameContainingIgnoreCase(name);
    }

    public List<Pathway> getPathwaysByStartingLevel(
            String startingLevel) {

        return pathwayRepository
                .findByStartingLevelIgnoreCase(startingLevel);
    }

    public List<Pathway> getPathwaysByType(
            String pathwayType) {

        return pathwayRepository
                .findByPathwayTypeIgnoreCase(pathwayType);
    }

    public List<Pathway> getPathwaysByCareerId(
            Long careerId) {

        return pathwayRepository
                .findByCareerId(careerId);
    }

    public Pathway savePathway(Pathway pathway) {

        return pathwayRepository.save(pathway);
    }

    public Pathway assignCareer(
            Long pathwayId,
            Long careerId) {

        Pathway pathway = getPathwayById(pathwayId);

        Career career = careerRepository.findById(careerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Career not found"));

        pathway.setCareer(career);

        return pathwayRepository.save(pathway);
    }

    public Pathway addEducationProgram(
            Long pathwayId,
            Long educationProgramId) {

        Pathway pathway = getPathwayById(pathwayId);

        EducationProgram educationProgram =
                educationProgramRepository.findById(
                        educationProgramId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Education program not found"));

        pathway.addEducationProgram(educationProgram);

        return pathwayRepository.save(pathway);
    }

    public Pathway removeEducationProgram(
            Long pathwayId,
            Long educationProgramId) {

        Pathway pathway = getPathwayById(pathwayId);

        EducationProgram educationProgram =
                educationProgramRepository.findById(
                        educationProgramId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Education program not found"));

        pathway.removeEducationProgram(educationProgram);

        return pathwayRepository.save(pathway);
    }
}