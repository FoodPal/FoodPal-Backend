package com.foodpal.foodpal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroceryListItemRepository extends CrudRepository<GroceryListItem, Long> {
    List<GroceryListItem> findByList(GroceryList list);
    Optional<GroceryListItem> findById(Long id);
}
