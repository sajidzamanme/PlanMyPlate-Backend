package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.Inventory;
import com.teamconfused.planmyplate.entity.InvItem;
import com.teamconfused.planmyplate.entity.User;
import com.teamconfused.planmyplate.exception.DuplicateResourceException;
import com.teamconfused.planmyplate.exception.ResourceNotFoundException;
import com.teamconfused.planmyplate.repository.InventoryRepository;
import com.teamconfused.planmyplate.repository.InvItemRepository;
import com.teamconfused.planmyplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;
    private final InvItemRepository invItemRepository;
    private final UserRepository userRepository;

    public Inventory getByUserId(Integer userId) {
        return repository.findByUser_UserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory for user", userId));
    }

    public Inventory getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventory", id));
    }

    public Inventory createForUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));

        if (repository.findByUser_UserId(userId).isPresent()) {
            throw new DuplicateResourceException("Inventory", "userId", userId);
        }

        Inventory inventory = new Inventory();
        inventory.setUser(user);
        inventory.setLastUpdate(LocalDate.now());
        return repository.save(inventory);
    }

    public Inventory update(Integer id, Inventory inventory) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Inventory", id);
        }
        Inventory existing = repository.findById(id).get();
        existing.setLastUpdate(LocalDate.now());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Inventory", id);
        }
        repository.deleteById(id);
    }

    public List<InvItem> getInventoryItems(Integer inventoryId) {
        if (!repository.existsById(inventoryId)) {
            throw new ResourceNotFoundException("Inventory", inventoryId);
        }
        return invItemRepository.findByInventory_InvId(inventoryId);
    }

    public InvItem addItemToInventory(Integer inventoryId, InvItem item) {
        Inventory inventory = repository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", inventoryId));
        item.setInventory(inventory);
        return invItemRepository.save(item);
    }

    public void removeItemFromInventory(Integer itemId) {
        if (!invItemRepository.existsById(itemId)) {
            throw new ResourceNotFoundException("Inventory item", itemId);
        }
        invItemRepository.deleteById(itemId);
    }

    public InvItem addToInventory(Integer userId, com.teamconfused.planmyplate.entity.Ingredient ingredient,
            int quantity) {
        Inventory inventory = repository.findByUser_UserId(userId)
                .orElseGet(() -> createForUser(userId));

        // Check if item already exists
        List<InvItem> items = invItemRepository.findByInventory_InvId(inventory.getInvId());
        for (InvItem existing : items) {
            if (existing.getIngredient().getIngId().equals(ingredient.getIngId())) {
                existing.setQuantity(existing.getQuantity() + quantity);
                // InvItem doesn't have lastUpdate, only Inventory does
                inventory.setLastUpdate(LocalDate.now());
                repository.save(inventory);
                return invItemRepository.save(existing);
            }
        }

        InvItem newItem = new InvItem();
        newItem.setInventory(inventory);
        newItem.setIngredient(ingredient);
        newItem.setQuantity(quantity);
        newItem.setDateAdded(LocalDate.now());
        // Default expiry date logic could go here, e.g., +1 week
        newItem.setExpiryDate(LocalDate.now().plusWeeks(1));

        inventory.setLastUpdate(LocalDate.now());
        repository.save(inventory);

        return invItemRepository.save(newItem);
    }

    public InvItem updateItem(Integer itemId, com.teamconfused.planmyplate.dto.UpdateItemRequestDto request) {
        InvItem item = invItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item", itemId));

        if (request.getQuantity() != null) {
            item.setQuantity(request.getQuantity());
            if (request.getQuantity() <= 0) {
                // Logic for 0 quantity: remove item
                invItemRepository.delete(item);
                return null; // Or return a "deleted" status wrapper
            }
        }
        if (request.getExpiryDate() != null) {
            item.setExpiryDate(request.getExpiryDate());
        }

        // Update parent inventory last update time
        Inventory inventory = item.getInventory();
        inventory.setLastUpdate(LocalDate.now());
        repository.save(inventory);

        return invItemRepository.save(item);
    }
}
