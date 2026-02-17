package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.entity.GroceryList;
import com.teamconfused.planmyplate.service.GroceryListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery-lists")
@RequiredArgsConstructor
public class GroceryListController {

    private final GroceryListService service;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroceryList>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getAllByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryList> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<GroceryList>> getByStatus(@PathVariable Integer userId, @PathVariable String status) {
        return ResponseEntity.ok(service.getByStatus(userId, status));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<GroceryList> create(@PathVariable Integer userId, @RequestBody GroceryList groceryList) {
        return ResponseEntity.ok(service.create(userId, groceryList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryList> update(@PathVariable Integer id, @RequestBody GroceryList groceryList) {
        return ResponseEntity.ok(service.update(id, groceryList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Void> purchase(@PathVariable Integer id,
            @RequestBody com.teamconfused.planmyplate.dto.PurchaseRequestDto dto) {
        service.purchaseItems(id, dto.getItems());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{listId}/items/{itemId}")
    public ResponseEntity<com.teamconfused.planmyplate.entity.GroceryListItem> updateItem(
            @PathVariable Integer listId, // Not strictly needed if itemId is unique, but good for validation if we
                                          // wanted to check ownership
            @PathVariable Integer itemId,
            @RequestBody com.teamconfused.planmyplate.dto.UpdateItemRequestDto request) {
        return ResponseEntity.ok(service.updateItem(itemId, request));
    }
}
