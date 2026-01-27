package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.entity.Inventory;
import com.teamconfused.planmyplate.entity.InvItem;
import com.teamconfused.planmyplate.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    
    private final InventoryService service;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Inventory> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @PostMapping("/user/{userId}")
    public ResponseEntity<Inventory> createForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.createForUser(userId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> update(@PathVariable Integer id, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(service.update(id, inventory));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{inventoryId}/items")
    public ResponseEntity<List<InvItem>> getInventoryItems(@PathVariable Integer inventoryId) {
        return ResponseEntity.ok(service.getInventoryItems(inventoryId));
    }
    
    @PostMapping("/{inventoryId}/items")
    public ResponseEntity<InvItem> addItem(@PathVariable Integer inventoryId, @RequestBody InvItem item) {
        return ResponseEntity.ok(service.addItemToInventory(inventoryId, item));
    }
    
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Integer itemId) {
        service.removeItemFromInventory(itemId);
        return ResponseEntity.ok().build();
    }
}
