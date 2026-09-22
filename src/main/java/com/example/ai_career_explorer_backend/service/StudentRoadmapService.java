package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.entity.StudentRoadmap;
import com.example.ai_career_explorer_backend.entity.User;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;
import com.example.ai_career_explorer_backend.repository.StudentRoadmapRepository;
import com.example.ai_career_explorer_backend.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentRoadmapService {

    private final ObjectMapper objectMapper;
    private final StudentRoadmapRepository roadmapRepository;
    private final UserRepository userRepository;
    private final CareerRepository careerRepository;
    private final StudentProfileRepository profileRepository;
    private final SkillGapService skillGapService;

    public StudentRoadmapService(
            StudentRoadmapRepository roadmapRepository,
            UserRepository userRepository,
            CareerRepository careerRepository,
            StudentProfileRepository profileRepository,
            SkillGapService skillGapService,
            ObjectMapper objectMapper
    ) {
        this.roadmapRepository = roadmapRepository;
        this.userRepository = userRepository;
        this.careerRepository = careerRepository;
        this.profileRepository = profileRepository;
        this.skillGapService = skillGapService;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public StudentRoadmap generateRoadmap(
            Long userId,
            Long careerId
    ) {

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        Career career =
                careerRepository.findById(careerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Career not found"
                                )
                        );

        StudentProfile profile =
                profileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"
                                )
                        );

        Map<String, Object> skillGap =
                skillGapService.analyzeSkillGap(
                        userId,
                        careerId
                );

        @SuppressWarnings("unchecked")
        List<String> missingSkills =
                (List<String>) skillGap.get(
                        "missingSkills"
                );

        String currentStage =
                buildCurrentStage(profile);

        String targetStage =
                "Ready to explore " + career.getTitle();

        String goal =
                profile.getCareerGoals() != null
                        && !profile.getCareerGoals().isBlank()
                        ? profile.getCareerGoals()
                        : career.getTitle();

        String roadmapData =
                buildRoadmapData(
                        career,
                        missingSkills
                );

        StudentRoadmap roadmap =
                roadmapRepository
                        .findByUserIdAndCareerId(
                                userId,
                                careerId
                        )
                        .orElseGet(
                                StudentRoadmap::new
                        );

        roadmap.setUser(user);
        roadmap.setCareer(career);
        roadmap.setGoal(goal);
        roadmap.setCurrentStage(currentStage);
        roadmap.setTargetStage(targetStage);
        roadmap.setRoadmapData(roadmapData);
        roadmap.setStatus("ACTIVE");

        return roadmapRepository.save(roadmap);
    }

    public List<StudentRoadmap> getStudentRoadmaps(
            Long userId
    ) {
        return roadmapRepository.findByUserId(userId);
    }

    public StudentRoadmap getStudentCareerRoadmap(
            Long userId,
            Long careerId
    ) {
        return roadmapRepository
                .findByUserIdAndCareerId(
                        userId,
                        careerId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Roadmap not found"
                        )
                );
    }

    @Transactional
    public StudentRoadmap updateStepStatus(
            Long userId,
            Long careerId,
            Integer stepNumber,
            String status
    ) {

        StudentRoadmap roadmap =
                roadmapRepository
                        .findByUserIdAndCareerId(
                                userId,
                                careerId
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Roadmap not found"
                                )
                        );

        if (!"COMPLETED".equals(status)
                && !"NOT_STARTED".equals(status)) {

            throw new IllegalArgumentException(
                    "Invalid roadmap step status"
            );
        }

        String roadmapData =
                roadmap.getRoadmapData();

        if (roadmapData == null
                || roadmapData.isBlank()) {

            throw new RuntimeException(
                    "Roadmap data not found"
            );
        }

        Map<String, Object> roadmapMap =
                parseRoadmapData(roadmapData);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> steps =
                (List<Map<String, Object>>) roadmapMap.get(
                        "steps"
                );

        if (steps == null) {
            throw new RuntimeException(
                    "Roadmap steps not found"
            );
        }

        boolean stepFound = false;

        for (Map<String, Object> step : steps) {

            Number currentStep =
                    (Number) step.get("step");

            if (currentStep != null
                    && currentStep.intValue() == stepNumber) {

                step.put(
                        "status",
                        status
                );

                stepFound = true;
                break;
            }
        }

        if (!stepFound) {
            throw new RuntimeException(
                    "Roadmap step not found"
            );
        }

        roadmap.setRoadmapData(
                convertToJsonLikeString(
                        roadmapMap
                )
        );

        return roadmapRepository.save(roadmap);
    }

    private String buildCurrentStage(
            StudentProfile profile
    ) {

        List<String> parts =
                new ArrayList<>();

        if (profile.getEducationLevel() != null
                && !profile.getEducationLevel().isBlank()) {

            parts.add(
                    profile.getEducationLevel()
            );
        }

        if (profile.getCurrentClass() != null
                && !profile.getCurrentClass().isBlank()) {

            parts.add(
                    profile.getCurrentClass()
            );
        }

        if (profile.getStream() != null
                && !profile.getStream().isBlank()) {

            parts.add(
                    profile.getStream()
            );
        }

        if (parts.isEmpty()) {
            return "Current student stage";
        }

        return String.join(
                " • ",
                parts
        );
    }

    private String buildRoadmapData(
            Career career,
            List<String> missingSkills
    ) {

        Map<String, Object> roadmap =
                new LinkedHashMap<>();

        roadmap.put(
                "career",
                career.getTitle()
        );

        roadmap.put(
                "totalMissingSkills",
                missingSkills.size()
        );

        roadmap.put(
                "missingSkills",
                missingSkills
        );

        List<Map<String, Object>> steps =
                new ArrayList<>();

        int stepNumber = 1;

        for (String skillName : missingSkills) {

            Map<String, Object> step =
                    new LinkedHashMap<>();

            Skill skill =
                    findSkillByName(
                            career,
                            skillName
                    );

            String description =
                    skill != null
                            && skill.getDescription() != null
                            && !skill.getDescription().isBlank()
                            ? skill.getDescription()
                            : "Develop foundational knowledge and practical understanding of "
                                    + skillName
                                    + ".";

            step.put(
                    "step",
                    stepNumber++
            );

            step.put(
                    "skill",
                    skillName
            );

            step.put(
                    "description",
                    description
            );

            step.put(
                    "action",
                    buildSkillAction(skillName)
            );

            step.put(
                    "status",
                    "NOT_STARTED"
            );

            steps.add(step);
        }

        roadmap.put(
                "steps",
                steps
        );

        return convertToJsonLikeString(
                roadmap
        );
    }

    private String buildSkillAction(
            String skillName
    ) {

        if (skillName == null
                || skillName.isBlank()) {

            return "Build foundational knowledge and practice through small hands-on exercises.";
        }

        return switch (skillName.trim().toLowerCase()) {

            case "algorithms" ->
                    "Learn common algorithmic approaches, trace simple problems step by step, and practice solving progressively harder problems.";

            case "data structures" ->
                    "Learn how common data structures work, implement simple examples, and practice choosing an appropriate structure for different problems.";

            case "programming" ->
                    "Strengthen programming fundamentals by writing small programs and practicing variables, conditions, loops, functions, and problem solving.";

            case "backend development" ->
                    "Learn how server-side applications work, build a small backend service, and practice handling requests, business logic, and data.";

            case "frontend development" ->
                    "Build small web interfaces and practice structuring pages, handling user interaction, and connecting the interface to application data.";

            case "web development" ->
                    "Create small web projects and practice the fundamentals of pages, client-server communication, navigation, and application structure.";

            case "api development" ->
                    "Learn how APIs expose application functionality, build simple endpoints, and practice sending and receiving structured data.";

            case "git" ->
                    "Create a small project repository and practice commits, branches, merging changes, and maintaining a clear project history.";

            case "software testing" ->
                    "Learn basic testing concepts and write tests for small pieces of code to verify expected behaviour and identify defects.";

            default ->
                    "Build foundational knowledge of "
                            + skillName
                            + " and practice it through small hands-on exercises.";
        };
    }

    private Skill findSkillByName(
            Career career,
            String skillName
    ) {

        if (career.getRequiredSkills() == null
                || skillName == null) {

            return null;
        }

        for (Skill skill : career.getRequiredSkills()) {

            if (skill != null
                    && skill.getName() != null
                    && skill.getName().equalsIgnoreCase(
                            skillName
                    )) {

                return skill;
            }
        }

        return null;
    }

    private Map<String, Object> parseRoadmapData(
            String roadmapData
    ) {

        try {

            return objectMapper.readValue(
                    roadmapData,
                    new TypeReference<Map<String, Object>>() {}
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse roadmap data",
                    e
            );
        }
    }

    private String convertToJsonLikeString(
            Map<String, Object> roadmap
    ) {

        try {

            return objectMapper.writeValueAsString(
                    roadmap
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to serialize roadmap data",
                    e
            );
        }
    }
}
