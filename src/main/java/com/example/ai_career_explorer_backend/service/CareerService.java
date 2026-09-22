package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.EducationProgram;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.EducationProgramRepository;
import com.example.ai_career_explorer_backend.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerService {

    private final CareerRepository careerRepository;
    private final EducationProgramRepository educationProgramRepository;
    private final SkillRepository skillRepository;

    public CareerService(
            CareerRepository careerRepository,
            EducationProgramRepository educationProgramRepository,
            SkillRepository skillRepository) {

        this.careerRepository = careerRepository;
        this.educationProgramRepository = educationProgramRepository;
        this.skillRepository = skillRepository;
    }

    public List<Career> getAllCareers() {
        return careerRepository.findAll();
    }

    public Career getCareerById(Long id) {
        return careerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career not found"));
    }

    public List<Career> searchCareers(String title) {
        return careerRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Career> getCareersByDomain(String domain) {
        return careerRepository.findByDomainIgnoreCase(domain);
    }

    public List<Career> getCareersByFamily(String careerFamily) {
        return careerRepository.findByCareerFamilyIgnoreCase(careerFamily);
    }

    public List<Career> getCareersByRole(String role) {
        return careerRepository.findByRoleIgnoreCase(role);
    }

    public List<Career> getCareersByDomainAndFamily(
            String domain,
            String careerFamily) {

        return careerRepository
                .findByDomainIgnoreCaseAndCareerFamilyIgnoreCase(
                        domain,
                        careerFamily
                );
    }

    public Career saveCareer(Career career) {
        return careerRepository.save(career);
    }

    public Career addEducationProgram(
            Long careerId,
            Long educationProgramId) {

        Career career = getCareerById(careerId);

        EducationProgram educationProgram =
                educationProgramRepository.findById(educationProgramId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Education program not found"
                                ));

        career.addEducationProgram(educationProgram);

        return careerRepository.save(career);
    }

    public Career removeEducationProgram(
            Long careerId,
            Long educationProgramId) {

        Career career = getCareerById(careerId);

        EducationProgram educationProgram =
                educationProgramRepository.findById(educationProgramId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Education program not found"
                                ));

        career.removeEducationProgram(educationProgram);

        return careerRepository.save(career);
    }

    public Career addRequiredSkill(
            Long careerId,
            Long skillId) {

        Career career = getCareerById(careerId);

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Skill not found"
                        ));

        career.addRequiredSkill(skill);

        return careerRepository.save(career);
    }

    public Career removeRequiredSkill(
            Long careerId,
            Long skillId) {

        Career career = getCareerById(careerId);

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Skill not found"
                        ));

        career.removeRequiredSkill(skill);

        return careerRepository.save(career);
    }
}