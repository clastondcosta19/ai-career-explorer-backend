package com.example.ai_career_explorer_backend.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pathways")
public class Pathway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "starting_level", length = 100)
    private String startingLevel;

    @Column(length = 1500)
    private String steps;

    @Column(length = 1000)
    private String outcomes;

    @Column(name = "pathway_type", length = 100)
    private String pathwayType;

    @ManyToOne
    @JoinColumn(name = "career_id")
    private Career career;

    @ManyToMany
    @JoinTable(
            name = "pathway_education_programs",
            joinColumns = @JoinColumn(name = "pathway_id"),
            inverseJoinColumns = @JoinColumn(name = "education_program_id")
    )
    private Set<EducationProgram> educationPrograms = new HashSet<>();

    public Pathway() {
    }

    public Pathway(
            String name,
            String description,
            String startingLevel,
            String steps,
            String outcomes,
            String pathwayType) {

        this.name = name;
        this.description = description;
        this.startingLevel = startingLevel;
        this.steps = steps;
        this.outcomes = outcomes;
        this.pathwayType = pathwayType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartingLevel() {
        return startingLevel;
    }

    public void setStartingLevel(String startingLevel) {
        this.startingLevel = startingLevel;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(String outcomes) {
        this.outcomes = outcomes;
    }

    public String getPathwayType() {
        return pathwayType;
    }

    public void setPathwayType(String pathwayType) {
        this.pathwayType = pathwayType;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Set<EducationProgram> getEducationPrograms() {
        return educationPrograms;
    }

    public void setEducationPrograms(
            Set<EducationProgram> educationPrograms) {

        this.educationPrograms = educationPrograms;
    }

    public void addEducationProgram(
            EducationProgram educationProgram) {

        this.educationPrograms.add(educationProgram);
    }

    public void removeEducationProgram(
            EducationProgram educationProgram) {

        this.educationPrograms.remove(educationProgram);
    }
}