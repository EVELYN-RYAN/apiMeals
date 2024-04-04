package com.meals.apimeals.Request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealRequest {
    
    private Long groupId;

    private Long responsibleFamilyId;

    private Long recipeId;

    private LocalDate deliveryDate;

    private LocalTime deliveryTime;

    private String notes;

    public MealRequest(){
        
    }
    public MealRequest(Long groupId, Long familyId, Long recipeId, LocalDate deliveryDate, LocalTime deliveryTime,
             String notes) {
        this.groupId = groupId;
        this.responsibleFamilyId = familyId;
        this.recipeId = recipeId;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.notes = notes;
    }
}
