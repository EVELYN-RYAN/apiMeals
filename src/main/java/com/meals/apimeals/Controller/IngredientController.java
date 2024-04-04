package com.meals.apimeals.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meals.apimeals.Models.Grocery;
import com.meals.apimeals.Models.Ingredient;
import com.meals.apimeals.Repo.GroceryRepo;
import com.meals.apimeals.Repo.IngredientRepo;
import com.meals.apimeals.Request.IngredientRequest;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class IngredientController {

    @Autowired
    private IngredientRepo ingredientRepo;

    @Autowired
    private GroceryRepo groceryRepo;
    
    @GetMapping(value = "/api/ingredients")
    public List<Ingredient> geIngredients(){
        return ingredientRepo.findAll();
    }

    @PostMapping(value = "/api/ingredients")
    public ResponseEntity<Ingredient> saveIngredient(@RequestBody IngredientRequest iRequest){
        Grocery grocery = groceryRepo.findByGroceryId(iRequest.getGroceryId());

        Ingredient ingredient = new Ingredient(iRequest);
        ingredient.setGrocery(grocery);
        ingredientRepo.save(ingredient);
        return new ResponseEntity<Ingredient>(ingredient, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/ingredients")
    public Ingredient updateIngredient(@RequestBody  Ingredient ingredient){
        return ingredientRepo.save(ingredient);
    }
    
    @DeleteMapping(value = "/api/ingredients/{ingredientId}")
    public String deleteIngredient(@PathVariable long ingredientId){
        Ingredient deleteIngredient = ingredientRepo.findByIngredientId(ingredientId);
        ingredientRepo.delete(deleteIngredient);
        return "DELETED";
    }    
    
}
