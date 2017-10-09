package com.foodpal.foodpal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class GroceryList {

    @OneToMany(mappedBy = "list")
    List<GroceryListItem> items;

    private String name;

    @ManyToOne
    private User user;


}
