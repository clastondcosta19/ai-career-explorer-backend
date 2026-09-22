package com.example.ai_career_explorer_backend.controller;

import com.example.ai_career_explorer_backend.entity.Skill;
import com.example.ai_career_explorer_backend.repository.SkillRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {

    private final SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        return ResponseEntity.ok(skillRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return skillRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Skill>> searchSkills(
            @RequestParam String name) {
        return ResponseEntity.ok(
                skillRepository.findByNameContainingIgnoreCase(name)
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Skill>> getSkillsByCategory(
            @PathVariable String category) {
        return ResponseEntity.ok(
                skillRepository.findByCategoryIgnoreCase(category)
        );
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(
            @RequestBody Skill skill) {
        return ResponseEntity.ok(
                skillRepository.save(skill)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(
            @PathVariable Long id,
            @RequestBody Skill skill) {

        return skillRepository.findById(id)
                .map(existingSkill -> {
                    existingSkill.setName(skill.getName());
                    existingSkill.setDescription(skill.getDescription());
                    existingSkill.setCategory(skill.getCategory());

                    return ResponseEntity.ok(
                            skillRepository.save(existingSkill)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(
            @PathVariable Long id) {

        if (!skillRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        skillRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}