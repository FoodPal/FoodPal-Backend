package com.foodpal.foodpal;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroceryList {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(mappedBy = "list")
    List<GroceryListItem> items;

    private String name;

    @ManyToOne
    private Account account;


}
