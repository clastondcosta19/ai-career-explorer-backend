package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.entity.StudentRoadmap;
import com.example.ai_career_explorer_backend.service.StudentRoadmapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roadmaps")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentRoadmapController {

    private final StudentRoadmapService roadmapService;

    public StudentRoadmapController(
            StudentRoadmapService roadmapService
    ) {
        this.roadmapService = roadmapService;
    }

    @PostMapping("/{userId}/career/{careerId}")
    public ResponseEntity<StudentRoadmap> generateRoadmap(
            @PathVariable Long userId,
            @PathVariable Long careerId
    ) {

        return ResponseEntity.ok(
                roadmapService.generateRoadmap(
                        userId,
                        careerId
                )
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<StudentRoadmap>> getStudentRoadmaps(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                roadmapService.getStudentRoadmaps(
                        userId
                )
        );
    }

    @GetMapping("/{userId}/career/{careerId}")
    public ResponseEntity<StudentRoadmap> getStudentCareerRoadmap(
            @PathVariable Long userId,
            @PathVariable Long careerId
    ) {

        return ResponseEntity.ok(
                roadmapService.getStudentCareerRoadmap(
                        userId,
                        careerId
                )
        );
    }

    @PatchMapping("/{userId}/career/{careerId}/step/{stepNumber}")
    public ResponseEntity<StudentRoadmap> updateStepStatus(
            @PathVariable Long userId,
            @PathVariable Long careerId,
            @PathVariable Integer stepNumber,
            @RequestParam String status
    ) {

        return ResponseEntity.ok(
                roadmapService.updateStepStatus(
                        userId,
                        careerId,
                        stepNumber,
                        status
                )
        );
    }
}