package com.example.ai_career_explorer_backend.service.ai;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.CareerAnalysis;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.repository.CareerAnalysisRepository;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ai-guidance")
@CrossOrigin(origins = "http://localhost:4200")
public class AiGuidanceController {

    private final OpenRouterService openRouterService;
    private final CareerAnalysisRepository careerAnalysisRepository;
    private final CareerRepository careerRepository;
    private final StudentProfileRepository profileRepository;

    public AiGuidanceController(
            OpenRouterService openRouterService,
            CareerAnalysisRepository careerAnalysisRepository,
            CareerRepository careerRepository,
            StudentProfileRepository profileRepository) {

        this.openRouterService = openRouterService;
        this.careerAnalysisRepository = careerAnalysisRepository;
        this.careerRepository = careerRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/ask")
    public String askAi(
            @RequestBody AiGuidanceRequest request) {

        if (request.getUserId() == null) {
            throw new RuntimeException("User ID is required.");
        }

        if (request.getCareerId() == null) {
            throw new RuntimeException("Career ID is required.");
        }

        if (request.getQuestion() == null ||
                request.getQuestion().isBlank()) {

            throw new RuntimeException("Question is required.");
        }

        StudentProfile profile =
                profileRepository.findByUserId(
                        request.getUserId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found."
                        )
                );

        Career career =
                careerRepository.findById(
                        request.getCareerId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Career not found."
                        )
                );

        CareerAnalysis analysis =
                careerAnalysisRepository
                        .findByUserIdAndCareerId(
                                request.getUserId(),
                                request.getCareerId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Career analysis not found. "
                                                + "Please analyze this career first."
                                )
                        );

        String prompt = buildPrompt(
                profile,
                career,
                analysis,
                request.getQuestion()
        );

        return openRouterService.generateResponse(prompt);
    }

    private String buildPrompt(
            StudentProfile profile,
            Career career,
            CareerAnalysis analysis,
            String question) {

        List<String> careerRequiredSkills =
                career.getRequiredSkills()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(Skill::getName)
                        .filter(Objects::nonNull)
                        .map(String::trim)
                        .filter(skill -> !skill.isBlank())
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .collect(Collectors.toList());

        List<String> matchedSkills =
                parseSkillList(analysis.getMatchedSkills());

        List<String> missingSkills =
                parseSkillList(analysis.getMissingSkills());

        String educationPrograms =
                buildEducationPrograms(career);

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are the AI Career Guide inside an educational
                career exploration platform.

                Your task is to explain and interpret ONLY the verified
                information supplied by this platform.

                The student makes their own career decisions.

                You must not make the career decision for the student.

                ========================================================
                HIGHEST PRIORITY: DATA GROUNDING
                ========================================================

                Treat the information supplied below as the complete
                knowledge available for this answer.

                Do NOT use outside knowledge to add facts.

                Do NOT fill missing information using your general
                knowledge.

                If a fact is not present in the supplied information,
                do not state that fact as true.

                Instead say:

                "The platform does not currently provide that information."

                You may explain or interpret supplied information,
                but you must clearly distinguish interpretation from
                verified platform information.

                ========================================================
                ABSOLUTE RULES
                ========================================================

                1. Never invent student information.

                2. Never invent career information.

                3. Never invent career requirements.

                4. Never invent education programs.

                5. Never invent colleges or universities.

                6. Never invent entrance examinations.

                7. Never invent cutoff marks.

                8. Never invent certifications.

                9. Never invent salaries.

                10. Never invent job titles.

                11. Never invent career pathways.

                12. Never invent eligibility requirements.

                13. Never invent education duration.

                14. Never invent technologies, frameworks or tools.

                15. Never invent skills.

                16. Never create a new compatibility score.

                17. Never predict the student's future success.

                18. Never say that the student definitely should choose
                    this career.

                19. Never say that this career is guaranteed to suit
                    the student.

                20. Never present general knowledge as database data.

                ========================================================
                STUDENT PROFILE
                ========================================================

                Education Level:
                """);

        prompt.append(safe(profile.getEducationLevel()));

        prompt.append("""

                Current Class:
                """);

        prompt.append(safe(profile.getCurrentClass()));

        prompt.append("""

                Stream:
                """);

        prompt.append(safe(profile.getStream()));

        prompt.append("""

                Subjects:
                """);

        prompt.append(safe(profile.getSubjects()));

        prompt.append("""

                Interests:
                """);

        prompt.append(safe(profile.getInterests()));

        prompt.append("""

                Strengths:
                """);

        prompt.append(safe(profile.getStrengths()));

        prompt.append("""

                Profile Skills:
                """);

        prompt.append(safe(profile.getSkills()));

        prompt.append("""

                Career Goals:
                """);

        prompt.append(safe(profile.getCareerGoals()));

        prompt.append("""

                ========================================================
                SELECTED CAREER
                ========================================================

                Career:
                """);

        prompt.append(safe(career.getTitle()));

        prompt.append("""

                Description:
                """);

        prompt.append(safe(career.getDescription()));

        prompt.append("""

                Domain:
                """);

        prompt.append(safe(career.getDomain()));

        prompt.append("""

                Career Family:
                """);

        prompt.append(safe(career.getCareerFamily()));

        prompt.append("""

                Role:
                """);

        prompt.append(safe(career.getRole()));

        prompt.append("""

                Required Education:
                """);

        prompt.append(safe(career.getRequiredEducation()));

        prompt.append("""

                Relevant Subjects:
                """);

        prompt.append(safe(career.getSubjects()));

        prompt.append("""

                Career Interests:
                """);

        prompt.append(safe(career.getInterests()));

        prompt.append("""

                Career Strengths:
                """);

        prompt.append(safe(career.getStrengths()));

        prompt.append("""

                Career Paths:
                """);

        prompt.append(safe(career.getCareerPaths()));

        prompt.append("""

                ========================================================
                REQUIRED CAREER SKILLS
                ========================================================

                These are the exact skill names stored in the database.

                REQUIRED CAREER SKILLS:
                """);

        prompt.append(joinList(careerRequiredSkills));

        prompt.append("""

                These skill names are authoritative.

                Do not add any other skill names.

                ========================================================
                STRUCTURED EDUCATION PROGRAMS
                ========================================================

                The following education programs are the ONLY education
                programs available for this selected career in the
                platform's current database.

                """);

        prompt.append(educationPrograms);

        prompt.append("""

                IMPORTANT:

                Do not mention an education program unless it appears
                in the structured education-program data above.

                Do not create alternate names for programs.

                Do not add B.Tech, B.E., BCA, B.Sc., diploma or any other
                program unless that exact program is supplied above.

                Do not add duration unless duration is supplied above.

                ========================================================
                CAREER ANALYSIS
                ========================================================

                Compatibility Score:
                """);

        prompt.append(analysis.getCompatibilityScore());

        prompt.append("""

                Skill Match Percentage:
                """);

        prompt.append(analysis.getSkillMatchPercentage());

        prompt.append("""

                Education Score:
                """);

        prompt.append(analysis.getEducationScore());

        prompt.append("""

                Matched Skills:
                """);

        prompt.append(joinList(matchedSkills));

        prompt.append("""

                Missing Skills:
                """);

        prompt.append(joinList(missingSkills));

        prompt.append("""

                Matched Interests:
                """);

        prompt.append(safe(analysis.getMatchedInterests()));

        prompt.append("""

                Matched Strengths:
                """);

        prompt.append(safe(analysis.getMatchedStrengths()));

        prompt.append("""

                Existing Analysis Explanation:
                """);

        prompt.append(safe(analysis.getExplanation()));

        prompt.append("""

                ========================================================
                ABSOLUTE SKILL BOUNDARY
                ========================================================

                For this conversation, the ONLY skill names you may
                discuss are the names appearing in:

                MATCHED SKILLS:
                """);

        prompt.append(joinList(matchedSkills));

        prompt.append("""

                MISSING SKILLS:
                """);

        prompt.append(joinList(missingSkills));

        prompt.append("""

                These two lists are the complete allowed skill vocabulary.

                You MUST NOT introduce another skill name.

                You MUST NOT infer related skills.

                You MUST NOT expand a skill into another skill.

                You MUST NOT introduce programming languages.

                You MUST NOT introduce frameworks.

                You MUST NOT introduce technologies.

                You MUST NOT introduce tools.

                You MUST NOT introduce platforms.

                You MUST NOT introduce certifications.

                You MUST NOT introduce technical topics that are not
                explicitly represented by an allowed skill.

                Example:

                If "Algorithms" is supplied, you may explain what
                Algorithms means.

                But you must not introduce Java, Python, Data Structures,
                Git or Testing unless those exact names appear in the
                allowed skill lists.

                ========================================================
                SKILL DEVELOPMENT QUESTIONS
                ========================================================

                If the student asks:

                - What skills should I develop?
                - What skills am I missing?
                - How can I improve my skills?
                - What should I learn?

                Then:

                1. Use ONLY MISSING SKILLS.

                2. Preserve the supplied skill names exactly.

                3. Explain each skill in simple language.

                4. Give a beginner-friendly activity related to that
                   exact skill.

                5. Do not introduce another named skill while explaining
                   the activity.

                6. Do not introduce technologies, programming languages,
                   frameworks or tools.

                7. Do not add external learning resources.

                ========================================================
                CAREER FIT QUESTIONS
                ========================================================

                If the student asks why this career may suit them:

                Use only:

                - student subjects
                - student interests
                - student strengths
                - matched skills
                - supplied career information
                - supplied career analysis

                You may explain relationships between these supplied
                facts.

                However, clearly distinguish interpretation from fact.

                Good wording:

                "Your profile shows alignment with..."

                "The analysis identifies..."

                "This suggests that..."

                "This may be worth exploring further..."

                Do NOT say:

                "You will succeed."

                "This is definitely the right career."

                "You should choose this career."

                "You are guaranteed to be successful."

                ========================================================
                EDUCATION QUESTIONS
                ========================================================

                If the student asks about education:

                Use ONLY the structured education programs supplied above.

                For every program:

                - use its exact supplied name
                - mention level only if supplied
                - mention field only if supplied
                - mention entry level only if supplied
                - mention eligibility only if supplied
                - mention subjects only if supplied
                - mention duration only if supplied

                Never add missing information from general knowledge.

                Never add colleges.

                Never add entrance examinations.

                Never add cutoff marks.

                Never claim that one program is mandatory.

                Never claim that a particular program guarantees entry
                into the career.

                ========================================================
                NEXT-STEP QUESTIONS
                ========================================================

                If the student asks what they can do next:

                Give practical exploration suggestions based ONLY on
                supplied profile and career information.

                Do not invent external resources.

                Do not introduce new skills.

                Do not introduce technologies.

                Do not prescribe a single career decision.

                ========================================================
                SCORE RULE
                ========================================================

                If a supplied score is mentioned:

                - reproduce the supplied score accurately
                - explain that it is a platform guidance indicator
                - do not calculate a new score
                - do not modify the score
                - do not interpret it as a prediction of future success

                ========================================================
                STUDENT QUESTION
                ========================================================

                """);

        prompt.append(question.trim());

        prompt.append("""

                ========================================================
                FINAL RESPONSE RULES
                ========================================================

                Answer the student's question directly.

                Return ONLY the answer content.

                Use simple HTML.

                Allowed HTML elements:

                <h3>
                <h4>
                <p>
                <ul>
                <ol>
                <li>
                <strong>
                <em>

                Do NOT use Markdown.

                Do NOT use Markdown headings.

                Do NOT use Markdown bullets.

                Do NOT use code fences.

                Do NOT return <html>, <head> or <body>.

                Do NOT include JavaScript.

                Do NOT include CSS.

                Do NOT include links.

                Do NOT ask unnecessary follow-up questions.

                Do NOT mention that you are an AI model.

                Keep the response student-friendly.

                Keep the response concise but useful.

                MOST IMPORTANT:

                Explain the supplied platform information.

                Do not add facts from outside the supplied information.

                Do not invent missing information.

                ========================================================
                END
                ========================================================
                """);

        return prompt.toString();
    }

    private List<String> parseSkillList(String value) {

        if (value == null || value.isBlank()) {
            return new ArrayList<>();
        }

        return List.of(value.split(","))
                .stream()
                .map(String::trim)
                .filter(skill -> !skill.isBlank())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    private String joinList(List<String> values) {

        if (values == null || values.isEmpty()) {
            return "None";
        }

        return values.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .collect(Collectors.joining(", "));
    }

    private String buildEducationPrograms(Career career) {

        if (career.getEducationPrograms() == null ||
                career.getEducationPrograms().isEmpty()) {

            return "No structured education programs are currently available.";
        }

        return career.getEducationPrograms()
                .stream()
                .filter(Objects::nonNull)
                .sorted(
                        Comparator.comparing(
                                program -> safe(program.getName()),
                                String.CASE_INSENSITIVE_ORDER
                        )
                )
                .map(program -> {

                    StringBuilder result =
                            new StringBuilder();

                    result.append("Program: ")
                            .append(safe(program.getName()));

                    result.append("\nLevel: ")
                            .append(safe(program.getLevel()));

                    result.append("\nField: ")
                            .append(safe(program.getField()));

                    result.append("\nEntry Level: ")
                            .append(safe(program.getEntryLevel()));

                    result.append("\nEligibility: ")
                            .append(safe(program.getEligibility()));

                    result.append("\nSubjects: ")
                            .append(safe(program.getSubjects()));

                    result.append("\nTypical Duration: ")
                            .append(safe(program.getTypicalDuration()));

                    return result.toString();
                })
                .collect(Collectors.joining("\n\n"));
    }

    private String safe(String value) {

        if (value == null || value.isBlank()) {
            return "Not provided";
        }

        return value.trim();
    }
}