package com.foodpal.foodpal;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroceryList {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long id;
    @OneToMany(mappedBy = "list")
    List<GroceryListItem> items;

    private String name;

    @ManyToOne
    private Account account;

    public GroceryList(){}

    public GroceryList(String name, Account account){
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @JsonGetter("total-cost")
    public Double cost(){
        Double cost = 0.0;
        for(GroceryListItem item: items){
           cost += item.getCost();
        }

        return cost;
    }
}
