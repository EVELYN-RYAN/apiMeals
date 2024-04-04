package com.meals.apimeals.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.meals.apimeals.Models.Person;
import com.meals.apimeals.Repo.PersonRepo;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class PersonController {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/api/people")
    public ResponseEntity<List<Person>> getFamilies() {
        List<Person> personList = personRepo.findAll();
        return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
    }
    @GetMapping("/api/person/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable long personId){
        Person person = personRepo.findByPersonId(personId);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }
    @PostMapping("/api/person")
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        person = personRepo.save(person);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @PutMapping("/api/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {        
        return new ResponseEntity<Person>(personRepo.save(person),HttpStatus.OK);
    }
    
    @DeleteMapping("/api/person/{personId}")
    public ResponseEntity<String> deletePerson(@PathVariable long personId){
        Person deletePerson = personRepo.findByPersonId(personId);
        personRepo.delete(deletePerson);
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }
    
}

