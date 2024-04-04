package com.meals.apimeals.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.meals.apimeals.Models.Family;
import com.meals.apimeals.Models.Person;
import com.meals.apimeals.Repo.FamilyRepo;
import com.meals.apimeals.Repo.PersonRepo;
import com.meals.apimeals.Request.FamilyRequest;

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
public class FamilyController {

    @Autowired
    private FamilyRepo familyRepo;

    @Autowired 
    private PersonRepo  personRepo;

    @GetMapping("/api/families")
    public ResponseEntity<List<Family>> getFamilies() {
        List<Family> familyList = familyRepo.findAll();
        return new ResponseEntity<List<Family>>(familyList, HttpStatus.OK);
    }
    @GetMapping("/api/family/{familyId}")
    public ResponseEntity<Family> getFamily(@PathVariable long familyId){
        Family family = familyRepo.findByFamilyId(familyId);
        return new ResponseEntity<Family>(family, HttpStatus.OK);
    }
    @PostMapping("/api/family")
    public ResponseEntity<Family> saveFamily(@RequestBody FamilyRequest fRequest){
        Family family = new Family(fRequest);
        List<Long> familyMemberIds = fRequest.getFamilyMembers();
        for (long personId : familyMemberIds) {
            Person member = personRepo.findByPersonId(personId);
            family.addFamilyMember(member);
        }
        
        family = familyRepo.save(family);
        return new ResponseEntity<Family>(family, HttpStatus.OK);
    }

    @PutMapping("/api/family")
    public ResponseEntity<Family> updateFamily(@RequestBody Family family) {        
        return new ResponseEntity<Family>(familyRepo.save(family),HttpStatus.OK);
    }
    
    @DeleteMapping("/api/family/{familyId}")
    public ResponseEntity<String> deleteFamily(@PathVariable long familyId){
        Family deleteFamily = familyRepo.findByFamilyId(familyId);
        familyRepo.delete(deleteFamily);
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }
    
}

