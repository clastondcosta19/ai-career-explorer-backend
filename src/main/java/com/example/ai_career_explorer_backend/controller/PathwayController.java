package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.entity.Pathway;
import com.example.ai_career_explorer_backend.service.PathwayService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pathways")
@CrossOrigin(origins = "http://localhost:4200")
public class PathwayController {

    private final PathwayService pathwayService;

    public PathwayController(PathwayService pathwayService) {
        this.pathwayService = pathwayService;
    }

    @GetMapping
    public ResponseEntity<List<Pathway>> getAllPathways() {

        return ResponseEntity.ok(
                pathwayService.getAllPathways()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pathway> getPathwayById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pathwayService.getPathwayById(id)
        );
    }


    @GetMapping("/search")
    public ResponseEntity<List<Pathway>> searchPathways(
            @RequestParam String name) {

        return ResponseEntity.ok(
                pathwayService.searchPathways(name)
        );
    }


    @GetMapping("/starting-level/{startingLevel}")
    public ResponseEntity<List<Pathway>> getPathwaysByStartingLevel(
            @PathVariable String startingLevel) {

        return ResponseEntity.ok(
                pathwayService.getPathwaysByStartingLevel(
                        startingLevel
                )
        );
    }


    @GetMapping("/type/{pathwayType}")
    public ResponseEntity<List<Pathway>> getPathwaysByType(
            @PathVariable String pathwayType) {

        return ResponseEntity.ok(
                pathwayService.getPathwaysByType(
                        pathwayType
                )
        );
    }


    @GetMapping("/career/{careerId}")
    public ResponseEntity<List<Pathway>> getPathwaysByCareerId(
            @PathVariable Long careerId) {

        return ResponseEntity.ok(
                pathwayService.getPathwaysByCareerId(
                        careerId
                )
        );
    }


    @PostMapping
    public ResponseEntity<Pathway> createPathway(
            @RequestBody Pathway pathway) {

        return ResponseEntity.ok(
                pathwayService.savePathway(pathway)
        );
    }


    @PostMapping("/{pathwayId}/career/{careerId}")
    public ResponseEntity<Pathway> assignCareer(
            @PathVariable Long pathwayId,
            @PathVariable Long careerId) {

        return ResponseEntity.ok(
                pathwayService.assignCareer(
                        pathwayId,
                        careerId
                )
        );
    }


    @PostMapping(
            "/{pathwayId}/education-programs/{educationProgramId}"
    )
    public ResponseEntity<Pathway> addEducationProgram(
            @PathVariable Long pathwayId,
            @PathVariable Long educationProgramId) {

        return ResponseEntity.ok(
                pathwayService.addEducationProgram(
                        pathwayId,
                        educationProgramId
                )
        );
    }


    @DeleteMapping(
            "/{pathwayId}/education-programs/{educationProgramId}"
    )
    public ResponseEntity<Pathway> removeEducationProgram(
            @PathVariable Long pathwayId,
            @PathVariable Long educationProgramId) {

        return ResponseEntity.ok(
                pathwayService.removeEducationProgram(
                        pathwayId,
                        educationProgramId
                )
        );
    }
}