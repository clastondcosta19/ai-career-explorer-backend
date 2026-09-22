package com.example.ai_career_explorer_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "education_programs")
public class EducationProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 100)
    private String level;

    @Column(length = 100)
    private String field;

    @Column(length = 1000)
    private String description;

    @Column(name = "entry_level", length = 100)
    private String entryLevel;

    @Column(length = 1000)
    private String eligibility;

    @Column(length = 1000)
    private String subjects;

    @Column(name = "typical_duration", length = 100)
    private String typicalDuration;

    public EducationProgram() {
    }

    public EducationProgram(
            String name,
            String level,
            String field,
            String description,
            String entryLevel,
            String eligibility,
            String subjects,
            String typicalDuration) {

        this.name = name;
        this.level = level;
        this.field = field;
        this.description = description;
        this.entryLevel = entryLevel;
        this.eligibility = eligibility;
        this.subjects = subjects;
        this.typicalDuration = typicalDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntryLevel() {
        return entryLevel;
    }

    public void setEntryLevel(String entryLevel) {
        this.entryLevel = entryLevel;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getTypicalDuration() {
        return typicalDuration;
    }

    public void setTypicalDuration(String typicalDuration) {
        this.typicalDuration = typicalDuration;
    }
}