package com.meals.apimeals.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.meals.apimeals.Models.Group;
import com.meals.apimeals.Models.Family;
import com.meals.apimeals.Repo.FamilyRepo;
import com.meals.apimeals.Repo.GroupRepo;
import com.meals.apimeals.Request.GroupRequest;

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
public class GroupController {

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private FamilyRepo familyRepo;

    @GetMapping("/api/groups")
    public ResponseEntity<List<Group>> getFamilies() {
        List<Group> groupList = groupRepo.findAll();
        return new ResponseEntity<List<Group>>(groupList, HttpStatus.OK);
    }
    @GetMapping("/api/group/{groupId}")
    public ResponseEntity<Group> getGroup(@PathVariable long groupId){
        Group group = groupRepo.findByGroupId(groupId);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }
    @PostMapping("/api/group")
    public ResponseEntity<Group> saveGroup(@RequestBody GroupRequest gRequest){
        Group group = new Group(gRequest);
        Family family = familyRepo.findByFamilyId(gRequest.getLeadFamilyId());
        family.setGroup(group);
        family.setAsLeadFamily();
        group.addFamily(family);
        group = groupRepo.save(group);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }

    @PutMapping("/api/group")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {        
        return new ResponseEntity<Group>(groupRepo.save(group),HttpStatus.OK);
    }

    @PutMapping("/api/groupId/{groupId}/familyId/{familyId}")
    public ResponseEntity<Group> addFamilyToGroup(@PathVariable long groupId,@PathVariable long familyId) {
        Group group = groupRepo.findByGroupId(groupId);
        Family family = familyRepo.findByFamilyId(familyId);
        group.addFamily(family);       
        return new ResponseEntity<Group>(groupRepo.save(group),HttpStatus.OK);
    }
    
    @DeleteMapping("/api/group/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable long groupId){
        Group deleteGroup = groupRepo.findByGroupId(groupId);
        groupRepo.delete(deleteGroup);
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }
    
}

