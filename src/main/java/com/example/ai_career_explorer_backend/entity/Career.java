package com.example.ai_career_explorer_backend.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "careers")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 100)
    private String domain;

    @Column(name = "career_family", length = 150)
    private String careerFamily;

    @Column(length = 150)
    private String role;

    @Column(name = "required_education", length = 1500)
    private String requiredEducation;

    @Column(length = 1000)
    private String subjects;

    @Column(length = 1500)
    private String skills;

    @Column(length = 1000)
    private String interests;

    @Column(length = 1000)
    private String strengths;

    @Column(name = "career_paths", length = 3000)
    private String careerPaths;


    @ManyToMany
    @JoinTable(
        name = "career_education_programs",
        joinColumns = @JoinColumn(name = "career_id"),
        inverseJoinColumns = @JoinColumn(name = "education_program_id")
    )
    private Set<EducationProgram> educationPrograms = new HashSet<>();

   
    @ManyToMany
    @JoinTable(
        name = "career_skills",
        joinColumns = @JoinColumn(name = "career_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> requiredSkills = new HashSet<>();

    public Career() {
    }

    public Career(
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

        this.title = title;
        this.description = description;
        this.domain = domain;
        this.careerFamily = careerFamily;
        this.role = role;
        this.requiredEducation = requiredEducation;
        this.subjects = subjects;
        this.skills = skills;
        this.interests = interests;
        this.strengths = strengths;
        this.careerPaths = careerPaths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCareerFamily() {
        return careerFamily;
    }

    public void setCareerFamily(String careerFamily) {
        this.careerFamily = careerFamily;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRequiredEducation() {
        return requiredEducation;
    }

    public void setRequiredEducation(String requiredEducation) {
        this.requiredEducation = requiredEducation;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getCareerPaths() {
        return careerPaths;
    }

    public void setCareerPaths(String careerPaths) {
        this.careerPaths = careerPaths;
    }

    public Set<EducationProgram> getEducationPrograms() {
        return educationPrograms;
    }

    public void setEducationPrograms(Set<EducationProgram> educationPrograms) {
        this.educationPrograms = educationPrograms;
    }

    public void addEducationProgram(EducationProgram educationProgram) {
        this.educationPrograms.add(educationProgram);
    }

    public void removeEducationProgram(EducationProgram educationProgram) {
        this.educationPrograms.remove(educationProgram);
    }

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void addRequiredSkill(Skill skill) {
        this.requiredSkills.add(skill);
    }

    public void removeRequiredSkill(Skill skill) {
        this.requiredSkills.remove(skill);
    }
}