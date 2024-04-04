package com.meals.apimeals.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(
  scope = Person.class,
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "personId")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;

    @Column
    private String firstName;

    @Column
    private String lastName;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="family_id")
    private Family family;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private List<String> allergy;

    @Column
    @JsonIgnore
    private boolean isActive = true;

    public Person(){

    }
    public Person(long personId, Family family, String email, String password, List<String> allergy) {
        this.personId = personId;
        this.family = family;
        this.email = email;
        this.password = password;
        this.allergy = allergy;
    }
    public void delete(){
      this.isActive = false;
    }
}
