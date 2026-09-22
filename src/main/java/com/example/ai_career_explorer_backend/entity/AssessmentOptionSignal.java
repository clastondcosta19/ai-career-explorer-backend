package com.example.ai_career_explorer_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assessment_option_signals")
public class AssessmentOptionSignal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private AssessmentQuestion question;

    @Column(name = "selected_option", nullable = false, length = 1)
    private String selectedOption;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(nullable = false)
    private Integer weight;

    public AssessmentOptionSignal() {
    }

    public AssessmentOptionSignal(
            AssessmentQuestion question,
            String selectedOption,
            Skill skill,
            Integer weight) {

        this.question = question;
        this.selectedOption = selectedOption;
        this.skill = skill;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public AssessmentQuestion getQuestion() {
        return question;
    }

    public void setQuestion(AssessmentQuestion question) {
        this.question = question;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}