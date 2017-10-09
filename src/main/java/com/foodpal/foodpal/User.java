package com.foodpal.foodpal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    @OneToMany(mappedBy = "user")
    private List<GroceryList> lists;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private int zipcode;
}
