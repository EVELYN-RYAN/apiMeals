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
import com.meals.apimeals.Repo.GroceryRepo;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class GroceryController {


    @Autowired
    private GroceryRepo groceryRepo;
    

    @GetMapping(value = "/api/groceries")
    public List<Grocery> getGroceries(){
        return groceryRepo.findAll();
    }

    @PostMapping(value = "/api/groceries")
    public ResponseEntity<Grocery> saveGrocery(@RequestBody Grocery grocery){
        groceryRepo.save(grocery);
        return new ResponseEntity<Grocery>(grocery, HttpStatus.CREATED);
    }
    // @PostMapping(value = "/api/groceries")
    // public ResponseEntity<Grocery> saveGrocery(@RequestBody GroceryRequest gRequest){
    //     Grocery grocery = new Grocery(gRequest);
    //     grocery = groceryRepo.save(grocery);
    //     return new ResponseEntity<Grocery>(grocery, HttpStatus.CREATED);
    // }

    @PutMapping(value = "/api/groceries")
    public Grocery updateGrocery(@RequestBody  Grocery grocery){
        return groceryRepo.save(grocery);
    }
    
    @DeleteMapping(value = "/api/groceries/{groceryId}")
    public ResponseEntity<String> deleteGrocery(@PathVariable long groceryId){
        Grocery deleteGrocery = groceryRepo.findByGroceryId(groceryId);
        groceryRepo.delete(deleteGrocery);
        return new ResponseEntity<String>("DELETED", HttpStatus.NO_CONTENT);
    }    
}
