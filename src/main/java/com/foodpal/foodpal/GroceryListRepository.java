package com.foodpal.foodpal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroceryListRepository extends CrudRepository<GroceryList, Long> {
    List<GroceryList> findByAccount(Account account);
    List<GroceryList> findByAccount_Username(String username);
    Optional<GroceryList> findByAccountAndId(Account account, Long Id);
}
