package com.example.ai_career_explorer_backend.config;

import com.example.ai_career_explorer_backend.entity.AssessmentOptionSignal;
import com.example.ai_career_explorer_backend.entity.AssessmentQuestion;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.repository.AssessmentOptionSignalRepository;
import com.example.ai_career_explorer_backend.repository.AssessmentQuestionRepository;
import com.example.ai_career_explorer_backend.repository.SkillRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AssessmentDataInitializer {

    @Bean
    @Transactional
    CommandLineRunner initializeAssessmentData(
            AssessmentQuestionRepository questionRepository,
            AssessmentOptionSignalRepository signalRepository,
            SkillRepository skillRepository) {

        return args -> {

            // Prevent duplicate questions
            if (questionRepository.count() > 0) {
                System.out.println("Assessment questions already exist. Skipping assessment initialization.");
                return;
            }

            System.out.println("Starting assessment data initialization...");

            AssessmentQuestion q1 = createQuestion(
                    questionRepository,
                    "Which type of activity do you enjoy the most?",
                    "INTEREST",
                    1,
                    "Writing code or building applications",
                    "Solving logical or mathematical problems",
                    "Talking and communicating with people",
                    "Creating designs or visual content"
            );

            addSignal(signalRepository, q1, "A",
                    skillRepository, "Programming", 3);

            addSignal(signalRepository, q1, "B",
                    skillRepository, "Logical Thinking", 3);

            addSignal(signalRepository, q1, "C",
                    skillRepository, "Communication", 3);

            addSignal(signalRepository, q1, "D",
                    skillRepository, "Creativity", 3);

            AssessmentQuestion q2 = createQuestion(
                    questionRepository,
                    "When you face a difficult problem, what do you usually do?",
                    "STRENGTH",
                    2,
                    "Break it into smaller problems and solve them",
                    "Search for patterns and analyze the information",
                    "Ask someone and discuss possible solutions",
                    "Try different creative approaches"
            );

            addSignal(signalRepository, q2, "A",
                    skillRepository, "Problem Solving", 3);

            addSignal(signalRepository, q2, "B",
                    skillRepository, "Analytical Thinking", 3);

            addSignal(signalRepository, q2, "C",
                    skillRepository, "Communication", 3);

            addSignal(signalRepository, q2, "D",
                    skillRepository, "Creativity", 3);

            AssessmentQuestion q3 = createQuestion(
                    questionRepository,
                    "Which task sounds most interesting to you?",
                    "INTEREST",
                    3,
                    "Building a website or application",
                    "Working with data and finding useful information",
                    "Designing a user interface",
                    "Finding security problems in a system"
            );

            addSignal(signalRepository, q3, "A",
                    skillRepository, "Web Development", 3);

            addSignal(signalRepository, q3, "B",
                    skillRepository, "Data Analysis", 3);

            addSignal(signalRepository, q3, "C",
                    skillRepository, "UI Design", 3);

            addSignal(signalRepository, q3, "D",
                    skillRepository, "Cybersecurity", 3);

            AssessmentQuestion q4 = createQuestion(
                    questionRepository,
                    "How do you approach learning something new?",
                    "LEARNING_STYLE",
                    4,
                    "I practice it by building something",
                    "I understand the theory first",
                    "I discuss it with others",
                    "I experiment and try different methods"
            );

            addSignal(signalRepository, q4, "A",
                    skillRepository, "Programming", 2);

            addSignal(signalRepository, q4, "B",
                    skillRepository, "Logical Thinking", 2);

            addSignal(signalRepository, q4, "C",
                    skillRepository, "Communication", 2);

            addSignal(signalRepository, q4, "D",
                    skillRepository, "Creativity", 2);

            AssessmentQuestion q5 = createQuestion(
                    questionRepository,
                    "Which activity would you prefer during a project?",
                    "INTEREST",
                    5,
                    "Developing the backend of an application",
                    "Analyzing information and creating reports",
                    "Designing the user experience",
                    "Managing tasks and coordinating the team"
            );

            addSignal(signalRepository, q5, "A",
                    skillRepository, "Backend Development", 3);

            addSignal(signalRepository, q5, "B",
                    skillRepository, "Data Analysis", 3);

            addSignal(signalRepository, q5, "C",
                    skillRepository, "UX Research", 3);

            addSignal(signalRepository, q5, "D",
                    skillRepository, "Communication", 3);


            AssessmentQuestion q6 = createQuestion(
                    questionRepository,
                    "What do you enjoy doing when using a computer?",
                    "INTEREST",
                    6,
                    "Writing programs",
                    "Working with numbers and data",
                    "Creating graphics and designs",
                    "Learning how systems and networks work"
            );

            addSignal(signalRepository, q6, "A",
                    skillRepository, "Programming", 3);

            addSignal(signalRepository, q6, "B",
                    skillRepository, "Statistics", 3);

            addSignal(signalRepository, q6, "C",
                    skillRepository, "Graphic Design", 3);

            addSignal(signalRepository, q6, "D",
                    skillRepository, "Networking", 3);

            AssessmentQuestion q7 = createQuestion(
                    questionRepository,
                    "Which statement describes you best?",
                    "STRENGTH",
                    7,
                    "I keep trying until I solve a problem",
                    "I pay close attention to small details",
                    "I enjoy explaining ideas to others",
                    "I like finding new and unusual solutions"
            );

            addSignal(signalRepository, q7, "A",
                    skillRepository, "Persistence", 3);

            addSignal(signalRepository, q7, "B",
                    skillRepository, "Attention to Detail", 3);

            addSignal(signalRepository, q7, "C",
                    skillRepository, "Communication", 3);

            addSignal(signalRepository, q7, "D",
                    skillRepository, "Creativity", 3);


            AssessmentQuestion q8 = createQuestion(
                    questionRepository,
                    "What would you enjoy working on?",
                    "INTEREST",
                    8,
                    "Developing mobile or web applications",
                    "Creating machine learning solutions",
                    "Protecting systems from cyber threats",
                    "Designing digital experiences"
            );

            addSignal(signalRepository, q8, "A",
                    skillRepository, "Web Development", 3);

            addSignal(signalRepository, q8, "B",
                    skillRepository, "Machine Learning", 3);

            addSignal(signalRepository, q8, "C",
                    skillRepository, "Cybersecurity", 3);

            addSignal(signalRepository, q8, "D",
                    skillRepository, "UX Research", 3);

            AssessmentQuestion q9 = createQuestion(
                    questionRepository,
                    "When debugging a program, what do you prefer?",
                    "STRENGTH",
                    9,
                    "Check the code carefully to find the mistake",
                    "Analyze the logic step by step",
                    "Search documentation and examples",
                    "Ask another developer for ideas"
            );

            addSignal(signalRepository, q9, "A",
                    skillRepository, "Attention to Detail", 3);

            addSignal(signalRepository, q9, "B",
                    skillRepository, "Logical Thinking", 3);

            addSignal(signalRepository, q9, "C",
                    skillRepository, "Problem Solving", 2);

            addSignal(signalRepository, q9, "D",
                    skillRepository, "Communication", 2);

            AssessmentQuestion q10 = createQuestion(
                    questionRepository,
                    "Which type of project would you like to build?",
                    "CAREER_INTEREST",
                    10,
                    "A software application",
                    "A data analysis dashboard",
                    "A visual design project",
                    "A cybersecurity solution"
            );

            addSignal(signalRepository, q10, "A",
                    skillRepository, "Programming", 3);

            addSignal(signalRepository, q10, "B",
                    skillRepository, "Data Visualization", 3);

            addSignal(signalRepository, q10, "C",
                    skillRepository, "UI Design", 3);

            addSignal(signalRepository, q10, "D",
                    skillRepository, "Cybersecurity", 3);


            AssessmentQuestion q11 = createQuestion(
                    questionRepository,
                    "Which skill would you most like to improve?",
                    "SKILL_INTEREST",
                    11,
                    "Programming and coding",
                    "Data analysis and statistics",
                    "Communication and presentation",
                    "Design and creativity"
            );

            addSignal(signalRepository, q11, "A",
                    skillRepository, "Programming", 3);

            addSignal(signalRepository, q11, "B",
                    skillRepository, "Data Analysis", 3);

            addSignal(signalRepository, q11, "C",
                    skillRepository, "Communication", 3);

            addSignal(signalRepository, q11, "D",
                    skillRepository, "Creativity", 3);

            AssessmentQuestion q12 = createQuestion(
                    questionRepository,
                    "How do you feel about working with technology?",
                    "INTEREST",
                    12,
                    "I enjoy learning new technologies",
                    "I like understanding how technology works",
                    "I prefer using technology to solve real problems",
                    "I enjoy creating things using technology"
            );

            addSignal(signalRepository, q12, "A",
                    skillRepository, "Programming", 2);

            addSignal(signalRepository, q12, "B",
                    skillRepository, "Logical Thinking", 2);

            addSignal(signalRepository, q12, "C",
                    skillRepository, "Problem Solving", 2);

            addSignal(signalRepository, q12, "D",
                    skillRepository, "Creativity", 2);

            AssessmentQuestion q13 = createQuestion(
                    questionRepository,
                    "What type of work environment would you prefer?",
                    "WORK_STYLE",
                    13,
                    "Working independently on technical tasks",
                    "Working with a team",
                    "Working directly with customers or people",
                    "Working on creative projects"
            );

            addSignal(signalRepository, q13, "A",
                    skillRepository, "Logical Thinking", 2);

            addSignal(signalRepository, q13, "B",
                    skillRepository, "Communication", 2);

            addSignal(signalRepository, q13, "C",
                    skillRepository, "Communication", 3);

            addSignal(signalRepository, q13, "D",
                    skillRepository, "Creativity", 3);

            AssessmentQuestion q14 = createQuestion(
                    questionRepository,
                    "What motivates you when working on a difficult task?",
                    "MOTIVATION",
                    14,
                    "Learning something new",
                    "Solving a challenging problem",
                    "Creating something useful",
                    "Helping other people"
            );

            addSignal(signalRepository, q14, "A",
                    skillRepository, "Curiosity", 2);

            addSignal(signalRepository, q14, "B",
                    skillRepository, "Problem Solving", 3);

            addSignal(signalRepository, q14, "C",
                    skillRepository, "Creativity", 3);

            addSignal(signalRepository, q14, "D",
                    skillRepository, "Communication", 2);


            AssessmentQuestion q15 = createQuestion(
                    questionRepository,
                    "Which result would make you most satisfied?",
                    "CAREER_INTEREST",
                    15,
                    "A working software application",
                    "A useful data report",
                    "A beautiful digital design",
                    "A successful team project"
            );

            addSignal(signalRepository, q15, "A",
                    skillRepository, "Programming", 3);

            addSignal(signalRepository, q15, "B",
                    skillRepository, "Data Analysis", 3);

            addSignal(signalRepository, q15, "C",
                    skillRepository, "UI Design", 3);

            addSignal(signalRepository, q15, "D",
                    skillRepository, "Communication", 3);


            System.out.println("Assessment data initialization completed.");
            System.out.println("Total assessment questions: " + questionRepository.count());
            System.out.println("Total assessment signals: " + signalRepository.count());
        };
    }

    private AssessmentQuestion createQuestion(
            AssessmentQuestionRepository repository,
            String questionText,
            String category,
            int orderNumber,
            String optionA,
            String optionB,
            String optionC,
            String optionD) {

        AssessmentQuestion question = new AssessmentQuestion(
                questionText,
                category,
                optionA,
                optionB,
                optionC,
                optionD
        );

        question.setOrderNumber(orderNumber);
        question.setAssessmentType("CORE");
        question.setCareerFamily(null);
        question.setActive(true);

        return repository.save(question);
    }

    private void addSignal(
            AssessmentOptionSignalRepository signalRepository,
            AssessmentQuestion question,
            String selectedOption,
            SkillRepository skillRepository,
            String skillName,
            int weight) {

        Skill skill = skillRepository
                .findByNameIgnoreCase(skillName)
                .orElseThrow(() -> new RuntimeException(
                        "Skill not found: " + skillName
                ));

        AssessmentOptionSignal signal = new AssessmentOptionSignal(
                question,
                selectedOption,
                skill,
                weight
        );

        signalRepository.save(signal);
    }
}