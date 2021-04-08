package com.pozzanghera.turmaparticipante.repository;

import com.pozzanghera.turmaparticipante.model.Participant;

import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {

}