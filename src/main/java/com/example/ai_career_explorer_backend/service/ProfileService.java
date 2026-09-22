package com.example.ai_career_explorer_backend.service;

import com.example.ai_career_explorer_backend.dto.ProfileRequest;
import com.example.ai_career_explorer_backend.dto.ProfileResponse;
import com.example.ai_career_explorer_backend.dto.UserResponse;
import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.entity.StudentProfile;
import com.example.ai_career_explorer_backend.entity.User;
import com.example.ai_career_explorer_backend.repository.SkillRepository;
import com.example.ai_career_explorer_backend.repository.StudentProfileRepository;
import com.example.ai_career_explorer_backend.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileService {

    private final StudentProfileRepository profileRepository;

    private final UserRepository userRepository;

    private final SkillRepository skillRepository;

    public ProfileService(
            StudentProfileRepository profileRepository,
            UserRepository userRepository,
            SkillRepository skillRepository) {

        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    public StudentProfile saveProfile(
            Long userId,
            ProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile profile = profileRepository
                .findByUserId(userId)
                .orElse(new StudentProfile());

        profile.setUser(user);

        profile.setEducationLevel(
                request.getEducationLevel());

        profile.setCurrentClass(
                request.getCurrentClass());

        profile.setStream(
                request.getStream());

        profile.setSubjects(
                request.getSubjects());

        profile.setInterests(
                request.getInterests());

        profile.setSkills(
                request.getSkills());

        profile.setStrengths(
                request.getStrengths());

        profile.setCareerGoals(
                request.getCareerGoals());

        return profileRepository.save(profile);
    }

    public ProfileResponse getProfile(Long userId) {

        StudentProfile profile =
                profileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Profile not found"));

        User user = profile.getUser();

        UserResponse userResponse =
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCreatedAt()
                );

        ProfileResponse response =
                new ProfileResponse();

        response.setId(profile.getId());

        response.setUser(userResponse);

        response.setEducationLevel(
                profile.getEducationLevel());

        response.setCurrentClass(
                profile.getCurrentClass());

        response.setStream(
                profile.getStream());

        response.setSubjects(
                profile.getSubjects());

        response.setInterests(
                profile.getInterests());

        response.setSkills(
                profile.getSkills());

        response.setStrengths(
                profile.getStrengths());

        response.setCareerGoals(
                profile.getCareerGoals());

        response.setStudentSkills(
                profile.getStudentSkills());

        response.setCreatedAt(
                profile.getCreatedAt());

        response.setUpdatedAt(
                profile.getUpdatedAt());

        return response;
    }

    public StudentProfile addStudentSkill(
            Long userId,
            Long skillId) {

        StudentProfile profile =
                profileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Profile not found"));

        Skill skill =
                skillRepository.findById(skillId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Skill not found"));

        profile.addStudentSkill(skill);

        return profileRepository.save(profile);
    }

    public StudentProfile removeStudentSkill(
            Long userId,
            Long skillId) {

        StudentProfile profile =
                profileRepository.findByUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Profile not found"));

        Skill skill =
                skillRepository.findById(skillId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Skill not found"));

        profile.removeStudentSkill(skill);

        return profileRepository.save(profile);
    }
}