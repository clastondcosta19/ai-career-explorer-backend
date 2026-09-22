package com.example.ai_career_explorer_backend.dto;

public class AssessmentResponseDto {

    private Long id;
    private Long questionId;
    private String selectedOption;

    public AssessmentResponseDto() {
    }

    public AssessmentResponseDto(
            Long id,
            Long questionId,
            String selectedOption) {

        this.id = id;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}