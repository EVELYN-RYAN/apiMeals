
package com.meals.apimeals.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meals.apimeals.Request.FamilyRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long familyId;
    
    @Column
    private String familyName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name="family_id")
    private List<Person> familyMembers;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private long zip;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="group_id") 
    private Group group;

    @Column
    private Boolean isLeadFamily = false;

    @Column
    @JsonIgnore
    private boolean isActive = true;
    
    public Family(){

    }
    public Family(FamilyRequest fRequest){
        this.familyName = fRequest.getFamilyName();
        this.address = fRequest.getAddress();
        this.city = fRequest.getCity();
        this.state = fRequest.getState();
        this.zip = fRequest.getZip();
    }
    public Family(long familyId, String familyName, List<Person> familyMembers, String address, String city,
            String state, long zip, Group group, Boolean isLeadFamily) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.familyMembers = familyMembers;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.group = group;
        this.isLeadFamily = isLeadFamily;
    }

    public void addFamilyMember(Person person){
        if (this.familyMembers == null){
            this.familyMembers =  new ArrayList<Person>();
        }
       this.familyMembers.add(person);
    }
    public void setAsLeadFamily(){
        this.isLeadFamily = true;
    }
    public void delete(){
        this.isActive = false;
      }
}
