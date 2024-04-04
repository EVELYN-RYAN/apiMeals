package com.meals.apimeals.Request;

import java.util.List;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class IngredientRequest {

    private String name;

    private float amount;

    private String measure;

    private long groceryId;

    private List<String> allergyTags;
}
