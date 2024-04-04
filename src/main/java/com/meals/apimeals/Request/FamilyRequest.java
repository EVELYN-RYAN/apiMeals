package com.meals.apimeals.Request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyRequest {
    
    private String familyName;

    private List<Long> familyMembers;

    private String address;

    private String city;

    private String state;

    private long zip;
}
