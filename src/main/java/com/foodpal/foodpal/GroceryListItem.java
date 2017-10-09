package com.foodpal.foodpal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class GroceryListItem {

    private String name;
    private String description;
    private Double cost;

    @ManyToOne
    private GroceryList list;
}
