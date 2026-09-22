package com.example.ai_career_explorer_backend.repository;

import com.example.ai_career_explorer_backend.entity.Pathway;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathwayRepository extends JpaRepository<Pathway, Long> {

    List<Pathway> findByStartingLevelIgnoreCase(String startingLevel);

    List<Pathway> findByPathwayTypeIgnoreCase(String pathwayType);

    List<Pathway> findByCareerId(Long careerId);

    List<Pathway> findByNameContainingIgnoreCase(String name);
}