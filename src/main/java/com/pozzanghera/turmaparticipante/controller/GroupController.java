package com.pozzanghera.turmaparticipante.controller;

import java.util.List;

import com.pozzanghera.turmaparticipante.model.Participant;
import com.pozzanghera.turmaparticipante.model.Team;
import com.pozzanghera.turmaparticipante.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/turma")
public class GroupController {
   @Autowired
   private TeamRepository teamRepository;

   @PostMapping(path = "/new")
   public @ResponseBody String postTeam(@RequestParam String descricao, @RequestParam String tipo,
         @RequestParam(required = false) List<Participant> participantes) {
      Team t = new Team();
      t.setDescription(descricao);
      t.setType(tipo);
      if (participantes != null) {
         t.setParticipants(participantes);
      }
      teamRepository.save(t);
      return "Saved";
   }

   @GetMapping(path = "/")
   public @ResponseBody Iterable<Team> getAllTeam() {
      // This returns a JSON or XML with the users
      return teamRepository.findAll();
   }
}