package com.pozzanghera.turmaparticipante.repository;

import com.pozzanghera.turmaparticipante.model.Participant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
   Iterable<Participant> findByNameContaining(@Param("name") String name);

}