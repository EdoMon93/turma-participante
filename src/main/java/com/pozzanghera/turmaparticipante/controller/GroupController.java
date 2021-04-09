package com.pozzanghera.turmaparticipante.controller;

import com.pozzanghera.turmaparticipante.model.Team;
import com.pozzanghera.turmaparticipante.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/turma")
public class GroupController {
   @Autowired
   private TeamRepository teamRepository;

   @PostMapping(path = "/")
   public String postTeam(@RequestBody Team team) {
      System.out.println(team);
      teamRepository.save(team);
      return "Saved";
   }

   @GetMapping(path = "/")
   public @ResponseBody Iterable<Team> getAllTeam() {
      // This returns a JSON or XML with the users
      return teamRepository.findAll();
   }
}