package com.example.ai_career_explorer_backend.service.ai;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.CareerAnalysis;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.repository.CareerAnalysisRepository;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;

import org.springframework.stereotype.Service;

@Service
public class AiGuidanceService {

    private final OpenRouterService openRouterService;
    private final StudentProfileRepository profileRepository;
    private final CareerRepository careerRepository;
    private final CareerAnalysisRepository analysisRepository;

    public AiGuidanceService(
            OpenRouterService openRouterService,
            StudentProfileRepository profileRepository,
            CareerRepository careerRepository,
            CareerAnalysisRepository analysisRepository) {

        this.openRouterService = openRouterService;
        this.profileRepository = profileRepository;
        this.careerRepository = careerRepository;
        this.analysisRepository = analysisRepository;
    }

    public String generateCareerGuidance(
            Long userId,
            Long careerId,
            String studentQuestion) {

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

        CareerAnalysis analysis =
                analysisRepository
                        .findByUserIdAndCareerId(
                                userId,
                                careerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Career analysis not found"));

        String prompt =
                buildPrompt(
                        profile,
                        career,
                        analysis,
                        studentQuestion
                );

        return openRouterService.generateResponse(prompt);
    }

    private String buildPrompt(
            StudentProfile profile,
            Career career,
            CareerAnalysis analysis,
            String studentQuestion) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are the AI guidance assistant inside an application
                called AI Career Explorer.

                Your role is to help a student understand a career based
                ONLY on the verified career and student information
                provided below.

                IMPORTANT RULES:

                1. Do not invent career requirements, qualifications,
                   skills, subjects, courses, or education pathways.

                2. Do not change or recalculate the compatibility score.

                3. Treat the career database and career analysis as the
                   authoritative source for factual career information.

                4. Use the student's profile and analysis to personalize
                   the explanation.

                5. A compatibility score is a guidance indicator, not a
                   prediction of career success.

                6. Be encouraging but realistic.

                7. If information is not available in the supplied data,
                   clearly say that it is not available rather than
                   inventing an answer.

                8. Give practical next steps when appropriate.

                STUDENT PROFILE
                ----------------
                Education level:
                """);

        prompt.append(value(profile.getEducationLevel()));

        prompt.append("\nCurrent class:\n");
        prompt.append(value(profile.getCurrentClass()));

        prompt.append("\nStream:\n");
        prompt.append(value(profile.getStream()));

        prompt.append("\nSubjects:\n");
        prompt.append(value(profile.getSubjects()));

        prompt.append("\nInterests:\n");
        prompt.append(value(profile.getInterests()));

        prompt.append("\nStrengths:\n");
        prompt.append(value(profile.getStrengths()));

        prompt.append("\nSkills:\n");
        prompt.append(value(profile.getSkills()));

        prompt.append("\nCareer goals:\n");
        prompt.append(value(profile.getCareerGoals()));

        prompt.append("""

                
                STUDENT PROFILE SKILLS FROM THE SKILL DATABASE
                ----------------------------------------------
                """);

        if (profile.getStudentSkills() == null ||
                profile.getStudentSkills().isEmpty()) {

            prompt.append("No structured skills recorded.");

        } else {

            for (Skill skill : profile.getStudentSkills()) {

                if (skill == null ||
                        skill.getName() == null) {
                    continue;
                }

                prompt.append("- ");
                prompt.append(skill.getName());

                if (skill.getCategory() != null &&
                        !skill.getCategory().isBlank()) {

                    prompt.append(" [");
                    prompt.append(skill.getCategory());
                    prompt.append("]");
                }

                prompt.append("\n");
            }
        }

        prompt.append("""

                
                CAREER
                ------
                Title:
                """);

        prompt.append(value(career.getTitle()));

        prompt.append("\nDescription:\n");
        prompt.append(value(career.getDescription()));

        prompt.append("\nCareer family:\n");
        prompt.append(value(career.getCareerFamily()));

        prompt.append("\nRole:\n");
        prompt.append(value(career.getRole()));

        prompt.append("\nRequired education:\n");
        prompt.append(value(career.getRequiredEducation()));

        prompt.append("\nRelevant subjects:\n");
        prompt.append(value(career.getSubjects()));

        prompt.append("\nCareer skills:\n");
        prompt.append(value(career.getSkills()));

        prompt.append("\nRelevant interests:\n");
        prompt.append(value(career.getInterests()));

        prompt.append("\nRelevant strengths:\n");
        prompt.append(value(career.getStrengths()));

        prompt.append("\nCareer paths:\n");
        prompt.append(value(career.getCareerPaths()));

        prompt.append("""

                
                CAREER ANALYSIS
                ---------------
                Compatibility score:
                """);

        prompt.append(analysis.getCompatibilityScore());

        prompt.append("\nSkill match percentage:\n");
        prompt.append(analysis.getSkillMatchPercentage());

        prompt.append("\nEducation pathway score:\n");
        prompt.append(analysis.getEducationScore());

        prompt.append("\nMatched skills:\n");
        prompt.append(value(analysis.getMatchedSkills()));

        prompt.append("\nMissing skills:\n");
        prompt.append(value(analysis.getMissingSkills()));

        prompt.append("\nMatched interests:\n");
        prompt.append(value(analysis.getMatchedInterests()));

        prompt.append("\nMatched strengths:\n");
        prompt.append(value(analysis.getMatchedStrengths()));

        prompt.append("\nExisting analysis explanation:\n");
        prompt.append(value(analysis.getExplanation()));

        prompt.append("""

                
                STUDENT QUESTION
                ----------------
                """);

        prompt.append(
                studentQuestion == null ||
                        studentQuestion.isBlank()
                        ? "Please explain how this career fits my current profile and what I should focus on next."
                        : studentQuestion.trim()
        );

        prompt.append("""

                
                RESPONSE INSTRUCTIONS
                ---------------------
                Answer the student's question directly.

                Use the supplied profile and analysis to explain the
                situation in a personalized way.

                Prefer clear sections or short bullet points when useful.

                Do not mention internal database fields, API calls,
                prompts, or implementation details.
                """);

        return prompt.toString();
    }

    private String value(String text) {

        if (text == null || text.isBlank()) {
            return "Not provided";
        }

        return text.trim();
    }
}