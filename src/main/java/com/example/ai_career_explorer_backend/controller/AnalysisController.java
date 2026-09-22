package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.service.CareerMatchingService;
import com.example.ai_career_explorer_backend.service.SkillGapService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin(origins = "http://localhost:4200")
public class AnalysisController {

    private final CareerMatchingService careerMatchingService;
    private final SkillGapService skillGapService;

    public AnalysisController(
            CareerMatchingService careerMatchingService,
            SkillGapService skillGapService) {

        this.careerMatchingService = careerMatchingService;
        this.skillGapService = skillGapService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<Map<String, Object>>> analyzeCareers(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                careerMatchingService.getCareerMatches(userId)
        );
    }

    @GetMapping("/{userId}/career/{careerId}/skills")
    public ResponseEntity<Map<String, Object>> analyzeSkillGap(
            @PathVariable Long userId,
            @PathVariable Long careerId) {

        return ResponseEntity.ok(
                skillGapService.analyzeSkillGap(
                        userId,
                        careerId
                )
        );
    }
}