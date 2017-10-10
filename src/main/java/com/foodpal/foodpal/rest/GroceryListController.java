package com.foodpal.foodpal.rest;

import com.foodpal.foodpal.*;
import com.foodpal.foodpal.exceptions.GroceryListNotFoundException;
import com.foodpal.foodpal.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grocery-lists")
public class GroceryListController {

    private final AccountRepository accountRepository;
    private final GroceryListRepository groceryListRepository;
    private final GroceryListItemRepository groceryListItemRepository;

    @Autowired
    GroceryListController(AccountRepository accountRepository, GroceryListRepository groceryListRepository, GroceryListItemRepository groceryListItemRepository){
        this.accountRepository = accountRepository;
        this.groceryListRepository = groceryListRepository;
        this.groceryListItemRepository = groceryListItemRepository;
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

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> destroy(Principal principal, @RequestBody GroceryList input){
        this.validateUser(principal);

        return this.accountRepository
                .findByUsername(principal.getName())
                .map(account -> groceryListRepository.findByAccountAndId(account, input.getId())
                        .map(groceryList -> {
                             groceryListRepository.delete(groceryList.getId());
                            return new ResponseEntity<>(HttpStatus.OK);
                        }).orElseThrow(() -> new GroceryListNotFoundException(input.getId()))).orElseThrow(() -> new UserNotFoundException(principal.getName()));
    }

    @RequestMapping(path = "/{id}/items")
    ResponseEntity<?> addToList(Principal principal, @RequestParam Long id, @RequestBody GroceryListItem input){
        this.validateUser(principal);
        Account account = this.accountRepository.findByUsername(principal.getName()).get();
        Optional<GroceryList> groceryList = this.groceryListRepository.findByAccountAndId(account, id);
        Optional<GroceryListItem> groceryListItem = this.groceryListItemRepository.findById(input.getId());

        if(groceryList.isPresent() && groceryListItem.isPresent()){
            groceryList.get().getItems().add(groceryListItem.get());
            return ResponseEntity.ok().build();
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    private void validateUser(Principal principal) {
        String userId = principal.getName();
        this.accountRepository
                .findByUsername(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
