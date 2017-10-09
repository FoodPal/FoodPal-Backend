package com.foodpal.foodpal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class GroceryListItem {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;
    private String description;
    private Double cost;

    @ManyToOne
    private GroceryList list;

    private String category;
}
