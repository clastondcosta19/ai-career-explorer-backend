package com.example.ai_career_explorer_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_responses")
public class StudentResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private AssessmentQuestion question;

    @Column(nullable = false, length = 10)
    private String selectedOption;

    public StudentResponse() {
    }

    public StudentResponse(
            User user,
            AssessmentQuestion question,
            String selectedOption) {

        this.user = user;
        this.question = question;
        this.selectedOption = selectedOption;
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
}