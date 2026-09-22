package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.AssessmentOptionSignal;
import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.entity.StudentResponse;
import com.example.ai_career_explorer_backend.repository.AssessmentOptionSignalRepository;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;
import com.example.ai_career_explorer_backend.repository.StudentResponseRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CareerMatchingService {

    private final CareerRepository careerRepository;
    private final StudentProfileRepository profileRepository;
    private final StudentResponseRepository studentResponseRepository;
    private final AssessmentOptionSignalRepository assessmentOptionSignalRepository;

    public CareerMatchingService(
            CareerRepository careerRepository,
            StudentProfileRepository profileRepository,
            StudentResponseRepository studentResponseRepository,
            AssessmentOptionSignalRepository assessmentOptionSignalRepository) {

        this.careerRepository = careerRepository;
        this.profileRepository = profileRepository;
        this.studentResponseRepository = studentResponseRepository;
        this.assessmentOptionSignalRepository = assessmentOptionSignalRepository;
    }

    public List<Map<String, Object>> getCareerMatches(Long userId) {

        StudentProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        List<Career> careers = careerRepository.findAll();

        Set<String> studentSkills = profile.getStudentSkills()
                .stream()
                .map(Skill::getName)
                .map(String::toLowerCase)
                .map(String::trim)
                .filter(skill -> !skill.isBlank())
                .collect(Collectors.toSet());

        Map<String, Integer> assessmentSkillScores =
                calculateAssessmentSkillScores(userId);

        List<Map<String, Object>> results = new ArrayList<>();

        for (Career career : careers) {

            Set<String> requiredSkills = career.getRequiredSkills()
                    .stream()
                    .map(Skill::getName)
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .filter(skill -> !skill.isBlank())
                    .collect(Collectors.toSet());

            double skillScore =
                    calculateStructuredSkillMatch(
                            studentSkills,
                            requiredSkills);

            double assessmentScore =
                    calculateAssessmentCareerMatch(
                            assessmentSkillScores,
                            requiredSkills);

            double interestScore = calculateMatch(
                    profile.getInterests(),
                    career.getInterests());

            double subjectScore = calculateMatch(
                    profile.getSubjects(),
                    career.getSubjects());

            double educationScore = calculateEducationMatch(
                    profile.getEducationLevel(),
                    profile.getCurrentClass(),
                    profile.getStream(),
                    profile.getSubjects(),
                    career.getRequiredEducation(),
                    career.getSubjects());

            double strengthScore = calculateMatch(
                    profile.getStrengths(),
                    career.getStrengths());

            double finalScore =
                    (skillScore * 0.35)
                    + (assessmentScore * 0.15)
                    + (interestScore * 0.15)
                    + (subjectScore * 0.15)
                    + (educationScore * 0.10)
                    + (strengthScore * 0.10);

            int compatibility =
                    (int) Math.round(finalScore * 100);

            String confidence =
                    getConfidence(compatibility);

            List<String> matchedFactors =
                    new ArrayList<>();

            if (skillScore > 0) {
                matchedFactors.add("Skills");
            }

            if (assessmentScore > 0) {
                matchedFactors.add("Assessment");
            }

            if (interestScore > 0) {
                matchedFactors.add("Interests");
            }

            if (subjectScore > 0) {
                matchedFactors.add("Subjects");
            }

            if (educationScore > 0) {
                matchedFactors.add("Education");
            }

            if (strengthScore > 0) {
                matchedFactors.add("Strengths");
            }

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
                    "compatibilityScore",
                    compatibility
            );

            result.put(
                    "educationScore",
                    (int) Math.round(
                            educationScore * 100
                    )
            );

            result.put(
                    "confidence",
                    confidence
            );

            result.put(
                    "matchedFactors",
                    matchedFactors
            );

            results.add(result);
        }

        results.sort((a, b) ->
                Integer.compare(
                        (Integer) b.get("compatibilityScore"),
                        (Integer) a.get("compatibilityScore")
                )
        );

        return results;
    }

    private Map<String, Integer> calculateAssessmentSkillScores(
            Long userId) {

        List<StudentResponse> responses =
                studentResponseRepository.findByUserId(userId);

        Map<String, Integer> skillScores =
                new HashMap<>();

        for (StudentResponse response : responses) {

            if (response == null ||
                    response.getQuestion() == null ||
                    response.getSelectedOption() == null) {
                continue;
            }

            Long questionId =
                    response.getQuestion().getId();

            String selectedOption =
                    response.getSelectedOption()
                            .trim()
                            .toUpperCase();

            List<AssessmentOptionSignal> signals =
                    assessmentOptionSignalRepository
                            .findByQuestionIdAndSelectedOption(
                                    questionId,
                                    selectedOption);

            for (AssessmentOptionSignal signal : signals) {

                if (signal == null ||
                        signal.getSkill() == null ||
                        signal.getSkill().getName() == null ||
                        signal.getWeight() == null) {
                    continue;
                }

                String skillName =
                        signal.getSkill()
                                .getName()
                                .toLowerCase()
                                .trim();

                if (skillName.isBlank()) {
                    continue;
                }

                skillScores.merge(
                        skillName,
                        signal.getWeight(),
                        Integer::sum
                );
            }
        }

        return skillScores;
    }

    private double calculateAssessmentCareerMatch(
            Map<String, Integer> assessmentSkillScores,
            Set<String> requiredSkills) {

        if (assessmentSkillScores.isEmpty() ||
                requiredSkills.isEmpty()) {
            return 0.0;
        }

        int totalAssessmentWeight =
                assessmentSkillScores.values()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum();

        if (totalAssessmentWeight <= 0) {
            return 0.0;
        }

        int matchedAssessmentWeight = 0;

        for (String requiredSkill : requiredSkills) {

            Integer weight =
                    assessmentSkillScores.get(requiredSkill);

            if (weight != null) {
                matchedAssessmentWeight += weight;
            }
        }

        return (double) matchedAssessmentWeight
                / totalAssessmentWeight;
    }

    private double calculateStructuredSkillMatch(
            Set<String> studentSkills,
            Set<String> requiredSkills) {

        if (studentSkills.isEmpty() ||
                requiredSkills.isEmpty()) {
            return 0.0;
        }

        long matches = requiredSkills.stream()
                .filter(studentSkills::contains)
                .count();

        return (double) matches / requiredSkills.size();
    }

    private double calculateMatch(
            String studentData,
            String careerData) {

        if (studentData == null ||
                studentData.isBlank() ||
                careerData == null ||
                careerData.isBlank()) {
            return 0.0;
        }

        Set<String> studentItems =
                normalize(studentData);

        Set<String> careerItems =
                normalize(careerData);

        if (studentItems.isEmpty() ||
                careerItems.isEmpty()) {
            return 0.0;
        }

        long matches = studentItems.stream()
                .filter(careerItems::contains)
                .count();

        return (double) matches / careerItems.size();
    }

    private double calculateEducationMatch(
            String educationLevel,
            String currentClass,
            String stream,
            String studentSubjects,
            String requiredEducation,
            String careerSubjects) {

        if ((educationLevel == null ||
                educationLevel.isBlank()) &&
                (currentClass == null ||
                currentClass.isBlank())) {

            return 0.50;
        }

        double stageScore =
                calculateEducationStageMatch(
                        educationLevel,
                        currentClass,
                        requiredEducation);

        double streamScore =
                calculateStreamMatch(
                        stream,
                        requiredEducation,
                        careerSubjects);

        double subjectScore =
                calculateEducationSubjectMatch(
                        studentSubjects,
                        careerSubjects);

        return
                (stageScore * 0.60)
                + (streamScore * 0.20)
                + (subjectScore * 0.20);
    }

    private double calculateEducationStageMatch(
            String educationLevel,
            String currentClass,
            String requiredEducation) {

        if (requiredEducation == null ||
                requiredEducation.isBlank()) {
            return 0.50;
        }

        String studentEducation =
                educationLevel == null
                        ? ""
                        : educationLevel
                                .toLowerCase()
                                .trim();

        String studentClass =
                currentClass == null
                        ? ""
                        : currentClass
                                .toLowerCase()
                                .trim();

        String required =
                requiredEducation
                        .toLowerCase()
                        .trim();

        if (studentEducation.contains("higher_secondary") ||
                studentEducation.contains("higher secondary") ||
                studentEducation.contains("class_12") ||
                studentEducation.contains("class 12") ||
                studentClass.contains("class_12") ||
                studentClass.contains("class 12")) {

            if (required.contains("b.tech") ||
                    required.contains("b.e.") ||
                    required.contains("bca") ||
                    required.contains("b.sc") ||
                    required.contains("bba") ||
                    required.contains("bachelor") ||
                    required.contains("undergraduate") ||
                    required.contains("after 12th") ||
                    required.contains("after 12") ||
                    required.contains("ca pathway")) {

                return 1.0;
            }

            if (required.contains("portfolio-based") ||
                    required.contains("portfolio based")) {

                if (required.contains("degree") ||
                        required.contains("diploma")) {

                    return 0.85;
                }

                return 0.70;
            }

            if (required.contains("diploma") ||
                    required.contains("polytechnic") ||
                    required.contains("iti")) {

                return 0.85;
            }

            if (required.contains("degree")) {
                return 0.85;
            }

            return 0.50;
        }

        if (studentEducation.contains("secondary") ||
                studentEducation.contains("class_10") ||
                studentEducation.contains("class 10") ||
                studentClass.contains("class_10") ||
                studentClass.contains("class 10")) {

            if (required.contains("diploma") ||
                    required.contains("after 10th") ||
                    required.contains("after class 10") ||
                    required.contains("iti") ||
                    required.contains("polytechnic")) {

                return 1.0;
            }

            if (required.contains("b.tech") ||
                    required.contains("b.e.") ||
                    required.contains("bca") ||
                    required.contains("b.sc") ||
                    required.contains("bba") ||
                    required.contains("bachelor") ||
                    required.contains("undergraduate") ||
                    required.contains("degree") ||
                    required.contains("after 12") ||
                    required.contains("ca pathway")) {

                return 0.70;
            }

            return 0.50;
        }

        if (studentEducation.contains("college") ||
                studentEducation.contains("undergraduate")) {

            if (required.contains("degree") ||
                    required.contains("bachelor") ||
                    required.contains("b.tech") ||
                    required.contains("b.e.") ||
                    required.contains("bca") ||
                    required.contains("b.sc") ||
                    required.contains("bba") ||
                    required.contains("undergraduate")) {

                return 1.0;
            }

            if (required.contains("specialized") ||
                    required.contains("specialised") ||
                    required.contains("portfolio")) {

                return 0.85;
            }

            return 0.70;
        }

        if (studentEducation.contains("graduate") ||
                studentEducation.contains("postgraduate")) {

            if (required.contains("master") ||
                    required.contains("postgraduate") ||
                    required.contains("degree") ||
                    required.contains("bachelor") ||
                    required.contains("undergraduate")) {

                return 1.0;
            }

            return 0.80;
        }

        return 0.50;
    }

    private double calculateStreamMatch(
            String stream,
            String requiredEducation,
            String careerSubjects) {

        if (stream == null ||
                stream.isBlank()) {
            return 0.50;
        }

        String studentStream =
                stream.toLowerCase().trim();

        String required =
                requiredEducation == null
                        ? ""
                        : requiredEducation
                                .toLowerCase()
                                .trim();

        String subjects =
                careerSubjects == null
                        ? ""
                        : careerSubjects
                                .toLowerCase()
                                .trim();

        if (studentStream.contains("science")) {

            if (subjects.contains("physics") ||
                    subjects.contains("chemistry") ||
                    subjects.contains("mathematics") ||
                    subjects.contains("biology") ||
                    subjects.contains("computer science") ||
                    subjects.contains("programming") ||
                    required.contains("engineering") ||
                    required.contains("b.tech") ||
                    required.contains("b.e.") ||
                    required.contains("computer science")) {

                return 1.0;
            }

            if (required.contains("bachelor") ||
                    required.contains("degree") ||
                    required.contains("undergraduate") ||
                    required.contains("after 12th") ||
                    required.contains("after 12")) {

                return 0.75;
            }

            return 0.50;
        }

        if (studentStream.contains("commerce")) {

            if (subjects.contains("accounting") ||
                    subjects.contains("accountancy") ||
                    subjects.contains("economics") ||
                    subjects.contains("business") ||
                    subjects.contains("finance") ||
                    required.contains("commerce") ||
                    required.contains("accounting") ||
                    required.contains("ca")) {

                return 1.0;
            }

            if (required.contains("bachelor") ||
                    required.contains("degree") ||
                    required.contains("undergraduate") ||
                    required.contains("after 12th") ||
                    required.contains("after 12")) {

                return 0.70;
            }

            return 0.50;
        }

        if (studentStream.contains("arts") ||
                studentStream.contains("humanities")) {

            if (subjects.contains("history") ||
                    subjects.contains("geography") ||
                    subjects.contains("political") ||
                    subjects.contains("psychology") ||
                    subjects.contains("sociology") ||
                    subjects.contains("language") ||
                    subjects.contains("literature") ||
                    subjects.contains("communication") ||
                    subjects.contains("teaching")) {

                return 1.0;
            }

            if (required.contains("bachelor") ||
                    required.contains("degree") ||
                    required.contains("undergraduate") ||
                    required.contains("after 12th") ||
                    required.contains("after 12")) {

                return 0.70;
            }

            return 0.50;
        }

        return 0.50;
    }


    private double calculateEducationSubjectMatch(
            String studentSubjects,
            String careerSubjects) {

        if (studentSubjects == null ||
                studentSubjects.isBlank() ||
                careerSubjects == null ||
                careerSubjects.isBlank()) {

            return 0.50;
        }

        Set<String> studentItems =
                normalizeSubjects(studentSubjects);

        Set<String> careerItems =
                normalizeSubjects(careerSubjects);

        if (studentItems.isEmpty() ||
                careerItems.isEmpty()) {

            return 0.50;
        }

        long matches = 0;

        for (String studentSubject : studentItems) {

            for (String careerSubject : careerItems) {

                if (subjectsAreRelated(
                        studentSubject,
                        careerSubject)) {

                    matches++;
                    break;
                }
            }
        }

        return Math.min(
                1.0,
                (double) matches / careerItems.size()
        );
    }

    /*
     * =====================================================
     * SUBJECT RELATION
     * =====================================================
     */

    private boolean subjectsAreRelated(
            String studentSubject,
            String careerSubject) {

        if (studentSubject.equals(careerSubject)) {
            return true;
        }

        if (studentSubject.contains(careerSubject) ||
                careerSubject.contains(studentSubject)) {
            return true;
        }

        if (studentSubject.contains("math") &&
                careerSubject.contains("math")) {

            return true;
        }

        if (studentSubject.contains("account") &&
                careerSubject.contains("account")) {

            return true;
        }

        if (studentSubject.contains("computer") &&
                careerSubject.contains("computer")) {

            return true;
        }

        if (studentSubject.contains("program") &&
                careerSubject.contains("program")) {

            return true;
        }

        if (studentSubject.contains("physics") &&
                careerSubject.contains("physics")) {

            return true;
        }

        if (studentSubject.contains("chemistry") &&
                careerSubject.contains("chemistry")) {

            return true;
        }

        if (studentSubject.contains("biology") &&
                careerSubject.contains("biology")) {

            return true;
        }

        if (studentSubject.contains("business") &&
                careerSubject.contains("business")) {

            return true;
        }

        if (studentSubject.contains("economics") &&
                careerSubject.contains("economics")) {

            return true;
        }

        return false;
    }

    private Set<String> normalizeSubjects(String data) {

        if (data == null ||
                data.isBlank()) {

            return new HashSet<>();
        }

        return Arrays.stream(
                    data.toLowerCase()
                            .split("[,;]")
                )
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .collect(Collectors.toSet());
    }

    private Set<String> normalize(String data) {

        if (data == null ||
                data.isBlank()) {

            return new HashSet<>();
        }

        return Arrays.stream(
                    data.toLowerCase()
                            .split("[,;]")
                )
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .collect(Collectors.toSet());
    }


    private String getConfidence(int score) {

        if (score >= 75) {
            return "High";
        }

        if (score >= 50) {
            return "Moderate";
        }

        return "Low";
    }
}