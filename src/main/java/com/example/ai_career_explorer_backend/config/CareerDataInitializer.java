package com.example.ai_career_explorer_backend.config;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.repository.CareerRepository;
import com.example.ai_career_explorer_backend.repository.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CareerDataInitializer {

    @Bean
    CommandLineRunner initializeCareerData(
            CareerRepository careerRepository,
            SkillRepository skillRepository) {

        return args -> {

            addSkill(
                    skillRepository,
                    "Programming",
                    "Ability to write, understand and modify computer programs using programming languages.",
                    "Technical"
            );

            addSkill(
                    skillRepository,
                    "Problem Solving",
                    "Ability to understand problems, analyze possible solutions and apply effective approaches to solve them.",
                    "Cognitive"
            );

            addSkill(
                    skillRepository,
                    "Logical Thinking",
                    "Ability to reason systematically, identify relationships and patterns, and reach conclusions using logic.",
                    "Cognitive"
            );

            addSkill(
                    skillRepository,
                    "Communication",
                    "Ability to express ideas clearly, listen effectively, and communicate information with others.",
                    "Soft Skill"
            );

            addSkill(
                    skillRepository,
                    "Debugging",
                    "Ability to identify, analyze and fix errors or unexpected behavior in software and technical systems.",
                    "Technical"
            );

            addSkill(
                    skillRepository,
                    "Creativity",
                    "Ability to generate original ideas, approaches and solutions and to think beyond conventional methods.",
                    "Creative"
            );

            addSkill(
                    skillRepository,
                    "Analytical Thinking",
                    "Ability to examine information, identify patterns and relationships, evaluate alternatives, and reach reasoned conclusions.",
                    "Cognitive"
            );

            addSkill(
                    skillRepository,
                    "Attention to Detail",
                    "Ability to notice important details, identify errors, and maintain accuracy when working with information or tasks.",
                    "Cognitive"
            );

            addSkill(
                    skillRepository,
                    "Persistence",
                    "Ability to continue working toward a goal despite difficulties, mistakes, setbacks or challenging tasks.",
                    "Personal"
            );

            addSkill(
                    skillRepository,
                    "Curiosity",
                    "Willingness to explore new ideas, ask questions, learn unfamiliar concepts, and understand how things work.",
                    "Personal"
            );

            addSkill(
                    skillRepository,
                    "Data Analysis",
                    "Ability to examine, clean, interpret and communicate insights from data.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "SQL",
                    "Ability to retrieve, filter, join and analyze data using SQL databases.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Excel",
                    "Ability to use spreadsheets, formulas, functions and data tools for analysis.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Statistics",
                    "Understanding of statistical concepts, distributions, probability and data interpretation.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Machine Learning",
                    "Ability to build and evaluate systems that learn patterns from data.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Artificial Intelligence",
                    "Understanding and application of AI methods for solving practical problems.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Data Visualization",
                    "Ability to communicate data insights through charts, dashboards and visual representations.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Python",
                    "Programming ability using Python for software development, automation, data and AI.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Deep Learning",
                    "Understanding of neural networks and deep learning techniques.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Natural Language Processing",
                    "Ability to work with computational techniques for analyzing and processing human language.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Data Engineering",
                    "Ability to build systems and pipelines for collecting, transforming and managing data.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Database Management",
                    "Ability to design, maintain and work with structured databases.",
                    "Data & AI"
            );

            addSkill(
                    skillRepository,
                    "Java",
                    "Programming ability using Java for application and backend development.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "JavaScript",
                    "Programming ability for interactive web and application development.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "TypeScript",
                    "Typed programming ability commonly used for scalable web applications.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Web Development",
                    "Ability to build and maintain websites and web applications.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Frontend Development",
                    "Ability to build user interfaces and client-side web applications.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Backend Development",
                    "Ability to build server-side applications, APIs and backend services.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Software Testing",
                    "Ability to test software for correctness, reliability and defects.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Algorithms",
                    "Ability to design and reason about step-by-step computational solutions.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Data Structures",
                    "Understanding of structures used to organize and efficiently process data.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Git",
                    "Ability to use version control for managing source code and collaboration.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Cloud Computing",
                    "Understanding and use of computing resources and services delivered through the cloud.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "DevOps",
                    "Ability to combine software development and operations practices for reliable delivery.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "API Development",
                    "Ability to design and develop interfaces that allow software systems to communicate.",
                    "Software & IT"
            );

            addSkill(
                    skillRepository,
                    "Networking",
                    "Understanding of computer networks, protocols and network communication.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Cybersecurity",
                    "Knowledge of protecting systems, networks and information from security threats.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Linux",
                    "Ability to work with Linux operating systems and command-line environments.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Risk Analysis",
                    "Ability to identify, assess and communicate risks affecting systems or organizations.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Ethical Hacking",
                    "Understanding of authorized security testing and vulnerability assessment.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Digital Forensics",
                    "Ability to examine digital evidence and investigate technology-related incidents.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Security Analysis",
                    "Ability to identify vulnerabilities, threats and security weaknesses.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Cryptography",
                    "Understanding of techniques used to protect information through encryption and related methods.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "Incident Response",
                    "Ability to respond to and investigate cybersecurity incidents.",
                    "Cybersecurity"
            );

            addSkill(
                    skillRepository,
                    "UI Design",
                    "Ability to design clear, usable and visually effective digital interfaces.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "UX Research",
                    "Ability to study user needs, behaviors and experiences to inform product design.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Wireframing",
                    "Ability to create simplified layouts for websites or digital products.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Prototyping",
                    "Ability to create interactive or visual representations of proposed products.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Figma",
                    "Ability to use Figma for interface design, prototyping and collaborative design work.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Graphic Design",
                    "Ability to create visual communication using layout, typography, imagery and design principles.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Typography",
                    "Ability to select and arrange type effectively for visual communication.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Branding",
                    "Ability to develop visual and strategic elements that communicate a brand identity.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Visual Communication",
                    "Ability to communicate ideas and information effectively through visual media.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Adobe Creative Tools",
                    "Ability to use professional creative software for visual and graphic work.",
                    "Design"
            );

            addSkill(
                    skillRepository,
                    "Engineering Design",
                    "Ability to apply engineering principles to design technical solutions.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "CAD",
                    "Ability to use computer-aided design tools to create technical drawings and models.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Mathematics",
                    "Ability to apply mathematical concepts to technical and analytical problems.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Physics",
                    "Understanding and application of physical principles to technical problems.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Technical Drawing",
                    "Ability to create and interpret technical drawings and engineering representations.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Construction Planning",
                    "Ability to plan, coordinate and organize construction activities and resources.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Mechanical Design",
                    "Ability to design mechanical components, systems and machines.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Electrical Engineering",
                    "Knowledge of electrical systems, circuits and engineering principles.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Electronics",
                    "Understanding of electronic circuits, components and systems.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Thermodynamics",
                    "Understanding of heat, energy and thermodynamic systems.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Structural Analysis",
                    "Ability to analyze structures and their response to loads and forces.",
                    "Engineering"
            );

            addSkill(
                    skillRepository,
                    "Accounting",
                    "Ability to record, classify and interpret financial transactions.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Financial Analysis",
                    "Ability to analyze financial information to support business and investment decisions.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Taxation",
                    "Knowledge of tax concepts, calculations, compliance and regulations.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Auditing",
                    "Ability to examine financial records and processes for accuracy and compliance.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Numerical Reasoning",
                    "Ability to interpret and solve problems involving numbers and quantitative information.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Financial Modeling",
                    "Ability to build quantitative models for financial planning and analysis.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Investment Analysis",
                    "Ability to evaluate investments using financial and market information.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Budgeting",
                    "Ability to plan, monitor and manage financial resources.",
                    "Finance & Accounting"
            );

            addSkill(
                    skillRepository,
                    "Business Analysis",
                    "Ability to understand business problems and identify practical improvements.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Project Management",
                    "Ability to plan, coordinate and monitor projects, resources and timelines.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Leadership",
                    "Ability to guide people, coordinate work and support achievement of shared goals.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Decision Making",
                    "Ability to evaluate information and choose appropriate courses of action.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Strategic Thinking",
                    "Ability to consider long-term goals, alternatives and organizational consequences.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Entrepreneurship",
                    "Ability to identify opportunities and develop new products, services or ventures.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Business Communication",
                    "Ability to communicate clearly and professionally in business contexts.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Negotiation",
                    "Ability to communicate and reach agreements between parties with different interests.",
                    "Business & Management"
            );

            addSkill(
                    skillRepository,
                    "Content Marketing",
                    "Ability to create and use content to communicate with and engage target audiences.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "SEO",
                    "Understanding of techniques used to improve visibility of websites in search engines.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Social Media",
                    "Ability to plan, create and manage communication through social media platforms.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Marketing Analytics",
                    "Ability to analyze marketing data to understand performance and audience behavior.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Copywriting",
                    "Ability to write persuasive and engaging text for marketing and communication.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Public Relations",
                    "Ability to manage communication and relationships between organizations and audiences.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Video Production",
                    "Ability to plan, create and edit video content.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Photography",
                    "Ability to create and communicate ideas through photographic images.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Journalism",
                    "Ability to research, verify and communicate news and information.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Storytelling",
                    "Ability to communicate ideas and information through compelling narratives.",
                    "Marketing & Media"
            );

            addSkill(
                    skillRepository,
                    "Teaching",
                    "Ability to facilitate learning and explain concepts to students.",
                    "Education"
            );

            addSkill(
                    skillRepository,
                    "Subject Knowledge",
                    "Strong understanding of a particular academic or professional subject area.",
                    "Education"
            );

            addSkill(
                    skillRepository,
                    "Classroom Management",
                    "Ability to organize and manage an effective learning environment.",
                    "Education"
            );

            addSkill(
                    skillRepository,
                    "Lesson Planning",
                    "Ability to design structured learning activities and lessons.",
                    "Education"
            );

            addSkill(
                    skillRepository,
                    "Assessment Design",
                    "Ability to create assessments that measure learning and understanding.",
                    "Education"
            );

            addSkill(
                    skillRepository,
                    "Mentoring",
                    "Ability to support another person's learning, development and growth.",
                    "Education"
            );

            addSkill(
                    skillRepository,
                    "Public Speaking",
                    "Ability to communicate ideas clearly and confidently to an audience.",
                    "Communication"
            );

            addSkill(
                    skillRepository,
                    "Clinical Reasoning",
                    "Ability to use clinical information and evidence to support healthcare decisions.",
                    "Healthcare"
            );

            addSkill(
                    skillRepository,
                    "Patient Care",
                    "Ability to provide appropriate and compassionate care to patients.",
                    "Healthcare"
            );

            addSkill(
                    skillRepository,
                    "Medical Knowledge",
                    "Understanding of human health, disease and medical principles.",
                    "Healthcare"
            );

            addSkill(
                    skillRepository,
                    "Anatomy",
                    "Understanding of the structure of the human body.",
                    "Healthcare"
            );

            addSkill(
                    skillRepository,
                    "Pharmacology",
                    "Understanding of medicines, their effects and appropriate use.",
                    "Healthcare"
            );

            addSkill(
                    skillRepository,
                    "Laboratory Skills",
                    "Ability to perform and interpret laboratory procedures safely and accurately.",
                    "Healthcare"
            );

            addSkill(
                    skillRepository,
                    "Clinical Communication",
                    "Ability to communicate effectively with patients and healthcare professionals.",
                    "Healthcare"
            );

            addSkill(
                    skillRepository,
                    "Scientific Analysis",
                    "Ability to analyze scientific information and draw evidence-based conclusions.",
                    "Science & Research"
            );

            addSkill(
                    skillRepository,
                    "Research",
                    "Ability to investigate questions systematically using appropriate methods and evidence.",
                    "Science & Research"
            );

            addSkill(
                    skillRepository,
                    "Laboratory Research",
                    "Ability to conduct controlled scientific experiments and laboratory investigations.",
                    "Science & Research"
            );

            addSkill(
                    skillRepository,
                    "Experimental Design",
                    "Ability to design experiments to investigate scientific questions.",
                    "Science & Research"
            );

            addSkill(
                    skillRepository,
                    "Scientific Writing",
                    "Ability to communicate scientific findings clearly and accurately.",
                    "Science & Research"
            );

            addSkill(
                    skillRepository,
                    "Critical Thinking",
                    "Ability to evaluate evidence, assumptions and arguments logically.",
                    "Science & Research"
            );

            addSkill(
                    skillRepository,
                    "Legal Research",
                    "Ability to research laws, regulations, cases and legal information.",
                    "Law & Public Service"
            );

            addSkill(
                    skillRepository,
                    "Legal Reasoning",
                    "Ability to analyze legal issues and apply relevant principles.",
                    "Law & Public Service"
            );

            addSkill(
                    skillRepository,
                    "Case Analysis",
                    "Ability to examine facts, evidence and arguments in a case.",
                    "Law & Public Service"
            );

            addSkill(
                    skillRepository,
                    "Policy Analysis",
                    "Ability to examine policies, their effects and possible alternatives.",
                    "Law & Public Service"
            );

            addSkill(
                    skillRepository,
                    "Public Administration",
                    "Knowledge and ability to manage programs and services in public organizations.",
                    "Law & Public Service"
            );

            addSkill(
                    skillRepository,
                    "Civic Knowledge",
                    "Understanding of government, public institutions and civic systems.",
                    "Law & Public Service"
            );

            addSkill(
                    skillRepository,
                    "Architecture Design",
                    "Ability to develop architectural concepts and spatial designs.",
                    "Architecture & Construction"
            );

            addSkill(
                    skillRepository,
                    "Building Design",
                    "Ability to design functional and aesthetically appropriate buildings.",
                    "Architecture & Construction"
            );

            addSkill(
                    skillRepository,
                    "3D Modeling",
                    "Ability to create three-dimensional digital models of objects or spaces.",
                    "Architecture & Construction"
            );

            addSkill(
                    skillRepository,
                    "Urban Planning",
                    "Ability to plan and analyze the development and organization of urban areas.",
                    "Architecture & Construction"
            );

            addSkill(
                    skillRepository,
                    "Quantity Surveying",
                    "Ability to estimate and manage construction quantities and costs.",
                    "Architecture & Construction"
            );

            addSkill(
                    skillRepository,
                    "Agricultural Science",
                    "Understanding of crops, soil, farming systems and agricultural processes.",
                    "Agriculture & Environment"
            );

            addSkill(
                    skillRepository,
                    "Soil Science",
                    "Understanding of soil properties, management and its role in agriculture.",
                    "Agriculture & Environment"
            );

            addSkill(
                    skillRepository,
                    "Environmental Science",
                    "Understanding of environmental systems, impacts and sustainability.",
                    "Agriculture & Environment"
            );

            addSkill(
                    skillRepository,
                    "Environmental Analysis",
                    "Ability to analyze environmental conditions, data and impacts.",
                    "Agriculture & Environment"
            );

            addSkill(
                    skillRepository,
                    "Sustainability",
                    "Ability to consider environmental, social and economic sustainability in decisions.",
                    "Agriculture & Environment"
            );

            addSkill(
                    skillRepository,
                    "Customer Service",
                    "Ability to understand customer needs and provide helpful service.",
                    "Hospitality & Tourism"
            );

            addSkill(
                    skillRepository,
                    "Hospitality Management",
                    "Ability to manage services and operations in hospitality environments.",
                    "Hospitality & Tourism"
            );

            addSkill(
                    skillRepository,
                    "Event Management",
                    "Ability to plan, organize and coordinate events.",
                    "Hospitality & Tourism"
            );

            addSkill(
                    skillRepository,
                    "Tourism Management",
                    "Ability to plan and manage tourism-related services and experiences.",
                    "Hospitality & Tourism"
            );

            addSkill(
                    skillRepository,
                    "Travel Planning",
                    "Ability to organize travel itineraries, logistics and experiences.",
                    "Hospitality & Tourism"
            );

            addCareer(
                    careerRepository,
                    1L,
                    "Software Developer",
                    "Designs, develops, tests and maintains software applications and systems.",
                    "Engineering & Technology",
                    "Computer & IT",
                    "Software Development",
                    "B.Tech/B.E. in Computer Science or related field; BCA; B.Sc. Computer Science; Diploma in Computer Engineering or related pathways",
                    "Mathematics, Computer Science, Physics",
                    "Programming, Problem Solving, Logical Thinking, Debugging, Communication",
                    "Technology, Programming, Building Applications, Problem Solving",
                    "Logical Thinking, Curiosity, Persistence, Attention to Detail",
                    "After 10th: 11th-12th Science → degree; Diploma/Polytechnic → lateral entry or further study; ITI/vocational → skill-focused technology routes. After 12th: B.Tech/B.E., BCA, B.Sc. Computer Science and other related programs."
            );

            addCareer(
                    careerRepository,
                    2L,
                    "Data Analyst",
                    "Analyzes data to identify patterns, trends and insights that help organizations make decisions.",
                    "Engineering & Technology",
                    "Computer & IT",
                    "Data & Analytics",
                    "B.Sc. Statistics, Mathematics, Computer Science; BCA; B.Tech/B.E.; BBA or related programs with data skills",
                    "Mathematics, Statistics, Computer Science",
                    "Data Analysis, Excel, SQL, Statistics, Problem Solving, Communication",
                    "Numbers, Data, Research, Finding Patterns",
                    "Analytical Thinking, Curiosity, Attention to Detail",
                    "After 12th: degree in Mathematics, Statistics, Computer Science, Engineering or related field → data skills → internships/projects → Data Analyst."
            );

            addCareer(
                    careerRepository,
                    3L,
                    "Cybersecurity Analyst",
                    "Protects computer systems, networks and data from security threats and unauthorized access.",
                    "Engineering & Technology",
                    "Computer & IT",
                    "Cybersecurity",
                    "B.Tech/B.E. Computer Science or IT; BCA; B.Sc. Computer Science/IT; diploma and specialized cybersecurity pathways",
                    "Computer Science, Mathematics, Physics",
                    "Networking, Cybersecurity, Linux, Problem Solving, Programming, Risk Analysis",
                    "Technology, Security, Computers, Investigation",
                    "Analytical Thinking, Persistence, Attention to Detail, Curiosity",
                    "After 12th: Computer Science/IT degree or diploma → networking and security skills → certifications/projects → cybersecurity roles."
            );

            addCareer(
                    careerRepository,
                    4L,
                    "UI/UX Designer",
                    "Designs user interfaces and experiences that make digital products easy, useful and enjoyable to use.",
                    "Design & Creative",
                    "Digital Design",
                    "UI/UX Design",
                    "Degree or diploma in Design, UI/UX, Graphic Design, Computer Science or related field; portfolio-based pathways are also possible",
                    "Computer Science, Art, Design",
                    "UI Design, UX Research, Wireframing, Prototyping, Figma, Communication",
                    "Design, Creativity, Technology, Understanding People",
                    "Creativity, Empathy, Observation, Communication",
                    "After 12th: Design degree/diploma or another relevant degree → UI/UX learning → portfolio → internships/projects → UI/UX Designer."
            );

            addCareer(
                    careerRepository,
                    5L,
                    "Mechanical Engineer",
                    "Designs, develops and improves machines, mechanical systems and manufacturing processes.",
                    "Engineering & Technology",
                    "Mechanical Engineering",
                    "Mechanical Engineering",
                    "B.Tech/B.E. Mechanical Engineering; Diploma in Mechanical Engineering with further study or career progression",
                    "Mathematics, Physics, Chemistry",
                    "Engineering Design, CAD, Problem Solving, Mathematics, Physics, Technical Drawing",
                    "Machines, Engineering, Manufacturing, Design",
                    "Problem Solving, Practical Thinking, Precision, Curiosity",
                    "After 10th: 11th-12th Science → engineering degree; Diploma/Polytechnic → further study or technical career routes. After 12th: B.Tech/B.E. Mechanical Engineering."
            );

            addCareer(
                    careerRepository,
                    6L,
                    "Civil Engineer",
                    "Plans, designs and supervises construction and infrastructure projects such as buildings, roads and bridges.",
                    "Engineering & Technology",
                    "Civil Engineering",
                    "Civil Engineering",
                    "B.Tech/B.E. Civil Engineering; Diploma in Civil Engineering with further study or technical career progression",
                    "Mathematics, Physics, Chemistry",
                    "Engineering Design, Mathematics, Construction Planning, CAD, Problem Solving",
                    "Buildings, Infrastructure, Construction, Design",
                    "Practical Thinking, Planning, Problem Solving, Attention to Detail",
                    "After 10th: Science pathway or Diploma/Polytechnic → Civil Engineering degree or technical route. After 12th: B.Tech/B.E. Civil Engineering."
            );

            addCareer(
                    careerRepository,
                    7L,
                    "Chartered Accountant",
                    "Works with accounting, taxation, auditing, financial reporting and financial management.",
                    "Business & Finance",
                    "Accounting & Finance",
                    "Chartered Accountancy",
                    "CA pathway after 12th through the Institute of Chartered Accountants of India; commerce background can be helpful but is not the only possible route",
                    "Accountancy, Mathematics, Economics, Business Studies",
                    "Accounting, Financial Analysis, Taxation, Auditing, Numerical Reasoning, Communication",
                    "Finance, Business, Numbers, Economics",
                    "Attention to Detail, Discipline, Analytical Thinking, Accuracy",
                    "After 12th: enter the CA pathway → foundation/intermediate stages as applicable → articleship and final qualification requirements → Chartered Accountant."
            );

            addCareer(
                    careerRepository,
                    8L,
                    "Digital Marketer",
                    "Uses digital channels such as search engines, social media, websites and email to promote products, services or organizations.",
                    "Business & Management",
                    "Marketing",
                    "Digital Marketing",
                    "Degree or diploma in Marketing, Business, Communications, Media or related field; specialized digital marketing training is also possible",
                    "Business Studies, Economics, English, Computer Science",
                    "Content Marketing, SEO, Social Media, Analytics, Communication, Creativity",
                    "Marketing, Social Media, Business, Content Creation",
                    "Creativity, Communication, Curiosity, Adaptability",
                    "After 12th: business/marketing/media degree or other degree → digital marketing skills → portfolio/projects → internships → digital marketing roles."
            );

            addCareer(
                    careerRepository,
                    9L,
                    "Graphic Designer",
                    "Creates visual designs for digital media, branding, advertising, publications and other communication needs.",
                    "Design & Creative",
                    "Visual Design",
                    "Graphic Design",
                    "Degree or diploma in Graphic Design, Visual Communication, Fine Arts or related field; portfolio-based learning pathways are also possible",
                    "Art, Design, Computer Science",
                    "Graphic Design, Typography, Branding, Adobe Creative Tools, Visual Communication",
                    "Art, Creativity, Design, Visual Communication",
                    "Creativity, Visual Thinking, Observation, Attention to Detail",
                    "After 12th: design/fine arts/visual communication degree or diploma → portfolio development → internships/freelance projects → Graphic Designer."
            );

            addCareer(
                    careerRepository,
                    10L,
                    "Teacher",
                    "Helps students learn subjects and develop academic, practical and personal skills.",
                    "Education",
                    "Teaching & Learning",
                    "School Teaching",
                    "Relevant subject degree followed by appropriate teacher education/qualification requirements; pathways vary by school level and institution",
                    "Any relevant academic subject",
                    "Communication, Teaching, Subject Knowledge, Classroom Management, Planning",
                    "Teaching, Learning, Helping Others, Subject Knowledge",
                    "Patience, Communication, Empathy, Organization",
                    "After 12th: relevant undergraduate study → appropriate teacher education/qualification → teaching opportunities. Exact requirements depend on the level and institution."
            );

            addCareer(
                    careerRepository,
                    11L,
                    "Data Scientist",
                    "Analyzes complex data to identify patterns, generate insights and support data-driven decision making.",
                    "Computer & IT",
                    "Engineering & Technology",
                    "Data Science",
                    "Bachelor's degree in Computer Science, Data Science, Statistics, Mathematics or related field",
                    "Mathematics, Statistics, Computer Science",
                    "Python, Statistics, Data Analysis, Machine Learning, SQL",
                    "Technology, Mathematics, Problem Solving, Data",
                    "Analytical Thinking, Logical Thinking, Curiosity, Problem Solving",
                    "Data Analyst → Data Scientist → Senior Data Scientist → Lead Data Scientist"
            );

            addCareer(
                    careerRepository,
                    12L,
                    "DevOps Engineer",
                    "Automates software development and deployment processes while improving reliability and collaboration.",
                    "Computer & IT",
                    "Engineering & Technology",
                    "DevOps Engineering",
                    "Bachelor's degree in Computer Science, Information Technology or related field",
                    "Computer Science, Mathematics",
                    "Linux, Git, Cloud Computing, CI/CD, Automation",
                    "Technology, Automation, Software Development, Infrastructure",
                    "Problem Solving, Logical Thinking, Persistence, Attention to Detail",
                    "Software Developer → DevOps Engineer → Senior DevOps Engineer → DevOps Architect"
            );

            addCareer(
                    careerRepository,
                    13L,
                    "Entrepreneur",
                    "Creates and develops businesses by identifying opportunities and building products or services.",
                    "Entrepreneurship",
                    "Business & Management",
                    "Business Entrepreneurship",
                    "No single mandatory degree; business, management or relevant professional education can be useful",
                    "Business Studies, Economics, Mathematics",
                    "Business Planning, Communication, Marketing, Financial Literacy",
                    "Business, Innovation, Leadership, Creativity",
                    "Creativity, Leadership, Risk Awareness, Persistence",
                    "Business Idea → Startup Founder → Business Owner → Entrepreneurial Leader"
            );

            addCareer(
                    careerRepository,
                    14L,
                    "Government Administrative Officer",
                    "Supports government programs, administration and public services.",
                    "Public Administration",
                    "Law & Public Service",
                    "Government Administration",
                    "Bachelor's degree followed by the relevant recruitment or competitive examination process",
                    "Political Science, Economics, History, Mathematics",
                    "Administration, Communication, Documentation, Planning",
                    "Public Service, Government, Management, Society",
                    "Organization, Communication, Responsibility, Problem Solving",
                    "Graduate → Administrative Officer → Senior Officer → Administrative Manager"
            );

            addCareer(
                    careerRepository,
                    15L,
                    "Civil Services Officer",
                    "Works in government administration and public service to implement policies and manage public programs.",
                    "Public Administration",
                    "Law & Public Service",
                    "Civil Services",
                    "Bachelor's degree followed by the relevant competitive examination and selection process",
                    "Political Science, History, Economics, Geography",
                    "Public Administration, Communication, Policy Analysis, Leadership",
                    "Public Service, Government, Society, Leadership",
                    "Leadership, Analytical Thinking, Communication, Decision Making",
                    "Graduate → Civil Services Candidate → Civil Services Officer → Senior Administrator"
            );

            addCareer(
                    careerRepository,
                    16L,
                    "Medical Laboratory Technologist",
                    "Performs laboratory tests that support diagnosis, treatment and medical research.",
                    "Medical Laboratory Science",
                    "Healthcare",
                    "Medical Laboratory Technology",
                    "Bachelor's degree or diploma in Medical Laboratory Technology or related field",
                    "Biology, Chemistry, Physics",
                    "Laboratory Techniques, Biology, Chemistry, Data Recording",
                    "Healthcare, Biology, Chemistry, Science",
                    "Attention to Detail, Analytical Thinking, Patience, Responsibility",
                    "Laboratory Technician → Medical Laboratory Technologist → Senior Technologist → Laboratory Manager"
            );

            addCareer(
                    careerRepository,
                    17L,
                    "Content Writer",
                    "Creates written content for websites, businesses, educational platforms and digital media.",
                    "Content & Communication",
                    "Media & Communication",
                    "Content Writing",
                    "Bachelor's degree is useful but relevant writing and communication skills are highly important",
                    "English, Literature, Media Studies",
                    "Writing, Research, Editing, Communication",
                    "Writing, Communication, Research, Creativity",
                    "Creativity, Communication, Curiosity, Attention to Detail",
                    "Junior Writer → Content Writer → Senior Content Writer → Content Strategist"
            );

            addCareer(
                    careerRepository,
                    18L,
                    "Chemical Engineer",
                    "Applies chemistry, mathematics and engineering principles to industrial processes and products.",
                    "Chemical Engineering",
                    "Engineering & Technology",
                    "Chemical Engineering",
                    "Bachelor's degree in Chemical Engineering",
                    "Chemistry, Mathematics, Physics",
                    "Process Engineering, Chemistry, Mathematics, Safety Analysis",
                    "Science, Chemistry, Engineering, Problem Solving",
                    "Analytical Thinking, Problem Solving, Attention to Detail, Persistence",
                    "Graduate Engineer → Chemical Engineer → Process Engineer → Senior Chemical Engineer"
            );

            addCareer(
                    careerRepository,
                    19L,
                    "Operations Manager",
                    "Coordinates business operations, processes, resources and teams to improve organizational efficiency.",
                    "Operations",
                    "Business & Management",
                    "Operations Management",
                    "Bachelor's degree in Business Administration, Management, Engineering or related field",
                    "Business Studies, Mathematics, Economics",
                    "Operations Management, Planning, Communication, Data Analysis",
                    "Business, Management, Problem Solving, Organization",
                    "Leadership, Organization, Problem Solving, Decision Making",
                    "Operations Executive → Operations Manager → Senior Operations Manager → Operations Director"
            );

            addCareer(
                    careerRepository,
                    20L,
                    "Chemist",
                    "Studies substances, chemical reactions and materials through laboratory research and analysis.",
                    "Chemistry",
                    "Science & Research",
                    "Chemical Research",
                    "Bachelor's degree in Chemistry or related field",
                    "Chemistry, Mathematics, Physics",
                    "Chemistry, Laboratory Techniques, Data Analysis, Research",
                    "Chemistry, Science, Research, Problem Solving",
                    "Analytical Thinking, Attention to Detail, Curiosity, Patience",
                    "Chemistry Graduate → Laboratory Analyst → Chemist → Research Scientist"
            );

            addCareer(
                    careerRepository,
                    21L,
                    "Research Scientist",
                    "Conducts systematic research to develop knowledge, test ideas and investigate scientific questions.",
                    "Scientific Research",
                    "Science & Research",
                    "Scientific Research",
                    "Bachelor's degree in a relevant science field; many research positions require postgraduate study",
                    "Science subjects, Mathematics",
                    "Research Methods, Data Analysis, Scientific Writing, Experimentation",
                    "Science, Research, Discovery, Problem Solving",
                    "Curiosity, Analytical Thinking, Patience, Persistence",
                    "Research Assistant → Scientist → Senior Scientist → Research Lead"
            );

            addCareer(
                    careerRepository,
                    22L,
                    "Video Editor",
                    "Edits video and audio content for entertainment, education, marketing and digital platforms.",
                    "Digital Media",
                    "Design & Creative",
                    "Video Editing",
                    "Degree, diploma or relevant training in Media, Film, Communication or Design",
                    "Media Studies, Art, Computer Applications",
                    "Video Editing, Storytelling, Audio Editing, Visual Design",
                    "Film, Media, Storytelling, Creativity",
                    "Creativity, Attention to Detail, Storytelling, Patience",
                    "Junior Video Editor → Video Editor → Senior Video Editor → Post Production Lead"
            );

            addCareer(
                    careerRepository,
                    23L,
                    "Pharmacist",
                    "Works with medicines, medication safety and patient guidance.",
                    "Pharmacy",
                    "Healthcare",
                    "Pharmacy Practice",
                    "Bachelor's degree in Pharmacy or relevant pharmacy qualification",
                    "Chemistry, Biology, Mathematics",
                    "Pharmacology, Chemistry, Communication, Medication Safety",
                    "Healthcare, Chemistry, Biology, Medicine",
                    "Attention to Detail, Analytical Thinking, Responsibility, Communication",
                    "Pharmacy Student → Pharmacist → Senior Pharmacist → Pharmacy Manager"
            );

            addCareer(
                    careerRepository,
                    24L,
                    "Public Relations Specialist",
                    "Manages communication between organizations and the public, media and stakeholders.",
                    "Public Relations",
                    "Media & Communication",
                    "Public Relations",
                    "Bachelor's degree in Public Relations, Mass Communication, Journalism, Marketing or related field",
                    "English, Media Studies, Business Studies",
                    "Communication, Media Relations, Writing, Event Management",
                    "Communication, Media, Business, Writing",
                    "Communication, Creativity, Organization, Relationship Building",
                    "PR Executive → PR Specialist → Senior PR Specialist → PR Manager"
            );

            addCareer(
                    careerRepository,
                    25L,
                    "Biologist",
                    "Studies living organisms and biological processes through observation, experimentation and research.",
                    "Biological Sciences",
                    "Science & Research",
                    "Biological Research",
                    "Bachelor's degree in Biology or related life science; advanced research roles may require postgraduate study",
                    "Biology, Chemistry",
                    "Biology, Research, Data Analysis, Laboratory Techniques",
                    "Biology, Nature, Research, Science",
                    "Curiosity, Analytical Thinking, Observation, Patience",
                    "Biology Graduate → Research Assistant → Biologist → Senior Researcher"
            );

            addCareer(
                    careerRepository,
                    26L,
                    "Cloud Engineer",
                    "Designs, deploys and maintains cloud-based computing infrastructure and services.",
                    "Cloud Computing",
                    "Engineering & Technology",
                    "Cloud Infrastructure",
                    "Bachelor's degree in Computer Science, Information Technology or related field",
                    "Computer Science, Mathematics",
                    "Cloud Computing, Networking, Linux, Programming, Security",
                    "Technology, Cloud Computing, Infrastructure, Problem Solving",
                    "Problem Solving, Analytical Thinking, Attention to Detail, Persistence",
                    "System Administrator → Cloud Engineer → Senior Cloud Engineer → Cloud Architect"
            );

            addCareer(
                    careerRepository,
                    27L,
                    "Machine Learning Engineer",
                    "Builds and deploys machine learning systems that allow software to learn from data.",
                    "Artificial Intelligence",
                    "Engineering & Technology",
                    "Machine Learning Engineering",
                    "Bachelor's degree in Computer Science, Artificial Intelligence, Data Science, Mathematics or related field",
                    "Mathematics, Computer Science, Statistics",
                    "Python, Machine Learning, Algorithms, Data Structures, SQL",
                    "Technology, Artificial Intelligence, Mathematics, Problem Solving",
                    "Logical Thinking, Analytical Thinking, Persistence, Curiosity",
                    "Software Developer → Machine Learning Engineer → Senior ML Engineer → ML Lead"
            );

            addCareer(
                    careerRepository,
                    28L,
                    "Physicist",
                    "Studies matter, energy, forces and natural phenomena using mathematical and experimental methods.",
                    "Physics",
                    "Science & Research",
                    "Physics Research",
                    "Bachelor's degree in Physics; advanced research roles commonly require postgraduate study",
                    "Physics, Mathematics",
                    "Physics, Mathematics, Research, Data Analysis",
                    "Physics, Mathematics, Science, Research",
                    "Analytical Thinking, Mathematical Thinking, Curiosity, Problem Solving",
                    "Physics Graduate → Research Assistant → Physicist → Senior Researcher"
            );

            addCareer(
                    careerRepository,
                    29L,
                    "Marketing Manager",
                    "Plans and manages marketing strategies that help organizations reach customers and grow.",
                    "Marketing",
                    "Business & Management",
                    "Marketing Management",
                    "Bachelor's degree in Marketing, Business Administration, Management or related field",
                    "Business Studies, Economics, Mathematics",
                    "Marketing Strategy, Communication, Market Research, Digital Marketing",
                    "Marketing, Business, Creativity, Communication",
                    "Creativity, Communication, Leadership, Analytical Thinking",
                    "Marketing Executive → Marketing Manager → Senior Marketing Manager → Marketing Director"
            );

            addCareer(
                    careerRepository,
                    30L,
                    "Nurse",
                    "Provides patient care, supports treatment and works with healthcare teams.",
                    "Nursing",
                    "Healthcare",
                    "Nursing Care",
                    "Nursing degree or diploma with required professional registration",
                    "Biology, Chemistry",
                    "Patient Care, Communication, Clinical Skills, Observation",
                    "Healthcare, Biology, Helping People, Service",
                    "Empathy, Patience, Communication, Responsibility",
                    "Nursing Student → Registered Nurse → Senior Nurse → Nursing Manager"
            );

            addCareer(
                    careerRepository,
                    31L,
                    "Content Creator",
                    "Creates educational, entertainment or informational content for digital platforms.",
                    "Digital Media",
                    "Design & Creative",
                    "Content Creation",
                    "No single mandatory degree; communication, media, marketing or design education can be useful",
                    "English, Media Studies, Computer Applications",
                    "Content Writing, Video Creation, Social Media, Communication",
                    "Media, Communication, Creativity, Technology",
                    "Creativity, Communication, Storytelling, Consistency",
                    "Content Creator → Senior Creator → Content Strategist → Creative Director"
            );

            addCareer(
                    careerRepository,
                    32L,
                    "Web Developer",
                    "Creates and maintains websites and web applications for users and organizations.",
                    "Computer & IT",
                    "Engineering & Technology",
                    "Web Development",
                    "Bachelor's degree, diploma or relevant training in Computer Science or Web Development",
                    "Computer Science, Mathematics",
                    "HTML, CSS, JavaScript, Web Development, Git",
                    "Technology, Design, Problem Solving, Creativity",
                    "Creativity, Problem Solving, Attention to Detail, Logical Thinking",
                    "Junior Web Developer → Web Developer → Senior Web Developer → Web Architect"
            );

            addCareer(
                    careerRepository,
                    33L,
                    "Journalist",
                    "Researches, investigates and communicates news and information to the public.",
                    "Journalism",
                    "Media & Communication",
                    "Journalism",
                    "Bachelor's degree or relevant education in Journalism, Mass Communication or related field",
                    "English, History, Political Science, Media Studies",
                    "Writing, Research, Interviewing, Communication",
                    "Writing, Current Affairs, Society, Media",
                    "Curiosity, Communication, Critical Thinking, Observation",
                    "Journalism Student → Reporter → Journalist → Senior Journalist → Editor"
            );

            addCareer(
                    careerRepository,
                    34L,
                    "Mobile App Developer",
                    "Designs and develops applications for smartphones and other mobile devices.",
                    "Computer & IT",
                    "Engineering & Technology",
                    "Mobile Application Development",
                    "Bachelor's degree or diploma in Computer Science, Software Engineering or related field",
                    "Computer Science, Mathematics",
                    "Programming, Mobile Development, UI Design, APIs, Git",
                    "Technology, Mobile Applications, Creativity, Problem Solving",
                    "Problem Solving, Creativity, Logical Thinking, Persistence",
                    "Junior App Developer → Mobile Developer → Senior Mobile Developer → Mobile Architect"
            );

            addCareer(
                    careerRepository,
                    35L,
                    "Business Analyst",
                    "Studies business processes and requirements to help organizations improve performance and systems.",
                    "Business Analysis",
                    "Business & Management",
                    "Business Analysis",
                    "Bachelor's degree in Business, Management, Economics, Computer Science or related field",
                    "Mathematics, Business Studies, Economics, Computer Science",
                    "Data Analysis, Communication, Problem Solving, Business Analysis",
                    "Business, Technology, Problem Solving, Data",
                    "Analytical Thinking, Communication, Logical Thinking, Problem Solving",
                    "Business Analyst → Senior Business Analyst → Product Manager → Business Consultant"
            );

            addCareer(
                    careerRepository,
                    36L,
                    "Product Designer",
                    "Designs digital products and experiences by combining user needs, research and visual design.",
                    "Digital Product Design",
                    "Design & Creative",
                    "Product Design",
                    "Bachelor's degree, diploma or relevant training in Design, UI/UX or related field",
                    "Design, Computer Science, Psychology",
                    "UI Design, UX Research, Prototyping, Design Systems",
                    "Design, Technology, Creativity, User Experience",
                    "Creativity, Empathy, Problem Solving, Attention to Detail",
                    "UI/UX Designer → Product Designer → Senior Product Designer → Design Lead"
            );

            addCareer(
                    careerRepository,
                    37L,
                    "Electronics Engineer",
                    "Designs and develops electronic circuits, devices and systems.",
                    "Electronics",
                    "Engineering & Technology",
                    "Electronics Engineering",
                    "Bachelor's degree in Electronics Engineering, Electrical Engineering or related field",
                    "Mathematics, Physics, Electronics",
                    "Circuit Design, Electronics, Programming, Microcontrollers",
                    "Electronics, Technology, Innovation, Engineering",
                    "Logical Thinking, Problem Solving, Creativity, Attention to Detail",
                    "Electronics Engineer → Senior Electronics Engineer → Systems Engineer → Engineering Lead"
            );

            addCareer(
                    careerRepository,
                    38L,
                    "Policy Analyst",
                    "Researches public issues and evaluates policies to support informed decision making.",
                    "Public Policy",
                    "Law & Public Service",
                    "Policy Analysis",
                    "Bachelor's degree in Economics, Political Science, Public Policy, Law or related field",
                    "Political Science, Economics, Mathematics, History",
                    "Research, Data Analysis, Policy Analysis, Writing",
                    "Government, Society, Research, Economics",
                    "Analytical Thinking, Communication, Research Skills, Critical Thinking",
                    "Research Assistant → Policy Analyst → Senior Policy Analyst → Policy Advisor"
            );

            addCareer(
                    careerRepository,
                    39L,
                    "Physiotherapist",
                    "Helps patients improve movement, physical function and recovery from injuries or conditions.",
                    "Physical Therapy",
                    "Healthcare",
                    "Physiotherapy",
                    "Bachelor's degree in Physiotherapy or equivalent professional qualification",
                    "Biology, Physics",
                    "Physical Therapy, Patient Assessment, Exercise Therapy, Communication",
                    "Healthcare, Biology, Sports, Helping People",
                    "Empathy, Patience, Observation, Problem Solving",
                    "Physiotherapy Student → Physiotherapist → Senior Physiotherapist → Clinical Specialist"
            );

            addCareer(
                    careerRepository,
                    40L,
                    "Animator",
                    "Creates moving visual content for films, games, advertisements and digital media.",
                    "Animation & Multimedia",
                    "Design & Creative",
                    "Animation",
                    "Degree or diploma in Animation, Fine Arts, Multimedia or related field",
                    "Art, Design, Computer Applications",
                    "2D Animation, 3D Animation, Storyboarding, Visual Design",
                    "Animation, Art, Storytelling, Technology",
                    "Creativity, Patience, Visual Thinking, Storytelling",
                    "Junior Animator → Animator → Senior Animator → Animation Director"
            );

            addCareer(
                    careerRepository,
                    41L,
                    "Electrical Engineer",
                    "Designs and develops electrical systems, equipment and technologies.",
                    "Electrical Engineering",
                    "Engineering & Technology",
                    "Electrical Engineering",
                    "Bachelor's degree in Electrical Engineering or related field",
                    "Mathematics, Physics",
                    "Circuit Design, Electrical Systems, Mathematics, Problem Solving",
                    "Technology, Engineering, Electronics, Problem Solving",
                    "Analytical Thinking, Logical Thinking, Attention to Detail, Persistence",
                    "Graduate Engineer → Electrical Engineer → Senior Electrical Engineer → Engineering Manager"
            );

            addCareer(
                    careerRepository,
                    42L,
                    "Motion Graphics Designer",
                    "Creates animated visual content for digital media, advertising, entertainment and communication.",
                    "Digital Media",
                    "Design & Creative",
                    "Motion Graphics Design",
                    "Degree or diploma in Graphic Design, Animation, Multimedia or related field",
                    "Art, Design, Computer Applications",
                    "Motion Design, Graphic Design, Animation, Video Editing",
                    "Design, Animation, Media, Creativity",
                    "Creativity, Visual Thinking, Attention to Detail, Storytelling",
                    "Graphic Designer → Motion Designer → Senior Motion Designer → Creative Director"
            );

            addCareer(
                    careerRepository,
                    43L,
                    "Doctor",
                    "Diagnoses and treats patients while working to maintain and improve their health.",
                    "Medicine",
                    "Healthcare",
                    "Medical Practice",
                    "Medical degree such as MBBS followed by required professional training and registration",
                    "Biology, Chemistry, Physics",
                    "Clinical Knowledge, Communication, Diagnosis, Patient Care",
                    "Healthcare, Biology, Science, Helping People",
                    "Empathy, Analytical Thinking, Communication, Responsibility",
                    "Medical Student → Doctor → Specialist → Senior Physician"
            );

            addCareer(
                    careerRepository,
                    44L,
                    "Human Resources Manager",
                    "Manages recruitment, employee development, workplace policies and organizational people practices.",
                    "Human Resources",
                    "Business & Management",
                    "Human Resources Management",
                    "Bachelor's degree in Human Resources, Business Administration, Psychology or related field",
                    "Business Studies, Psychology, Economics",
                    "Communication, Recruitment, Leadership, Conflict Resolution",
                    "People, Business, Communication, Leadership",
                    "Communication, Empathy, Leadership, Organization",
                    "HR Executive → HR Manager → Senior HR Manager → HR Director"
            );

            addCareer(
                    careerRepository,
                    45L,
                    "AI Engineer",
                    "Develops software systems that use artificial intelligence and machine learning techniques.",
                    "Artificial Intelligence",
                    "Engineering & Technology",
                    "Artificial Intelligence Engineering",
                    "Bachelor's degree in Computer Science, AI, Data Science or related field",
                    "Mathematics, Computer Science, Physics",
                    "Python, Machine Learning, Programming, Algorithms, AI Fundamentals",
                    "Artificial Intelligence, Technology, Problem Solving, Innovation",
                    "Logical Thinking, Creativity, Curiosity, Analytical Thinking",
                    "Software Developer → AI Engineer → Senior AI Engineer → AI Architect"
            );

            addCareer(
                    careerRepository,
                    46L,
                    "Financial Analyst",
                    "Studies financial information and market data to help organizations make informed financial decisions.",
                    "Accounting & Finance",
                    "Business & Finance",
                    "Financial Analysis",
                    "Bachelor's degree in Finance, Accounting, Economics, Commerce or related field",
                    "Mathematics, Economics, Accounting",
                    "Financial Analysis, Excel, Accounting, Data Analysis",
                    "Finance, Mathematics, Business, Economics",
                    "Analytical Thinking, Attention to Detail, Numerical Thinking, Communication",
                    "Financial Analyst → Senior Financial Analyst → Finance Manager → Finance Director"
            );

            addCareer(
                    careerRepository,
                    47L,
                    "Lawyer",
                    "Provides legal advice and represents individuals or organizations in legal matters.",
                    "Legal Services",
                    "Law & Public Service",
                    "Legal Practice",
                    "Law degree and required professional qualification or registration",
                    "English, Political Science, History, Economics",
                    "Legal Research, Communication, Writing, Negotiation",
                    "Law, Justice, Communication, Society",
                    "Analytical Thinking, Communication, Attention to Detail, Reasoning",
                    "Law Student → Junior Lawyer → Lawyer → Senior Lawyer"
            );

            addCareer(
                    careerRepository,
                    48L,
                    "Biomedical Engineer",
                    "Combines engineering and biological sciences to develop technologies used in healthcare.",
                    "Biomedical Engineering",
                    "Engineering & Technology",
                    "Biomedical Engineering",
                    "Bachelor's degree in Biomedical Engineering or related engineering field",
                    "Biology, Physics, Mathematics, Chemistry",
                    "Engineering Design, Biology, Electronics, Data Analysis",
                    "Healthcare, Technology, Biology, Engineering",
                    "Analytical Thinking, Creativity, Problem Solving, Curiosity",
                    "Biomedical Engineer → Senior Biomedical Engineer → Biomedical Researcher → Engineering Lead"
            );

            addCareer(
                    careerRepository,
                    49L,
                    "Environmental Scientist",
                    "Studies environmental systems and develops approaches for understanding and managing environmental challenges.",
                    "Environmental Science",
                    "Science & Research",
                    "Environmental Science",
                    "Bachelor's degree in Environmental Science, Biology, Chemistry or related field",
                    "Biology, Chemistry, Geography, Environmental Science",
                    "Environmental Analysis, Research, Data Analysis, Field Studies",
                    "Environment, Science, Nature, Sustainability",
                    "Analytical Thinking, Curiosity, Observation, Problem Solving",
                    "Environmental Graduate → Environmental Scientist → Senior Scientist → Environmental Consultant"
            );

            addCareer(
                    careerRepository,
                    50L,
                    "Investment Analyst",
                    "Researches investments, markets and financial information to support investment decisions.",
                    "Investment & Finance",
                    "Business & Finance",
                    "Investment Analysis",
                    "Bachelor's degree in Finance, Economics, Commerce or related field",
                    "Mathematics, Economics, Business Studies",
                    "Financial Analysis, Research, Excel, Market Analysis",
                    "Finance, Economics, Business, Markets",
                    "Analytical Thinking, Numerical Thinking, Research Skills, Attention to Detail",
                    "Financial Analyst → Investment Analyst → Senior Investment Analyst → Portfolio Manager"
            );

            System.out.println(
                    "Career and skill master data initialization completed."
            );
        };
    }

    private void addSkill(
            SkillRepository repository,
            String name,
            String description,
            String category) {

        if (repository.findByNameIgnoreCase(name).isEmpty()) {

            repository.save(
                    new Skill(
                            name,
                            description,
                            category
                    )
            );
        }
    }

    private void addCareer(
            CareerRepository repository,
            Long id,
            String title,
            String description,
            String domain,
            String careerFamily,
            String role,
            String requiredEducation,
            String subjects,
            String skills,
            String interests,
            String strengths,
            String careerPaths) {

        boolean careerExists = repository
                .findByTitleContainingIgnoreCase(title)
                .stream()
                .anyMatch(existingCareer ->
                        existingCareer.getTitle() != null
                                && existingCareer.getTitle().equalsIgnoreCase(title));

        if (careerExists) {
            return;
        }

        Career career = new Career(
                title,
                description,
                domain,
                careerFamily,
                role,
                requiredEducation,
                subjects,
                skills,
                interests,
                strengths,
                careerPaths
        );

        repository.save(career);
    }
}