package com.meals.apimeals.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.meals.apimeals.Request.MealRequest;

@Getter
@Setter
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealId;
    
    @ManyToOne
    @JsonManagedReference(value="group")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JsonManagedReference(value="recipe")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JsonManagedReference(value="family")
    @JoinColumn(name="family_id")
    private Family responsibleFamily;

    @Column
    private LocalDate deliveryDate;
    @Column
    private LocalTime deliveryTime;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="mealDelivery")
    @JoinColumn(name="meal_id")
    private List<MealDelivery> mealDeliveries;

    @Column(length=512)
    private String notes;

    @Column
    @JsonIgnore
    private boolean isActive = true;

    public Meal(){

    }
    public Meal(MealRequest mRequest, List<Family> familyList){
        this.deliveryDate = mRequest.getDeliveryDate();
        this.deliveryTime = mRequest.getDeliveryTime();
        this.notes = mRequest.getNotes();
        this.mealDeliveries = new ArrayList<MealDelivery>();
        buildDeliveryList(familyList, mRequest);
    }

    public Meal(long mealId, Group group, Recipe recipe, Family family, LocalDate deliveryDate, LocalTime deliveryTime,
            List<MealDelivery> mealDeliveries, String notes) {
        this.mealId = mealId;
        this.group = group;
        this.recipe = recipe;
        this.responsibleFamily = family;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.mealDeliveries = mealDeliveries;
        this.notes = notes;
    }

    private void buildDeliveryList(List<Family> familyList, MealRequest mRequest){
        for (Family family: familyList){
            MealDelivery md = new MealDelivery();
            md.setFamily(family);
            md.setMeal(this);
            this.mealDeliveries.add(md);
        };
        
    }
    public void delete(){
        this.isActive = false;
    }
    
}
