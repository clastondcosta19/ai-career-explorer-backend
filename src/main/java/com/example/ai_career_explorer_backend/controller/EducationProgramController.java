package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.entity.EducationProgram;
import com.example.ai_career_explorer_backend.service.EducationProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education-programs")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationProgramController {

    private final EducationProgramService educationProgramService;

    public EducationProgramController(
            EducationProgramService educationProgramService) {
        this.educationProgramService = educationProgramService;
    }

    @GetMapping
    public ResponseEntity<List<EducationProgram>> getAllPrograms() {
        return ResponseEntity.ok(
                educationProgramService.getAllPrograms()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationProgram> getProgramById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                educationProgramService.getProgramById(id)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<EducationProgram>> searchPrograms(
            @RequestParam String name) {

        return ResponseEntity.ok(
                educationProgramService.searchPrograms(name)
        );
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<EducationProgram>> getProgramsByLevel(
            @PathVariable String level) {

        return ResponseEntity.ok(
                educationProgramService.getProgramsByLevel(level)
        );
    }

    @GetMapping("/field/{field}")
    public ResponseEntity<List<EducationProgram>> getProgramsByField(
            @PathVariable String field) {

        return ResponseEntity.ok(
                educationProgramService.getProgramsByField(field)
        );
    }

    @GetMapping("/entry-level/{entryLevel}")
    public ResponseEntity<List<EducationProgram>> getProgramsByEntryLevel(
            @PathVariable String entryLevel) {

        return ResponseEntity.ok(
                educationProgramService.getProgramsByEntryLevel(entryLevel)
        );
    }

    @PostMapping
    public ResponseEntity<EducationProgram> createProgram(
            @RequestBody EducationProgram program) {

        return ResponseEntity.ok(
                educationProgramService.saveProgram(program)
        );
    }
}