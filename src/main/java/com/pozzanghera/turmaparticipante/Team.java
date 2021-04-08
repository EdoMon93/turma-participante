package com.pozzanghera.turmaparticipante;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Turma {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String description;

  private String type;

  @OneToMany
   @JoinColumn(name="library_id")
   private Library library;
    

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

  public Participants getParticipants() {
   return type;
 }

 public void setType(String type) {
   this.type = type;
 }

}