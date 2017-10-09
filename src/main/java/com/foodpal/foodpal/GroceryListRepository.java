package com.foodpal.foodpal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroceryListRepository extends CrudRepository<GroceryList, Long> {
    List<GroceryList> findByAccount(Account account);
    List<GroceryList> findByAccount_Username(String username);
}
