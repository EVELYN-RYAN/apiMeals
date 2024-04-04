package com.meals.apimeals.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryRequest {

    private int daysBack = 0;
    
    private long groupId;

    public QueryRequest(){

    }
}
