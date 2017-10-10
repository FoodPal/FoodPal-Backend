package com.foodpal.foodpal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GroceryListNotFoundException extends RuntimeException {
    public GroceryListNotFoundException(Long list) {
        super("Grocery list " + list + " not found");
    }
}
