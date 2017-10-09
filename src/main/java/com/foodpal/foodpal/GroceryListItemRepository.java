package com.foodpal.foodpal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroceryListItemRepository extends CrudRepository<GroceryListItem, Long> {
    List<GroceryListItem> findByList(GroceryList list);
}
