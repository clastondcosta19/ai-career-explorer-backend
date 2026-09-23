package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.entity.StudentResponse;
import com.example.ai_career_explorer_backend.dto.AssessmentResponseDto;
import com.example.ai_career_explorer_backend.service.AssessmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessment")
@CrossOrigin(
	    origins = {
	        "http://localhost:4200",
	        "https://ai-career-explorers.netlify.app"
	    }
	)
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(
            AssessmentService assessmentService) {

        this.assessmentService = assessmentService;
    }

    @GetMapping("/questions")
    public ResponseEntity<List<?>> getCoreQuestions() {

        return ResponseEntity.ok(
                assessmentService.getCoreQuestions()
        );
    }

    @GetMapping("/family/{careerFamily}/questions")
    public ResponseEntity<List<?>> getFamilyQuestions(
            @PathVariable String careerFamily) {

        return ResponseEntity.ok(
                assessmentService.getFamilyQuestions(
                        careerFamily
                )
        );
    }

   
    @GetMapping("/{userId}/career-family")
    public ResponseEntity<String> determineCareerFamily(
            @PathVariable Long userId) {

        String careerFamily =
                assessmentService.determineCareerFamily(
                        userId
                );

        return ResponseEntity.ok(careerFamily);
    }

    @PostMapping(
            "/{userId}/questions/{questionId}/response"
    )
    public ResponseEntity<AssessmentResponseDto> saveResponse(

            @PathVariable Long userId,

            @PathVariable Long questionId,

            @RequestBody AssessmentAnswerRequest request) {

        StudentResponse response =
                assessmentService.saveResponse(
                        userId,
                        questionId,
                        request.getSelectedOption()
                );

        AssessmentResponseDto dto =
                new AssessmentResponseDto(
                        response.getId(),
                        response.getQuestion().getId(),
                        response.getSelectedOption()
                );

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{userId}/responses")
    public ResponseEntity<List<StudentResponse>>
            getStudentResponses(
                    @PathVariable Long userId) {

        return ResponseEntity.ok(
                assessmentService.getStudentResponses(
                        userId
                )
        );
    }

    public static class AssessmentAnswerRequest {

        private String selectedOption;

        public AssessmentAnswerRequest() {
        }

        public String getSelectedOption() {
            return selectedOption;
        }

        public void setSelectedOption(
                String selectedOption) {

            this.selectedOption = selectedOption;
        }
    }
}