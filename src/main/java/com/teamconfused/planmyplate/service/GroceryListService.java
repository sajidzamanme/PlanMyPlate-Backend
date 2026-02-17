package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.GroceryList;
import com.teamconfused.planmyplate.entity.User;
import com.teamconfused.planmyplate.exception.ResourceNotFoundException;
import com.teamconfused.planmyplate.repository.GroceryListRepository;
import com.teamconfused.planmyplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
public class GroceryListService {

    private final GroceryListRepository repository;
    private final UserRepository userRepository;

    public List<GroceryList> getAllByUserId(Integer userId) {
        return repository.findByUser_UserId(userId);
    }

    public GroceryList getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grocery list", id));
    }

    public GroceryList create(Integer userId, GroceryList groceryList) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        groceryList.setUser(user);
        groceryList.setDateCreated(LocalDate.now());
        groceryList.setStatus("active");
        return repository.save(groceryList);
    }

    public GroceryList update(Integer id, GroceryList groceryList) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Grocery list", id);
        }
        GroceryList existing = repository.findById(id).get();
        if (groceryList.getStatus() != null) {
            existing.setStatus(groceryList.getStatus());
        }
        if (groceryList.getItems() != null) {
            existing.setItems(groceryList.getItems());
        }
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Grocery list", id);
        }
        repository.deleteById(id);
    }

    public List<GroceryList> getByStatus(Integer userId, String status) {
        return repository.findByUser_UserIdAndStatus(userId, status);
    }

    public void addIngredients(Integer userId,
            java.util.Set<com.teamconfused.planmyplate.entity.Ingredient> ingredients) {
        if (ingredients == null || ingredients.isEmpty())
            return;

        // Find active grocery list or create new
        GroceryList activeList = repository.findByUser_UserIdAndStatus(userId, "active").stream().findFirst()
                .orElseGet(() -> {
                    GroceryList newList = new GroceryList();
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User", userId));
                    newList.setUser(user);
                    newList.setDateCreated(LocalDate.now());
                    newList.setStatus("active");
                    newList.setItems(new java.util.ArrayList<>());
                    return repository.save(newList);
                });

        if (activeList.getItems() == null) {
            activeList.setItems(new java.util.ArrayList<>());
        }

        for (com.teamconfused.planmyplate.entity.Ingredient ingredient : ingredients) {
            // Check if ingredient already exists in list
            boolean exists = activeList.getItems().stream()
                    .anyMatch(item -> item.getIngredient().getIngId().equals(ingredient.getIngId()));

            if (!exists) {
                com.teamconfused.planmyplate.entity.GroceryListItem newItem = new com.teamconfused.planmyplate.entity.GroceryListItem();
                newItem.setGroceryList(activeList);
                newItem.setIngredient(ingredient);
                newItem.setQuantity(1); // Default quantity
                newItem.setUnit("unit"); // Default unit
                activeList.getItems().add(newItem);
            }
        }

        repository.save(activeList);
    }

    /**
     * Add ingredients with specific quantities and units to the grocery list.
     * Aggregates quantities if the same ingredient already exists.
     */
    public void addIngredientsWithQuantities(Integer userId,
            java.util.List<com.teamconfused.planmyplate.dto.IngredientQuantityDto> ingredientData) {
        if (ingredientData == null || ingredientData.isEmpty())
            return;

        // Find active grocery list or create new
        GroceryList activeList = repository.findByUser_UserIdAndStatus(userId, "active").stream().findFirst()
                .orElseGet(() -> {
                    GroceryList newList = new GroceryList();
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User", userId));
                    newList.setUser(user);
                    newList.setDateCreated(LocalDate.now());
                    newList.setStatus("active");
                    newList.setItems(new java.util.ArrayList<>());
                    return repository.save(newList);
                });

        if (activeList.getItems() == null) {
            activeList.setItems(new java.util.ArrayList<>());
        }

        for (com.teamconfused.planmyplate.dto.IngredientQuantityDto data : ingredientData) {
            // Check if ingredient already exists in list with same unit
            com.teamconfused.planmyplate.entity.GroceryListItem existingItem = activeList.getItems().stream()
                    .filter(item -> item.getIngredient().getIngId().equals(data.getIngredient().getIngId())
                            && item.getUnit().equalsIgnoreCase(data.getUnit()))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                // Aggregate quantity
                existingItem.setQuantity(existingItem.getQuantity() + data.getQuantity());
            } else {
                // Add new item
                com.teamconfused.planmyplate.entity.GroceryListItem newItem = new com.teamconfused.planmyplate.entity.GroceryListItem();
                newItem.setGroceryList(activeList);
                newItem.setIngredient(data.getIngredient());
                newItem.setQuantity(data.getQuantity());
                newItem.setUnit(data.getUnit());
                activeList.getItems().add(newItem);
            }
        }

        repository.save(activeList);
    }

    private final com.teamconfused.planmyplate.repository.GroceryListItemRepository itemRepository;

    public com.teamconfused.planmyplate.entity.GroceryListItem updateItem(Integer itemId,
            com.teamconfused.planmyplate.dto.UpdateItemRequestDto request) {
        com.teamconfused.planmyplate.entity.GroceryListItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery list item", itemId));

        if (request.getQuantity() != null) {
            if (request.getQuantity() <= 0) {
                // Remove logic could go here, or throw error depending on requirements
                // For now, let's keep it but maybe handle removal via DELETE endpoint
                // explicitly
            }
            item.setQuantity(request.getQuantity());
        }
        if (request.getUnit() != null) {
            item.setUnit(request.getUnit());
        }

        return itemRepository.save(item);
    }

    private final InventoryService inventoryService;

    @org.springframework.transaction.annotation.Transactional
    public void purchaseItems(Integer listId, List<com.teamconfused.planmyplate.dto.PurchaseItemInfo> purchaseItems) {
        GroceryList list = repository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery list", listId));

        if (purchaseItems == null || purchaseItems.isEmpty())
            return;

        // Use a map for faster lookup of purchase info by itemId
        java.util.Map<Integer, Integer> purchaseMap = new java.util.HashMap<>();
        for (com.teamconfused.planmyplate.dto.PurchaseItemInfo info : purchaseItems) {
            purchaseMap.put(info.getItemId(), info.getQuantity());
        }

        // Use an iterator to safely remove while iterating
        java.util.Iterator<com.teamconfused.planmyplate.entity.GroceryListItem> iterator = list.getItems().iterator();
        while (iterator.hasNext()) {
            com.teamconfused.planmyplate.entity.GroceryListItem item = iterator.next();
            if (purchaseMap.containsKey(item.getId())) {
                Integer purchasedQty = purchaseMap.get(item.getId());
                log.info("Processing purchase for item ID {}: {} units (Current: {})", item.getId(), purchasedQty,
                        item.getQuantity());

                // Ensure we don't purchase more than we have (though logic should allow if
                // desired)
                // For now, let's just use what's sent.

                // Add to inventory with purchased quantity and unit
                inventoryService.addToInventory(list.getUser().getUserId(), item.getIngredient(),
                        purchasedQty, item.getUnit());

                // Update grocery list item quantity
                int remainingQty = item.getQuantity() - purchasedQty;
                if (remainingQty <= 0) {
                    log.info("Removing item ID {} from grocery list (fully purchased)", item.getId());
                    iterator.remove();
                    itemRepository.delete(item); // Explicit delete for safety
                } else {
                    log.info("Updating item ID {} quantity to {}", item.getId(), remainingQty);
                    item.setQuantity(remainingQty);
                    itemRepository.save(item); // Explicit save for safety
                }
            }
        }

        repository.saveAndFlush(list);
    }
}
