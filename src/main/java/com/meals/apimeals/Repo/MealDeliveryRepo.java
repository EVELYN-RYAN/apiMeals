package com.meals.apimeals.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meals.apimeals.Models.MealDelivery;



public interface MealDeliveryRepo extends JpaRepository<MealDelivery, Long> {
    
    MealDelivery findByDeliveryId(long deliveryId);

}
