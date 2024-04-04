package com.meals.apimeals.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.meals.apimeals.Request.IngredientRequest;

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
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientId;

    @Column
    private String name;

    @Column
    private float amount;

    @Column
    private String measure;

    @ManyToOne
    @JoinColumn(name="grocery_id")
    private Grocery grocery;

    @ManyToOne
    @JsonBackReference(value="Ingredients")
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @Column
    private List<String> allergyTags;

    public Ingredient(){

    }
    public Ingredient(IngredientRequest req) {
        this.name = req.getName();
        this.amount = req.getAmount();
        this.measure = req.getMeasure();
        this.allergyTags = req.getAllergyTags();
    }
    
    public Ingredient(long ingredientId, String name, float amount, String measure, Grocery grocery, Recipe recipe,
            List<String> allergyTags) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.amount = amount;
        this.measure = measure;
        this.grocery = grocery;
        this.recipe = recipe;
        this.allergyTags = allergyTags;
    }
}
