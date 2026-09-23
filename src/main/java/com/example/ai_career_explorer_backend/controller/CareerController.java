package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.entity.Career;
import com.example.ai_career_explorer_backend.service.CareerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careers")
@CrossOrigin(origins = "http://localhost:4200")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }


    @GetMapping
    public ResponseEntity<List<Career>> getAllCareers() {

        return ResponseEntity.ok(
                careerService.getAllCareers()
        );
    }


    @GetMapping("/search")
    public ResponseEntity<List<Career>> searchCareers(
            @RequestParam String title) {

        return ResponseEntity.ok(
                careerService.searchCareers(title)
        );
    }


    @GetMapping("/domain/{domain}")
    public ResponseEntity<List<Career>> getCareersByDomain(
            @PathVariable String domain) {

        return ResponseEntity.ok(
                careerService.getCareersByDomain(domain)
        );
    }

    @GetMapping("/family/{careerFamily}")
    public ResponseEntity<List<Career>> getCareersByFamily(
            @PathVariable String careerFamily) {

        return ResponseEntity.ok(
                careerService.getCareersByFamily(careerFamily)
        );
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<Career>> getCareersByRole(
            @PathVariable String role) {

        return ResponseEntity.ok(
                careerService.getCareersByRole(role)
        );
    }


    @GetMapping("/domain/{domain}/family/{careerFamily}")
    public ResponseEntity<List<Career>> getCareersByDomainAndFamily(
            @PathVariable String domain,
            @PathVariable String careerFamily) {

        return ResponseEntity.ok(
                careerService.getCareersByDomainAndFamily(
                        domain,
                        careerFamily
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                careerService.getCareerById(id)
        );
    }

    @PostMapping
    public ResponseEntity<Career> createCareer(
            @RequestBody Career career) {

        return ResponseEntity.ok(
                careerService.saveCareer(career)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Career> updateCareer(
            @PathVariable Long id,
            @RequestBody Career career) {

        Career existingCareer =
                careerService.getCareerById(id);

        existingCareer.setTitle(
                career.getTitle()
        );

        existingCareer.setDescription(
                career.getDescription()
        );

        existingCareer.setDomain(
                career.getDomain()
        );

        existingCareer.setCareerFamily(
                career.getCareerFamily()
        );

        existingCareer.setRole(
                career.getRole()
        );

        existingCareer.setRequiredEducation(
                career.getRequiredEducation()
        );

        existingCareer.setSubjects(
                career.getSubjects()
        );

        existingCareer.setSkills(
                career.getSkills()
        );

        existingCareer.setInterests(
                career.getInterests()
        );

        existingCareer.setStrengths(
                career.getStrengths()
        );

        existingCareer.setCareerPaths(
                career.getCareerPaths()
        );

        return ResponseEntity.ok(
                careerService.saveCareer(existingCareer)
        );
    }


    @PostMapping("/{careerId}/education-programs/{educationProgramId}")
    public ResponseEntity<Career> addEducationProgram(
            @PathVariable Long careerId,
            @PathVariable Long educationProgramId) {

        return ResponseEntity.ok(
                careerService.addEducationProgram(
                        careerId,
                        educationProgramId
                )
        );
    }

    @DeleteMapping("/{careerId}/education-programs/{educationProgramId}")
    public ResponseEntity<Career> removeEducationProgram(
            @PathVariable Long careerId,
            @PathVariable Long educationProgramId) {

        return ResponseEntity.ok(
                careerService.removeEducationProgram(
                        careerId,
                        educationProgramId
                )
        );
    }


    @PostMapping("/{careerId}/skills/{skillId}")
    public ResponseEntity<Career> addRequiredSkill(
            @PathVariable Long careerId,
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                careerService.addRequiredSkill(
                        careerId,
                        skillId
                )
        );
    }


    @DeleteMapping("/{careerId}/skills/{skillId}")
    public ResponseEntity<Career> removeRequiredSkill(
            @PathVariable Long careerId,
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                careerService.removeRequiredSkill(
                        careerId,
                        skillId
                )
        );
    }
}