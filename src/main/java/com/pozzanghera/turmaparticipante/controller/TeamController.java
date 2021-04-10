package com.pozzanghera.turmaparticipante.controller;

import com.pozzanghera.turmaparticipante.model.Team;
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
@RequestMapping(path = "/turma", produces = "application/json")
public class TeamController {
   @Autowired
   private TeamRepository teamRepository;
   @Autowired
   private ParticipantRepository participantRepository;

   // Post
   @PostMapping(path = "/")
   public ResponseEntity<Team> postTurma(@RequestBody Team team) {
      // System.out.println(team);
      team.getParticipants().forEach(participant -> {
         participantRepository.save(participant);
      });
      teamRepository.save(team);
      return new ResponseEntity<Team>(team, HttpStatus.CREATED);
   }

   // Get All
   @GetMapping(path = "/")
   public @ResponseBody Iterable<Team> getAllTurma() {
      return teamRepository.findAll();
   }

   // Get by id
   @GetMapping(path = "/{id}")
   public ResponseEntity<Team> getByIdTurma(@PathVariable Integer id) {
      if (teamRepository.findById(id).isPresent()) {
         return new ResponseEntity<Team>(teamRepository.findById(id).get(), HttpStatus.OK);
      } else
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   // putTurma
   @PutMapping(path = "/{id}")
   public ResponseEntity<Team> putTurma(@RequestBody Team newTeam, @PathVariable Integer id) {

      return teamRepository.findById(id).map(team -> {
         team.setDescription(newTeam.getDescription());
         team.setType(newTeam.getType());
         team.getParticipants().clear();
         team.getParticipants().addAll(newTeam.getParticipants());
         return new ResponseEntity<Team>(teamRepository.save(team), HttpStatus.OK);
      }).orElseGet(() -> {
         newTeam.setId(id);
         return new ResponseEntity<Team>(teamRepository.save(newTeam), HttpStatus.CREATED);
      });
   }

   // deleteTurma
   @DeleteMapping(path = "/{id}")
   public ResponseEntity<String> deleteTurma(@PathVariable Integer id) {
      if (teamRepository.findById(id).isPresent()) {
         teamRepository.deleteById(id);
         return new ResponseEntity<String>(HttpStatus.OK);
      } else
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   // Get by Name
   @GetMapping(path = "/search")
   public @ResponseBody Iterable<Team> getByNameTurma(@RequestParam String name) {
      return teamRepository.findByDescriptionContaining(name);
   }
}