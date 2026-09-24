package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.entity.CareerAnalysis;
import com.example.ai_career_explorer_backend.service.CareerAnalysisService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/career-analysis")
@CrossOrigin(
        origins = {
                "http://localhost:4200",
                "https://ai-career-explorers.netlify.app"
        }
)
public class CareerAnalysisController {

    private final CareerAnalysisService analysisService;

    public CareerAnalysisController(
            CareerAnalysisService analysisService) {

        this.analysisService = analysisService;
    }

    @PostMapping("/{userId}/career/{careerId}")
    public ResponseEntity<CareerAnalysis> analyzeCareer(
            @PathVariable Long userId,
            @PathVariable Long careerId) {

        return ResponseEntity.ok(
                analysisService.analyzeCareer(
                        userId,
                        careerId
                )
        );
    }

    @PostMapping("/{userId}/all")
    public ResponseEntity<List<CareerAnalysis>> analyzeAllCareers(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                analysisService.analyzeAllCareers(userId)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CareerAnalysis>> getStudentAnalyses(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                analysisService.getStudentAnalyses(userId)
        );
    }

    @GetMapping("/{userId}/career/{careerId}")
    public ResponseEntity<CareerAnalysis> getStudentCareerAnalysis(
            @PathVariable Long userId,
            @PathVariable Long careerId) {

        return ResponseEntity.ok(
                analysisService.getStudentCareerAnalysis(
                        userId,
                        careerId
                )
        );
    }
}