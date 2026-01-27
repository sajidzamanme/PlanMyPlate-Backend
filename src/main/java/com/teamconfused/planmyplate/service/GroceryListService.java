package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.GroceryList;
import com.teamconfused.planmyplate.entity.User;
import com.teamconfused.planmyplate.repository.GroceryListRepository;
import com.teamconfused.planmyplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceryListService {

    private final GroceryListRepository repository;
    private final UserRepository userRepository;

    public List<GroceryList> getAllByUserId(Integer userId) {
        return repository.findByUser_UserId(userId);
    }

    public GroceryList getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Grocery list not found"));
    }

    public GroceryList create(Integer userId, GroceryList groceryList) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        groceryList.setUser(user);
        groceryList.setDateCreated(LocalDate.now());
        groceryList.setStatus("active");
        return repository.save(groceryList);
    }

    public GroceryList update(Integer id, GroceryList groceryList) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Grocery list not found");
        }
        GroceryList existing = repository.findById(id).get();
        if (groceryList.getStatus() != null) {
            existing.setStatus(groceryList.getStatus());
        }
        if (groceryList.getIngredients() != null) {
            existing.setIngredients(groceryList.getIngredients());
        }
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Grocery list not found");
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
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    newList.setUser(user);
                    newList.setDateCreated(LocalDate.now());
                    newList.setStatus("active");
                    newList.setIngredients(new java.util.HashSet<>());
                    return repository.save(newList);
                });

        // Initialize if null (though orElseGet handles it, existing records might have
        // null)
        if (activeList.getIngredients() == null) {
            activeList.setIngredients(new java.util.HashSet<>());
        }

        activeList.getIngredients().addAll(ingredients);
        repository.save(activeList);
    }

    private final InventoryService inventoryService;

    @org.springframework.transaction.annotation.Transactional
    public void purchaseItems(Integer listId, List<Integer> ingredientIds) {
        GroceryList list = repository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Grocery list not found"));

        if (ingredientIds == null || ingredientIds.isEmpty())
            return;

        // Use an iterator to safely remove while iterating
        java.util.Iterator<com.teamconfused.planmyplate.entity.Ingredient> iterator = list.getIngredients().iterator();
        while (iterator.hasNext()) {
            com.teamconfused.planmyplate.entity.Ingredient ingredient = iterator.next();
            if (ingredientIds.contains(ingredient.getIngId())) {
                // Add to inventory (default quantity 1 for now as recipes don't strictly define
                // quantity yet)
                inventoryService.addToInventory(list.getUser().getUserId(), ingredient, 1);
                // Remove from grocery list
                iterator.remove();
            }
        }

        repository.save(list);
    }
}
