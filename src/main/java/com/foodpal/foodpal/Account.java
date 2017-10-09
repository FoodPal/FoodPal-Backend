package com.foodpal.foodpal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(mappedBy = "user")
    private List<GroceryList> lists;

    public String username;

    @JsonIgnore
    public String password;

    private String email;

    private int zipcode;
}
