package com.meals.apimeals.Models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Grocery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groceryId;

    @Column
    private String itemName;

    @Column
    private String link;

    @Column
    private float quantity;

    @Column
    private String measure;

    @Column
    private boolean longTerm;

    @Column
    private BigDecimal price;


    public Grocery(){

    }
    public Grocery(long groceryId, String itemName, String link, float quantity, String measure, boolean longTerm,
            BigDecimal price) {
        this.groceryId = groceryId;
        this.itemName = itemName;
        this.link = link = "";
        this.quantity = quantity = 1;
        this.measure = measure = "";
        this.longTerm = longTerm = false;
        this.price = price;
        }
}
