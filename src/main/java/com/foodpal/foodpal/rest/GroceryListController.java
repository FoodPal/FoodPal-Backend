package com.foodpal.foodpal.rest;

import com.foodpal.foodpal.AccountRepository;
import com.foodpal.foodpal.GroceryList;
import com.foodpal.foodpal.GroceryListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/grocery-lists")
public class GroceryListController {

    private final AccountRepository accountRepository;
    private final GroceryListRepository groceryListRepository;

    @Autowired
    GroceryListController(AccountRepository accountRepository, GroceryListRepository groceryListRepository){
        this.accountRepository = accountRepository;
        this.groceryListRepository = groceryListRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<GroceryList> getGroceryLists(Principal principal){
        this.validateUser(principal);

        return groceryListRepository
                .findByAccount_Username(principal.getName());
//        return StreamSupport.stream(groceryListRepository.findAll().spliterator(), false)
//                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(Principal principal, @RequestBody GroceryList input){
        this.validateUser(principal);

        return this.accountRepository
                .findByUsername(principal.getName())
                .map(account -> {
                   GroceryList groceryList = groceryListRepository.save(new GroceryList(input.getName(), account));

                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(groceryList.getId()).toUri();

                    return ResponseEntity.created(location).build();
                }).orElse(ResponseEntity.noContent().build());
    }


    private void validateUser(Principal principal) {
        String userId = principal.getName();
        this.accountRepository
                .findByUsername(userId);
    }
}
