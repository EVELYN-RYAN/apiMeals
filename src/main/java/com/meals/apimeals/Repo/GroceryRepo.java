package com.meals.apimeals.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meals.apimeals.Models.Grocery;

@Repository
public interface GroceryRepo extends JpaRepository<Grocery, Long> {

    Grocery findByGroceryId(long groceryId);
    
}
