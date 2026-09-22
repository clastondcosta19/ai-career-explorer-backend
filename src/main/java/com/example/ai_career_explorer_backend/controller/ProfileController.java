package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.dto.ProfileRequest;
import com.example.ai_career_explorer_backend.dto.ProfileResponse;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.service.ProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<StudentProfile> saveProfile(
            @PathVariable Long userId,
            @RequestBody ProfileRequest request) {

        StudentProfile profile =
                profileService.saveProfile(
                        userId,
                        request);

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(
            @PathVariable Long userId) {

        ProfileResponse profile =
                profileService.getProfile(userId);

        return ResponseEntity.ok(profile);
    }

    
    @PostMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<StudentProfile> addStudentSkill(
            @PathVariable Long userId,
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                profileService.addStudentSkill(
                        userId,
                        skillId)
        );
    }

    
    @DeleteMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<StudentProfile> removeStudentSkill(
            @PathVariable Long userId,
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                profileService.removeStudentSkill(
                        userId,
                        skillId)
        );
    }
}