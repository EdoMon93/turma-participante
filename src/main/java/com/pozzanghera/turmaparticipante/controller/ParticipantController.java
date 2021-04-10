package com.pozzanghera.turmaparticipante.controller;

import com.pozzanghera.turmaparticipante.model.Participant;
import com.pozzanghera.turmaparticipante.repository.ParticipantRepository;
import com.pozzanghera.turmaparticipante.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/participante", produces = "application/json")
public class ParticipantController {
   @Autowired
   private ParticipantRepository participantRepository;
   @Autowired
   private TeamRepository teamRepository;

   // Post
   @PostMapping(path = "/")
   public ResponseEntity<Participant> postParticipante(@RequestBody Participant participant) {
      // System.out.println(participant);
      teamRepository.save(participant.getTeam());
      participantRepository.save(participant);
      return new ResponseEntity<Participant>(participant, HttpStatus.CREATED);
   }

   // Get All
   @GetMapping(path = "/")
   public @ResponseBody Iterable<Participant> getAllParticipante() {
      return participantRepository.findAll();
   }

   // Get by id
   @GetMapping(path = "/{id}")
   public ResponseEntity<Participant> getByIdParticipante(@PathVariable Integer id) {
      if (participantRepository.findById(id).isPresent()) {
         return new ResponseEntity<Participant>(participantRepository.findById(id).get(), HttpStatus.OK);
      } else
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   // putParticipante
   @PutMapping(path = "/{id}")
   public ResponseEntity<Participant> putParticipante(@RequestBody Participant newParticipant, @PathVariable Integer id) {

      return participantRepository.findById(id).map(participant -> {
         participant.setName(newParticipant.getName());
         participant.setEmail(newParticipant.getEmail());
         participant.setObservations(newParticipant.getObservations());
         participant.setTeam(newParticipant.getTeam());
         return new ResponseEntity<Participant>(participantRepository.save(participant), HttpStatus.OK);
      }).orElseGet(() -> {
         newParticipant.setId(id);
         return new ResponseEntity<Participant>(participantRepository.save(newParticipant), HttpStatus.CREATED);
      });
   }

   // deleteParticipante
   @DeleteMapping(path = "/{id}")
   public ResponseEntity<String> deleteParticipante(@PathVariable Integer id) {
      if (participantRepository.findById(id).isPresent()) {
         participantRepository.deleteById(id);
         return new ResponseEntity<String>(HttpStatus.OK);
      } else
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   // Get by Name
   @GetMapping(path = "/search")
   public @ResponseBody Iterable<Participant> getByNameParticipante(@RequestParam String name) {
      return participantRepository.findByNameContaining(name);
   }
}