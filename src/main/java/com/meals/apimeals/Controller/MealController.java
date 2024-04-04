package com.meals.apimeals.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.meals.apimeals.Models.Meal;
import com.meals.apimeals.Models.Recipe;
import com.meals.apimeals.Models.Family;
import com.meals.apimeals.Models.Group;
import com.meals.apimeals.Repo.FamilyRepo;
import com.meals.apimeals.Repo.GroupRepo;
import com.meals.apimeals.Repo.MealRepo;
import com.meals.apimeals.Repo.RecipeRepo;
import com.meals.apimeals.Request.MealRequest;
import com.meals.apimeals.Request.QueryRequest;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
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
public class MealController {

    @Autowired
    private MealRepo mealRepo;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private FamilyRepo familyRepo;
    @Autowired
    private RecipeRepo recipeRepo;

    @GetMapping("/api/meals")
    public ResponseEntity<List<Meal>> getMeals() {
        List<Meal> mealList = mealRepo.findAll();
        return new ResponseEntity<List<Meal>>(mealList, HttpStatus.OK);
    }
    @GetMapping("/api/meal/{mealId}")
    public ResponseEntity<Meal> getMeal(@PathVariable long mealId){
        Meal meal = mealRepo.findByMealId(mealId);
        return new ResponseEntity<Meal>(meal, HttpStatus.OK);
    }
    @PostMapping("/api/search/meals")
    public ResponseEntity<List<Meal>> queryMeals(@RequestBody QueryRequest qr){
        Group group = groupRepo.findByGroupId(qr.getGroupId());
        LocalDate backDate = LocalDate.now().minusDays(qr.getDaysBack());
        List<Meal> meals = mealRepo.findByGroupAndDeliveryDate(group, backDate);
        return new ResponseEntity<List<Meal>>(meals, HttpStatus.OK);
    }
    @PostMapping("/api/meal")
    public ResponseEntity<Meal> saveMeal(@RequestBody MealRequest mRequest){
        Group group = groupRepo.findByGroupId(mRequest.getGroupId());
        List<Family> deliveryList = familyRepo.findByGroup(group);        
        Meal meal = new Meal(mRequest, deliveryList);
        Family family = familyRepo.findByFamilyId(mRequest.getResponsibleFamilyId());
        Recipe recipe = recipeRepo.findByRecipeId(mRequest.getRecipeId());
        meal.setGroup(group);
        meal.setResponsibleFamily(family);
        meal.setRecipe(recipe);
        meal = mealRepo.save(meal);
        return new ResponseEntity<Meal>(meal, HttpStatus.OK);
    }

    @PutMapping("/api/meal")
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal) {        
        return new ResponseEntity<Meal>(mealRepo.save(meal),HttpStatus.OK);
    }
    
    @DeleteMapping("/api/meal/{mealId}")
    public ResponseEntity<String> deleteMeal(@PathVariable long mealId){
        Meal deleteMeal = mealRepo.findByMealId(mealId);
        mealRepo.delete(deleteMeal);
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }
    
}

