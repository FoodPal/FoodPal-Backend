package com.foodpal.foodpal;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroceryList {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long Id;
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
        return Id;
    }
}
