package com.foodpal.foodpal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToMany(mappedBy = "account")
    private List<GroceryList> lists;

    public String username;

    @JsonIgnore
    public String password;

    private String email;

    private int zipcode;

    public Account() {}

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
}
