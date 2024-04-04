package com.meals.apimeals.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meals.apimeals.Request.GroupRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="\"Group\"")
public class Group {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    @Column
    private String groupName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name="group_id")
    private List<Family> families;


    @JsonIgnore
    private boolean isActive = true;

    public Group(){

    }
    public Group(GroupRequest gr){
        this.groupName = gr.getGroupName();
        this.families = new ArrayList<Family>();
    }
    public Group(long groupId, String groupName, List<Family> families) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.families = families;
    }
    public void addFamily(Family family){
        this.families.add(family);
    }
    public void delete(){
        this.isActive = false;
      }
}
