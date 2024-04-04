package com.meals.apimeals.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meals.apimeals.Models.Group;
import com.meals.apimeals.Models.Meal;



public interface MealRepo extends JpaRepository<Meal, Long> {
    Meal findByMealId(long mealId);

    @Query(
        value = "SELECT m FROM Meal m WHERE m.group = ?1 AND m.deliveryDate >= ?2"
    )
    List<Meal> findByGroupAndDeliveryDate(Group group, LocalDate deliveryDate );
}
