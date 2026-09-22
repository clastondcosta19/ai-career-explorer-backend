package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.AssessmentOptionSignal;
import com.example.ai_career_explorer_backend.entity.AssessmentQuestion;
import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.entity.StudentResponse;
import com.example.ai_career_explorer_backend.entity.User;
import com.example.ai_career_explorer_backend.repository.AssessmentOptionSignalRepository;
import com.example.ai_career_explorer_backend.repository.AssessmentRepository;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;
import com.example.ai_career_explorer_backend.repository.StudentResponseRepository;
import com.example.ai_career_explorer_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;

    private final AssessmentOptionSignalRepository signalRepository;

    private final StudentResponseRepository responseRepository;

    private final StudentProfileRepository profileRepository;

    private final UserRepository userRepository;

    private final CareerRepository careerRepository;

    public AssessmentService(
            AssessmentRepository assessmentRepository,
            AssessmentOptionSignalRepository signalRepository,
            StudentResponseRepository responseRepository,
            StudentProfileRepository profileRepository,
            UserRepository userRepository,
            CareerRepository careerRepository) {

        this.assessmentRepository = assessmentRepository;

        this.signalRepository = signalRepository;

        this.responseRepository = responseRepository;

        this.profileRepository = profileRepository;

        this.userRepository = userRepository;

        this.careerRepository = careerRepository;
    }

    public List<AssessmentQuestion> getCoreQuestions() {

        return assessmentRepository
                .findByAssessmentTypeAndActiveTrueOrderByOrderNumberAsc(
                        "CORE"
                );
    }

    public List<AssessmentQuestion> getFamilyQuestions(
            String careerFamily) {

        return assessmentRepository
                .findByAssessmentTypeAndCareerFamilyAndActiveTrueOrderByOrderNumberAsc(
                        "FAMILY",
                        careerFamily
                );
    }

    @Transactional(readOnly = true)
    public String determineCareerFamily(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile profile =
                profileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"
                                ));

        List<Career> careers =
                careerRepository.findAll();

        if (careers.isEmpty()) {

            throw new RuntimeException(
                    "No careers are available"
            );
        }

        Set<String> studentSkillNames =
                new HashSet<>();

        for (Skill skill : profile.getStudentSkills()) {

            if (skill == null ||
                    skill.getName() == null) {

                continue;
            }

            studentSkillNames.add(
                    normalizeSkillName(skill.getName())
            );
        }

        Map<String, Integer> familyScores =
                new HashMap<>();

        for (Career career : careers) {

            if (career == null ||
                    career.getCareerFamily() == null ||
                    career.getCareerFamily().isBlank()) {

                continue;
            }

            String family =
                    career.getCareerFamily().trim();

            int matchedSkills = 0;

            if (career.getRequiredSkills() != null) {

                for (Skill requiredSkill :
                        career.getRequiredSkills()) {

                    if (requiredSkill == null ||
                            requiredSkill.getName() == null) {

                        continue;
                    }

                    String requiredSkillName =
                            normalizeSkillName(
                                    requiredSkill.getName()
                            );

                    if (studentSkillNames.contains(
                            requiredSkillName)) {

                        matchedSkills++;
                    }
                }
            }

            familyScores.merge(
                    family,
                    matchedSkills,
                    Integer::sum
            );
        }

        if (familyScores.isEmpty()) {

            return careers.stream()
                    .map(Career::getCareerFamily)
                    .filter(family ->
                            family != null &&
                            !family.isBlank())
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "No career families are available"
                            ));
        }

        String bestFamily = null;
        int bestScore = -1;

        for (Map.Entry<String, Integer> entry :
                familyScores.entrySet()) {

            String family = entry.getKey();
            int score = entry.getValue();

            if (score > bestScore) {

                bestScore = score;
                bestFamily = family;
            }
        }

        if (bestFamily == null) {

            throw new RuntimeException(
                    "Unable to determine career family"
            );
        }

        return bestFamily;
    }

    public StudentResponse saveResponse(
            Long userId,
            Long questionId,
            String selectedOption) {

        User user = userRepository.findById(userId)

                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        AssessmentQuestion question =
                assessmentRepository.findById(questionId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Question not found"
                                ));

        if (!isValidOption(selectedOption)) {

            throw new RuntimeException(
                    "Invalid option. Use A, B, C or D."
            );
        }

        String normalizedOption =
                selectedOption
                        .trim()
                        .toUpperCase();

        StudentResponse response =
                responseRepository
                        .findByUserIdAndQuestionId(
                                userId,
                                questionId
                        )
                        .orElse(new StudentResponse());

        response.setUser(user);

        response.setQuestion(question);

        response.setSelectedOption(
                normalizedOption
        );

        StudentResponse savedResponse =
                responseRepository.save(response);

        updateStudentSkills(userId);

        return savedResponse;
    }

    public List<StudentResponse> getStudentResponses(
            Long userId) {

        if (!userRepository.existsById(userId)) {

            throw new RuntimeException(
                    "User not found"
            );
        }

        return responseRepository.findByUserId(userId);
    }

    private void updateStudentSkills(Long userId) {

        StudentProfile profile =
                profileRepository.findByUserId(userId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"
                                ));

        List<StudentResponse> responses =
                responseRepository.findByUserId(userId);

        Map<Long, Integer> skillScores =
                new HashMap<>();

        for (StudentResponse response :
                responses) {

            if (response.getQuestion() == null ||
                    response.getQuestion().getId() == null ||
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
                    signalRepository
                            .findByQuestionIdAndSelectedOption(
                                    questionId,
                                    selectedOption
                            );

            for (AssessmentOptionSignal signal :
                    signals) {

                Skill skill =
                        signal.getSkill();

                if (skill == null ||
                        skill.getId() == null) {

                    continue;
                }

                int weight =
                        signal.getWeight() != null
                                ? signal.getWeight()
                                : 0;

                skillScores.merge(
                        skill.getId(),
                        weight,
                        Integer::sum
                );
            }
        }

        int skillThreshold = 3;

        Set<Skill> calculatedSkills =
                new HashSet<>();

        for (Map.Entry<Long, Integer> entry :
                skillScores.entrySet()) {

            if (entry.getValue() < skillThreshold) {

                continue;
            }

            Long skillId =
                    entry.getKey();

            List<AssessmentOptionSignal> skillSignals =
                    signalRepository
                            .findBySkillId(skillId);

            for (AssessmentOptionSignal signal :
                    skillSignals) {

                Skill skill =
                        signal.getSkill();

                if (skill != null) {

                    calculatedSkills.add(skill);

                    break;
                }
            }
        }

        profile.getStudentSkills().clear();

        for (Skill skill :
                calculatedSkills) {

            profile.addStudentSkill(skill);
        }

        profileRepository.save(profile);
    }

    private String normalizeSkillName(
            String skillName) {

        if (skillName == null) {
            return "";
        }

        return skillName
                .trim()
                .toLowerCase()
                .replace("-", " ")
                .replace("_", " ")
                .replaceAll("\\s+", " ");
    }

    private boolean isValidOption(
            String option) {

        if (option == null) {

            return false;
        }

        String normalized =
                option.trim().toUpperCase();

        return normalized.equals("A")
                || normalized.equals("B")
                || normalized.equals("C")
                || normalized.equals("D");
    }
}