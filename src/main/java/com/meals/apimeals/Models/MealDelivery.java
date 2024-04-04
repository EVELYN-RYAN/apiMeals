package com.meals.apimeals.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

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
public class MealDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryId;

    @ManyToOne
    @JsonBackReference(value="mealDelivery")
    @JoinColumn(name="meal_id")
    private Meal meal;

    @ManyToOne
    @JsonIdentityReference
    @JoinColumn(name="family_id")
    private Family family;

    @Column
    private String method = "Hand-off";
    
    @Column
    private Boolean paid = false;

    @Column
    private String notes = null;
    // + (Allergy Alert)

    public MealDelivery(){
        
    }    
}
