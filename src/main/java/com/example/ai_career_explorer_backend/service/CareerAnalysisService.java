package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.CareerAnalysis;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.entity.User;
import com.example.ai_career_explorer_backend.repository.CareerAnalysisRepository;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;
import com.example.ai_career_explorer_backend.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CareerAnalysisService {

    private final CareerAnalysisRepository analysisRepository;
    private final CareerRepository careerRepository;
    private final StudentProfileRepository profileRepository;
    private final UserRepository userRepository;

    public CareerAnalysisService(
            CareerAnalysisRepository analysisRepository,
            CareerRepository careerRepository,
            StudentProfileRepository profileRepository,
            UserRepository userRepository) {

        this.analysisRepository = analysisRepository;
        this.careerRepository = careerRepository;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public CareerAnalysis analyzeCareer(
            Long userId,
            Long careerId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

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


        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        List<Skill> requiredSkills =
                new ArrayList<>(career.getRequiredSkills());

        List<Skill> studentSkills =
                new ArrayList<>(profile.getStudentSkills());

        for (Skill requiredSkill : requiredSkills) {

            boolean matched =
                    studentSkills.stream()
                            .anyMatch(studentSkill ->
                                    sameSkill(
                                            studentSkill,
                                            requiredSkill));

            if (matched) {
                matchedSkills.add(
                        requiredSkill.getName());
            } else {
                missingSkills.add(
                        requiredSkill.getName());
            }
        }

        int skillMatchPercentage = 0;

        if (!requiredSkills.isEmpty()) {

            skillMatchPercentage =
                    (int) Math.round(
                            (matchedSkills.size() * 100.0)
                                    / requiredSkills.size()
                    );
        }

        /*
         * ---------------------------------------------------------
         * 2. INTEREST MATCH
         * ---------------------------------------------------------
         */

        List<String> matchedInterests =
                findTextMatches(
                        profile.getInterests(),
                        career.getInterests()
                );

        int interestScore =
                calculateTextMatchPercentage(
                        profile.getInterests(),
                        career.getInterests()
                );

        /*
         * ---------------------------------------------------------
         * 3. STRENGTH MATCH
         * ---------------------------------------------------------
         */

        List<String> matchedStrengths =
                findTextMatches(
                        profile.getStrengths(),
                        career.getStrengths()
                );

        int strengthScore =
                calculateTextMatchPercentage(
                        profile.getStrengths(),
                        career.getStrengths()
                );

        /*
         * ---------------------------------------------------------
         * 4. SUBJECT MATCH
         * ---------------------------------------------------------
         */

        List<String> matchedSubjects =
                findTextMatches(
                        profile.getSubjects(),
                        career.getSubjects()
                );

        int subjectScore =
                calculateTextMatchPercentage(
                        profile.getSubjects(),
                        career.getSubjects()
                );

        /*
         * ---------------------------------------------------------
         * 5. EDUCATION / PATHWAY MATCH
         * ---------------------------------------------------------
         */

        int educationScore =
                calculateEducationCompatibility(
                        profile,
                        career
                );

        /*
         * ---------------------------------------------------------
         * 6. EDUCATION EXPLANATION
         * ---------------------------------------------------------
         */

        String educationExplanation =
                buildEducationExplanation(
                        profile,
                        career,
                        educationScore
                );

        /*
         * ---------------------------------------------------------
         * 7. FINAL COMPATIBILITY SCORE
         *
         * Skills      = 40%
         * Interests   = 20%
         * Strengths   = 15%
         * Subjects    = 15%
         * Education   = 10%
         * ---------------------------------------------------------
         */

        int compatibilityScore =
                (int) Math.round(

                        skillMatchPercentage * 0.40

                                + interestScore * 0.20

                                + strengthScore * 0.15

                                + subjectScore * 0.15

                                + educationScore * 0.10
                );

        /*
         * Keep score between 0 and 100.
         */

        compatibilityScore =
                Math.max(
                        0,
                        Math.min(
                                100,
                                compatibilityScore
                        )
                );

        /*
         * ---------------------------------------------------------
         * 8. GENERAL EXPLANATION
         * ---------------------------------------------------------
         */

        String explanation =
                buildExplanation(
                        career,
                        matchedSkills,
                        missingSkills,
                        matchedInterests,
                        matchedStrengths,
                        matchedSubjects,
                        skillMatchPercentage,
                        interestScore,
                        strengthScore,
                        subjectScore,
                        educationScore
                );

        /*
         * ---------------------------------------------------------
         * 9. CREATE OR UPDATE ANALYSIS
         * ---------------------------------------------------------
         */

        CareerAnalysis analysis =
                analysisRepository
                        .findByUserIdAndCareerId(
                                userId,
                                careerId
                        )
                        .orElse(new CareerAnalysis());

        analysis.setUser(user);

        analysis.setCareer(career);

        analysis.setCompatibilityScore(
                compatibilityScore);

        analysis.setSkillMatchPercentage(
                skillMatchPercentage);

        analysis.setEducationScore(
                educationScore);

        analysis.setEducationExplanation(
                educationExplanation
        );

        analysis.setMatchedSkills(
                String.join(
                        ", ",
                        matchedSkills
                ));

        analysis.setMissingSkills(
                String.join(
                        ", ",
                        missingSkills
                ));

        analysis.setMatchedInterests(
                String.join(
                        ", ",
                        matchedInterests
                ));

        analysis.setMatchedStrengths(
                String.join(
                        ", ",
                        matchedStrengths
                ));

        analysis.setExplanation(
                explanation
        );

        analysis.setAnalyzedAt(
                LocalDateTime.now()
        );

        return analysisRepository.save(analysis);
    }

    public List<CareerAnalysis> analyzeAllCareers(
            Long userId) {

        if (!userRepository.existsById(userId)) {

            throw new RuntimeException(
                    "User not found");
        }

        if (!profileRepository.existsByUserId(userId)) {

            throw new RuntimeException(
                    "Student profile not found");
        }

        List<Career> careers =
                careerRepository.findAll();

        List<CareerAnalysis> analyses =
                new ArrayList<>();

        for (Career career : careers) {

            analyses.add(
                    analyzeCareer(
                            userId,
                            career.getId()
                    )
            );
        }

        return analyses;
    }

    public List<CareerAnalysis> getStudentAnalyses(
            Long userId) {

        if (!userRepository.existsById(userId)) {

            throw new RuntimeException(
                    "User not found");
        }

        return analysisRepository.findByUserId(userId);
    }

    public CareerAnalysis getStudentCareerAnalysis(
            Long userId,
            Long careerId) {

        return analysisRepository
                .findByUserIdAndCareerId(
                        userId,
                        careerId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Career analysis not found"));
    }

    /*
     * ---------------------------------------------------------
     * SKILL COMPARISON
     * ---------------------------------------------------------
     */

    private boolean sameSkill(
            Skill first,
            Skill second) {

        if (first == null || second == null) {
            return false;
        }

        if (first.getName() == null ||
                second.getName() == null) {

            return false;
        }

        return first.getName()
                .trim()
                .equalsIgnoreCase(
                        second.getName().trim()
                );
    }

    /*
     * ---------------------------------------------------------
     * TEXT MATCHING
     * ---------------------------------------------------------
     */

    private List<String> findTextMatches(
            String studentText,
            String careerText) {

        List<String> matches =
                new ArrayList<>();

        if (studentText == null ||
                studentText.isBlank() ||
                careerText == null ||
                careerText.isBlank()) {

            return matches;
        }

        String[] studentValues =
                studentText.split(",");

        String[] careerValues =
                careerText.split(",");

        for (String studentValue : studentValues) {

            String cleanedStudent =
                    normalizeText(studentValue);

            if (cleanedStudent.isBlank()) {
                continue;
            }

            for (String careerValue : careerValues) {

                String cleanedCareer =
                        normalizeText(careerValue);

                if (cleanedCareer.isBlank()) {
                    continue;
                }

                if (textsMatch(
                        cleanedStudent,
                        cleanedCareer)) {

                    matches.add(
                            studentValue.trim()
                    );

                    break;
                }
            }
        }

        return matches;
    }

    /*
     * ---------------------------------------------------------
     * TEXT MATCH PERCENTAGE
     * ---------------------------------------------------------
     */

    private int calculateTextMatchPercentage(
            String studentText,
            String careerText) {

        if (studentText == null ||
                studentText.isBlank() ||
                careerText == null ||
                careerText.isBlank()) {

            return 0;
        }

        String[] studentValues =
                studentText.split(",");

        int total = 0;

        for (String value : studentValues) {

            if (!value.trim().isBlank()) {
                total++;
            }
        }

        if (total == 0) {
            return 0;
        }

        int matches =
                findTextMatches(
                        studentText,
                        careerText
                ).size();

        return (int) Math.round(
                (matches * 100.0) / total
        );
    }

    /*
     * ---------------------------------------------------------
     * EDUCATION COMPATIBILITY
     * ---------------------------------------------------------
     */

    private int calculateEducationCompatibility(
            StudentProfile profile,
            Career career) {

        String educationLevel =
                normalizeText(
                        profile.getEducationLevel()
                );

        String currentClass =
                normalizeText(
                        profile.getCurrentClass()
                );

        String careerEducation =
                normalizeText(
                        career.getRequiredEducation()
                );

        String careerPaths =
                normalizeText(
                        career.getCareerPaths()
                );

        if (educationLevel.isBlank() &&
                currentClass.isBlank()) {

            return 0;
        }

        String combinedCareerPath =
                careerEducation + " " + careerPaths;

        /*
         * Higher secondary / class 11-12 students.
         */

        if (containsAny(
                educationLevel,
                "higher secondary",
                "higher_secondary",
                "12th",
                "class 12",
                "class 11"
        ) ||
                containsAny(
                        currentClass,
                        "class 11",
                        "class 12",
                        "11",
                        "12"
                )) {

            if (containsAny(
                    combinedCareerPath,
                    "after 12th",
                    "after 12",
                    "b.tech",
                    "b.e.",
                    "bca",
                    "b.sc",
                    "bachelor",
                    "undergraduate",
                    "degree"
            )) {

                return 100;
            }

            if (containsAny(
                    combinedCareerPath,
                    "after 10th",
                    "diploma",
                    "polytechnic",
                    "iti"
            )) {

                return 70;
            }

            return 50;
        }

        /*
         * Class 10 / secondary students.
         */

        if (containsAny(
                educationLevel,
                "secondary",
                "class 10",
                "10th"
        ) ||
                containsAny(
                        currentClass,
                        "class 10",
                        "10"
                )) {

            if (containsAny(
                    combinedCareerPath,
                    "after 10th",
                    "diploma",
                    "polytechnic",
                    "iti"
            )) {

                return 100;
            }

            if (containsAny(
                    combinedCareerPath,
                    "after 12th",
                    "after 12",
                    "b.tech",
                    "b.e.",
                    "bca",
                    "b.sc",
                    "bachelor",
                    "undergraduate"
            )) {

                return 80;
            }

            return 50;
        }

        /*
         * College / undergraduate students.
         */

        if (containsAny(
                educationLevel,
                "undergraduate",
                "college",
                "bachelor"
        )) {

            if (containsAny(
                    combinedCareerPath,
                    "undergraduate",
                    "bachelor",
                    "degree",
                    "after 12th",
                    "after 12"
            )) {

                return 100;
            }

            return 70;
        }

        /*
         * Unknown or unsupported education stage.
         */

        return 50;
    }

    /*
     * ---------------------------------------------------------
     * EDUCATION EXPLANATION
     * ---------------------------------------------------------
     *
     * Generates a student-friendly explanation from the actual
     * profile and career data.
     *
     * This does NOT change the education score.
     */

    private String buildEducationExplanation(
            StudentProfile profile,
            Career career,
            int educationScore) {

        List<String> reasons =
                new ArrayList<>();

        String educationLevel =
                normalizeText(
                        profile.getEducationLevel()
                );

        String currentClass =
                normalizeText(
                        profile.getCurrentClass()
                );

        String stream =
                normalizeText(
                        profile.getStream()
                );

        String studentSubjects =
                profile.getSubjects();

        String careerSubjects =
                career.getSubjects();

        String requiredEducation =
                career.getRequiredEducation();

        /*
         * -----------------------------------------------------
         * EDUCATION STAGE
         * -----------------------------------------------------
         */

        if (containsAny(
                educationLevel,
                "higher secondary",
                "higher_secondary",
                "12th",
                "class 12"
        ) ||
                containsAny(
                        currentClass,
                        "class 12",
                        "12"
                )) {

            if (containsAny(
                    requiredEducation,
                    "after 12th",
                    "after 12",
                    "b.tech",
                    "b.e.",
                    "bca",
                    "b.sc",
                    "bachelor",
                    "undergraduate",
                    "degree"
            )) {

                reasons.add(
                        "Your current Class 12 education stage aligns with the general education pathway for this career."
                );

            } else {

                reasons.add(
                        "You are currently in Class 12, so additional education or training may be needed for this career."
                );
            }

        } else if (containsAny(
                educationLevel,
                "secondary",
                "class 10",
                "10th"
        ) ||
                containsAny(
                        currentClass,
                        "class 10",
                        "10"
                )) {

            if (containsAny(
                    requiredEducation,
                    "after 10th",
                    "diploma",
                    "polytechnic",
                    "iti"
            )) {

                reasons.add(
                        "Your current Class 10 education stage aligns with the pathway described for this career."
                );

            } else {

                reasons.add(
                        "You would generally need further education after Class 10 before entering the pathway for this career."
                );
            }

        } else if (!educationLevel.isBlank()) {

            reasons.add(
                    "Your current education stage has been compared with the education pathway described for this career."
            );
        }

        /*
         * -----------------------------------------------------
         * STREAM
         * -----------------------------------------------------
         */

        if (!stream.isBlank()) {

            if (stream.contains("science")) {

                if (containsAny(
                        careerSubjects,
                        "mathematics",
                        "math",
                        "physics",
                        "chemistry",
                        "biology",
                        "computer science",
                        "programming"
                ) ||
                        containsAny(
                                requiredEducation,
                                "engineering",
                                "b.tech",
                                "b.e.",
                                "computer science"
                        )) {

                    reasons.add(
                            "Your Science stream is relevant to the academic preparation commonly associated with this career."
                    );

                } else {

                    reasons.add(
                            "Your Science stream provides a broad academic foundation, although the career may require additional specialized study."
                    );
                }

            } else if (stream.contains("commerce")) {

                if (containsAny(
                        careerSubjects,
                        "accounting",
                        "accountancy",
                        "economics",
                        "business",
                        "finance"
                ) ||
                        containsAny(
                                requiredEducation,
                                "commerce",
                                "accounting",
                                "ca"
                        )) {

                    reasons.add(
                            "Your Commerce stream is relevant to the academic preparation commonly associated with this career."
                    );

                } else {

                    reasons.add(
                            "Your Commerce stream provides a general foundation, although additional specialized study may be required."
                    );
                }

            } else if (stream.contains("arts") ||
                    stream.contains("humanities")) {

                if (containsAny(
                        careerSubjects,
                        "history",
                        "geography",
                        "political",
                        "psychology",
                        "sociology",
                        "language",
                        "literature",
                        "communication",
                        "teaching"
                )) {

                    reasons.add(
                            "Your Arts/Humanities stream is relevant to the academic preparation associated with this career."
                    );

                } else {

                    reasons.add(
                            "Your Arts/Humanities stream provides a general foundation, although additional specialized study may be required."
                    );
                }
            }
        }

        /*
         * -----------------------------------------------------
         * SUBJECTS
         * -----------------------------------------------------
         */

        List<String> matchedSubjects =
                findTextMatches(
                        studentSubjects,
                        careerSubjects
                );

        if (!matchedSubjects.isEmpty()) {

            reasons.add(
                    "Relevant subjects in your profile include: "
                            + String.join(
                                    ", ",
                                    matchedSubjects
                            )
                            + "."
            );

        } else if (studentSubjects != null &&
                !studentSubjects.isBlank() &&
                careerSubjects != null &&
                !careerSubjects.isBlank()) {

            reasons.add(
                    "Your current subjects do not directly match the career subjects stored in the career pathway data."
            );
        }

        /*
         * -----------------------------------------------------
         * SCORE INTERPRETATION
         * -----------------------------------------------------
         */

        if (educationScore >= 90) {

            reasons.add(
                    "Overall, the available education information shows strong alignment with this career pathway."
            );

        } else if (educationScore >= 70) {

            reasons.add(
                    "Overall, the education pathway shows reasonable alignment, with some additional preparation potentially required."
            );

        } else if (educationScore >= 50) {

            reasons.add(
                    "Overall, the education information shows partial alignment, so the next education steps should be checked carefully."
            );

        } else {

            reasons.add(
                    "Overall, the current education information has limited alignment with the stored pathway for this career."
            );
        }

        return String.join(" ", reasons);
    }

    /*
     * ---------------------------------------------------------
     * TEXT NORMALIZATION
     * ---------------------------------------------------------
     */

    private String normalizeText(String text) {

        if (text == null) {
            return "";
        }

        return text
                .trim()
                .toLowerCase(Locale.ROOT)
                .replace("-", " ")
                .replace("_", " ")
                .replaceAll("\\s+", " ");
    }

    /*
     * ---------------------------------------------------------
     * FLEXIBLE TEXT COMPARISON
     * ---------------------------------------------------------
     */

    private boolean textsMatch(
            String first,
            String second) {

        if (first.equals(second)) {
            return true;
        }

        if (first.contains(second) ||
                second.contains(first)) {

            return true;
        }

        return false;
    }

    /*
     * ---------------------------------------------------------
     * CONTAINS ANY
     * ---------------------------------------------------------
     */

    private boolean containsAny(
            String text,
            String... values) {

        if (text == null || text.isBlank()) {
            return false;
        }

        for (String value : values) {

            if (value == null ||
                    value.isBlank()) {

                continue;
            }

            if (text.contains(
                    normalizeText(value))) {

                return true;
            }
        }

        return false;
    }

    /*
     * ---------------------------------------------------------
     * GENERAL EXPLANATION
     * ---------------------------------------------------------
     */

    private String buildExplanation(
            Career career,
            List<String> matchedSkills,
            List<String> missingSkills,
            List<String> matchedInterests,
            List<String> matchedStrengths,
            List<String> matchedSubjects,
            int skillScore,
            int interestScore,
            int strengthScore,
            int subjectScore,
            int educationScore) {

        StringBuilder explanation =
                new StringBuilder();

        explanation.append(
                "Your current profile was compared with the "
        );

        explanation.append(
                career.getTitle()
        );

        explanation.append(
                " career requirements. "
        );

        /*
         * Skills
         */

        if (!matchedSkills.isEmpty()) {

            explanation.append(
                    "Matched skills include: "
            );

            explanation.append(
                    String.join(
                            ", ",
                            matchedSkills
                    )
            );

            explanation.append(". ");
        }

        if (!missingSkills.isEmpty()) {

            explanation.append(
                    "Skills that may need development include: "
            );

            explanation.append(
                    String.join(
                            ", ",
                            missingSkills
                    )
            );

            explanation.append(". ");
        }

        /*
         * Interests
         */

        if (!matchedInterests.isEmpty()) {

            explanation.append(
                    "Your profile also shows relevant interests: "
            );

            explanation.append(
                    String.join(
                            ", ",
                            matchedInterests
                    )
            );

            explanation.append(". ");
        }

        /*
         * Strengths
         */

        if (!matchedStrengths.isEmpty()) {

            explanation.append(
                    "Relevant strengths include: "
            );

            explanation.append(
                    String.join(
                            ", ",
                            matchedStrengths
                    )
            );

            explanation.append(". ");
        }

        /*
         * Subjects
         */

        if (!matchedSubjects.isEmpty()) {

            explanation.append(
                    "Relevant subjects include: "
            );

            explanation.append(
                    String.join(
                            ", ",
                            matchedSubjects
                    )
            );

            explanation.append(". ");
        }

        /*
         * Education
         */

        if (educationScore >= 100) {

            explanation.append(
                    "Your current education stage aligns "
                            + "with the general pathway described "
                            + "for this career. "
            );

        } else if (educationScore >= 70) {

            explanation.append(
                    "Your current education stage can support "
                            + "a pathway toward this career, although "
                            + "additional education or training may "
                            + "be required. "
            );

        } else if (educationScore > 0) {

            explanation.append(
                    "The available education information provides "
                            + "a partial pathway match for this career. "
            );
        }

        /*
         * Overall scoring explanation.
         */

        explanation.append(
                "The compatibility score combines skill, interest, "
                        + "strength, subject and education signals; "
                        + "it is a guidance indicator rather than a "
                        + "prediction of career success."
        );

        return explanation
                .toString()
                .trim();
    }
}