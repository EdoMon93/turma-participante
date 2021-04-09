package com.pozzanghera.turmaparticipante.repository;

import com.pozzanghera.turmaparticipante.model.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Integer> {
   Iterable<Team> findByDescriptionContaining(@Param("description") String description);
}