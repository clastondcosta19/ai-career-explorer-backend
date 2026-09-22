package com.example.ai_career_explorer_backend.service.ai;

public class AiGuidanceRequest {

    private Long userId;

    private Long careerId;

    private String question;

    public AiGuidanceRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCareerId() {
        return careerId;
    }

    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}