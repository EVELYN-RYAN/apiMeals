package com.meals.apimeals.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.meals.apimeals.Models.MealDelivery;
import com.meals.apimeals.Repo.MealDeliveryRepo;

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
public class MealDeliveryController {

    @Autowired
    private MealDeliveryRepo mealDeliveryRepo;

    @GetMapping("/api/mealDeliveries")
    public ResponseEntity<List<MealDelivery>> getFamilies() {
        List<MealDelivery> mealDeliveryList = mealDeliveryRepo.findAll();
        return new ResponseEntity<List<MealDelivery>>(mealDeliveryList, HttpStatus.OK);
    }
    @GetMapping("/api/mealDelivery/{mealDeliveryId}")
    public ResponseEntity<MealDelivery> getMealDelivery(@PathVariable long mealDeliveryId){
        MealDelivery mealDelivery = mealDeliveryRepo.findByDeliveryId(mealDeliveryId);
        return new ResponseEntity<MealDelivery>(mealDelivery, HttpStatus.OK);
    }
    @PostMapping("/api/mealDelivery")
    public ResponseEntity<MealDelivery> saveMealDelivery(@RequestBody MealDelivery mealDelivery){
        mealDelivery = mealDeliveryRepo.save(mealDelivery);
        return new ResponseEntity<MealDelivery>(mealDelivery, HttpStatus.OK);
    }

    @PutMapping("/api/mealDelivery")
    public ResponseEntity<MealDelivery> updateMealDelivery(@RequestBody MealDelivery mealDelivery) {        
        return new ResponseEntity<MealDelivery>(mealDeliveryRepo.save(mealDelivery),HttpStatus.OK);
    }
    
    @DeleteMapping("/api/mealDelivery/{mealDeliveryId}")
    public ResponseEntity<String> deleteMealDelivery(@PathVariable long mealDeliveryId){
        MealDelivery deleteMealDelivery = mealDeliveryRepo.findByDeliveryId(mealDeliveryId);
        mealDeliveryRepo.delete(deleteMealDelivery);
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }
    
}

