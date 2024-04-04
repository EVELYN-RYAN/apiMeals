package com.meals.apimeals.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meals.apimeals.Models.Ingredient;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
    Ingredient findByIngredientId(long ingredientId);
}
