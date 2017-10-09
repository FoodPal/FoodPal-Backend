package com.foodpal.foodpal;

import javax.persistence.*;

@Entity
public class GroceryListItem {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private Long Id;

    private String name;
    private String description;
    private Double cost;

    @ManyToOne
    private GroceryList list;

    private String category;
}
