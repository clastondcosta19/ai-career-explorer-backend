package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkillGapService {

    private final CareerRepository careerRepository;
    private final StudentProfileRepository profileRepository;

    public SkillGapService(
            CareerRepository careerRepository,
            StudentProfileRepository profileRepository) {

        this.careerRepository = careerRepository;
        this.profileRepository = profileRepository;
    }

    public Map<String, Object> analyzeSkillGap(
            Long userId,
            Long careerId) {

        StudentProfile profile =
                profileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"));

        Career career =
                careerRepository.findById(careerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Career not found"));

        Set<String> studentSkills =
                profile.getStudentSkills()
                        .stream()
                        .map(Skill::getName)
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .filter(skill -> !skill.isBlank())
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());

        List<Skill> requiredSkillEntities =
                new ArrayList<>(
                        career.getRequiredSkills()
                );

        requiredSkillEntities.sort(
                Comparator.comparing(
                        Skill::getName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        List<String> requiredSkills =
                requiredSkillEntities.stream()
                        .map(Skill::getName)
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .filter(skill -> !skill.isBlank())
                        .collect(Collectors.toList());

        List<String> matchedSkills =
                requiredSkillEntities.stream()
                        .map(Skill::getName)
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .filter(skill -> !skill.isBlank())
                        .filter(skill ->
                                studentSkills.contains(
                                        skill.toLowerCase()
                                )
                        )
                        .collect(Collectors.toList());

        List<String> missingSkills =
                requiredSkillEntities.stream()
                        .map(Skill::getName)
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .filter(skill -> !skill.isBlank())
                        .filter(skill ->
                                !studentSkills.contains(
                                        skill.toLowerCase()
                                )
                        )
                        .collect(Collectors.toList());

        int skillMatchPercentage = 0;

        if (!requiredSkills.isEmpty()) {

            skillMatchPercentage =
                    (int) Math.round(
                            (matchedSkills.size() * 100.0)
                                    / requiredSkills.size()
                    );
        }

        List<Map<String, Object>> missingSkillDetails =
                requiredSkillEntities.stream()
                        .filter(skill ->
                                skill.getName() != null
                        )
                        .filter(skill ->
                                !studentSkills.contains(
                                        skill.getName()
                                                .trim()
                                                .toLowerCase()
                                )
                        )
                        .map(skill -> {

                            Map<String, Object> detail =
                                    new LinkedHashMap<>();

                            detail.put(
                                    "id",
                                    skill.getId()
                            );

                            detail.put(
                                    "name",
                                    skill.getName()
                            );

                            detail.put(
                                    "description",
                                    skill.getDescription()
                            );

                            detail.put(
                                    "category",
                                    skill.getCategory()
                            );

                            return detail;
                        })
                        .collect(Collectors.toList());

        List<Map<String, Object>> matchedSkillDetails =
                requiredSkillEntities.stream()
                        .filter(skill ->
                                skill.getName() != null
                        )
                        .filter(skill ->
                                studentSkills.contains(
                                        skill.getName()
                                                .trim()
                                                .toLowerCase()
                                )
                        )
                        .map(skill -> {

                            Map<String, Object> detail =
                                    new LinkedHashMap<>();

                            detail.put(
                                    "id",
                                    skill.getId()
                            );

                            detail.put(
                                    "name",
                                    skill.getName()
                            );

                            detail.put(
                                    "description",
                                    skill.getDescription()
                            );

                            detail.put(
                                    "category",
                                    skill.getCategory()
                            );

                            return detail;
                        })
                        .collect(Collectors.toList());

        Map<String, Object> result =
                new LinkedHashMap<>();

        result.put(
                "careerId",
                career.getId()
        );

        result.put(
                "careerTitle",
                career.getTitle()
        );

        result.put(
                "requiredSkills",
                requiredSkills
        );

        result.put(
                "matchedSkills",
                matchedSkills
        );

        result.put(
                "missingSkills",
                missingSkills
        );

        result.put(
                "skillMatchPercentage",
                skillMatchPercentage
        );

        result.put(
                "totalRequiredSkills",
                requiredSkills.size()
        );

        result.put(
                "totalMatchedSkills",
                matchedSkills.size()
        );

        result.put(
                "totalMissingSkills",
                missingSkills.size()
        );

        result.put(
                "missingSkillDetails",
                missingSkillDetails
        );

        result.put(
                "matchedSkillDetails",
                matchedSkillDetails
        );

        return result;
    }
}