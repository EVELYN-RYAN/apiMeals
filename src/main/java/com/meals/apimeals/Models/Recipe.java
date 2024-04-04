package com.meals.apimeals.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recipe {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long recipeId;

   @Column
   private String name;

   @Column
   private String description;

   @Column
   private String cookTime;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
   @JsonManagedReference(value="Ingredients")
   @JoinColumn(name = "recipe_id")
   private List<Ingredient> ingredients;

   @Column
   private List<String> directions;

   @Column
   private int numberOfServings;

   @Column
   private boolean approved;

   @Column
   @JsonIgnore
   private boolean isActive = true;

   public Recipe(){
    
   }

    public Recipe(long recipeId, String name, String description, String cookTime, List<Ingredient> ingredients,
            List<String> directions,int numberOfServings, boolean approved) {
        this.recipeId = recipeId;
        this.name = name;
        this.description = description;
        this.cookTime = cookTime;
        this.ingredients = ingredients;
        this.directions = directions;
        this.numberOfServings = numberOfServings;
        this.approved = approved;
    }
    public void delete(){
        this.isActive = false;
      }
   
}
