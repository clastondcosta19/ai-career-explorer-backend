package com.example.ai_career_explorer_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assessment_questions")
public class AssessmentQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String question;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(name = "assessment_type", nullable = false, length = 30)
    private String assessmentType;

    @Column(name = "career_family", length = 150)
    private String careerFamily;

    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "option_a", nullable = false, length = 500)
    private String optionA;

    @Column(name = "option_b", nullable = false, length = 500)
    private String optionB;

    @Column(name = "option_c", nullable = false, length = 500)
    private String optionC;

    @Column(name = "option_d", nullable = false, length = 500)
    private String optionD;

    public AssessmentQuestion() {
    }

    public AssessmentQuestion(
            String question,
            String category,
            String optionA,
            String optionB,
            String optionC,
            String optionD) {

        this.question = question;
        this.category = category;
        this.assessmentType = "CORE";
        this.careerFamily = null;
        this.orderNumber = 0;
        this.active = true;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getCareerFamily() {
        return careerFamily;
    }

    public void setCareerFamily(String careerFamily) {
        this.careerFamily = careerFamily;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
}