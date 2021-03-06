package com.pozzanghera.turmaparticipante.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;

  private String type;

  @OneToMany(targetEntity = Participant.class, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "team_id")
  private List<Participant> participants;

  @Override
  public String toString() {
    return "Team( id:"+id+", description:"+description+", type:"+type+", participants:"+participants.size()+" )";
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Participant> getParticipants() {
    return participants;
  }

  @JsonIgnoreProperties("team")
  public void setParticipants(List<Participant> participants) {
    this.participants = participants;
  }

}