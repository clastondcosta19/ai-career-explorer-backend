package com.example.ai_career_explorer_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "career_analyses",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"user_id", "career_id"}
        )
    }
)
public class CareerAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @Column(nullable = false)
    private Integer compatibilityScore;

    @Column(nullable = false)
    private Integer skillMatchPercentage;

    @Column(nullable = false)
    private Integer educationScore;

    @Column(length = 4000)
    private String educationExplanation;

    @Column(length = 2000)
    private String matchedSkills;

    @Column(length = 2000)
    private String missingSkills;

    @Column(length = 2000)
    private String matchedInterests;

    @Column(length = 2000)
    private String matchedStrengths;

    @Column(length = 4000)
    private String explanation;

    @Column(nullable = false)
    private LocalDateTime analyzedAt;

    public CareerAnalysis() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Integer getCompatibilityScore() {
        return compatibilityScore;
    }

    public void setCompatibilityScore(Integer compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }

    public Integer getSkillMatchPercentage() {
        return skillMatchPercentage;
    }

    public void setSkillMatchPercentage(Integer skillMatchPercentage) {
        this.skillMatchPercentage = skillMatchPercentage;
    }

    public Integer getEducationScore() {
        return educationScore;
    }

    public void setEducationScore(Integer educationScore) {
        this.educationScore = educationScore;
    }

    public String getEducationExplanation() {
        return educationExplanation;
    }

    public void setEducationExplanation(String educationExplanation) {
        this.educationExplanation = educationExplanation;
    }

    public String getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(String matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public String getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(String missingSkills) {
        this.missingSkills = missingSkills;
    }

    public String getMatchedInterests() {
        return matchedInterests;
    }

    public void setMatchedInterests(String matchedInterests) {
        this.matchedInterests = matchedInterests;
    }

    public String getMatchedStrengths() {
        return matchedStrengths;
    }

    public void setMatchedStrengths(String matchedStrengths) {
        this.matchedStrengths = matchedStrengths;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}