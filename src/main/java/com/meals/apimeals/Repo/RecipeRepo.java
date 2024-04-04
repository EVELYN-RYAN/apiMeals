package com.meals.apimeals.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meals.apimeals.Models.Recipe;


@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    public Recipe findByRecipeId(long recipeId);
}
