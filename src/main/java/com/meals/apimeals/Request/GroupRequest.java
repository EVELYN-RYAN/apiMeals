package com.meals.apimeals.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {
    
    private String groupName;

    private Long leadFamilyId;
}
