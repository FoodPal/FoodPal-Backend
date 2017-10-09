package com.foodpal.foodpal;

import javax.persistence.*;

@Entity
public class GroceryListItem {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;
    private String description;
    private Double cost;

    @ManyToOne
    private GroceryList list;

    private String category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getCost() {
        return cost;
    }

    public String getCategory() {
        return category;
    }
}
