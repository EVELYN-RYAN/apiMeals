package com.meals.apimeals.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.meals.apimeals.Models.Recipe;
import com.meals.apimeals.Repo.RecipeRepo;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class RecipeController {

    @Autowired
    private RecipeRepo recipeRepo;

    @GetMapping("/api/recipes")
    public ResponseEntity<List<Recipe>> getRecipes() {
        List<Recipe> recipeList = recipeRepo.findAll();
        return new ResponseEntity<List<Recipe>>(recipeList, HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{recipeId}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long recipeId){
        Recipe recipe = recipeRepo.findByRecipeId(recipeId);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @PostMapping("/api/recipe")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe){
        recipe = recipeRepo.save(recipe);
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @PutMapping("/api/recipe")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {        
        return new ResponseEntity<Recipe>(recipeRepo.save(recipe),HttpStatus.OK);
    }

    @DeleteMapping("/api/recipe/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable long recipeId){
        Recipe deleteRecipe = recipeRepo.findByRecipeId(recipeId);
        recipeRepo.delete(deleteRecipe);
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }
    
}
