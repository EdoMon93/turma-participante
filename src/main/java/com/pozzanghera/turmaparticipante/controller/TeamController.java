package com.pozzanghera.turmaparticipante.controller;

import com.pozzanghera.turmaparticipante.model.Team;
import com.pozzanghera.turmaparticipante.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/turma")
public class TeamController {
   @Autowired
   private TeamRepository teamRepository;

   // Post
   @PostMapping(path = "/new")
   public ResponseEntity<String> postTurma(@RequestBody Team team) {
      System.out.println(team);
      teamRepository.save(team);
      return new ResponseEntity<String>("Saved", HttpStatus.ACCEPTED);
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
         return new ResponseEntity<Team>(teamRepository.findById(id).get(), HttpStatus.OK) ;
      } else
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   // Get by Name
   @GetMapping(path = "/search")
   public @ResponseBody Iterable<Team> getByNameTurma(@RequestParam String description) {
      return teamRepository.findByDescriptionContaining(description);
   }
}