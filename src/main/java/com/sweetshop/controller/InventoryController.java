package com.sweetshop.controller;

import com.sweetshop.dto.PurchaseRequest;
import com.sweetshop.dto.RestockRequest;
import com.sweetshop.model.Sweet;
import com.sweetshop.service.InventoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/{id}/purchase")
@PreAuthorize("hasRole('USER')")
public Sweet purchaseSweet(
        @PathVariable String id,
        @RequestBody PurchaseRequest request
) {
    return inventoryService.purchaseSweet(id, request.getQuantity());
}

    @PostMapping("/{id}/restock")
@PreAuthorize("hasRole('ADMIN')")
public Sweet restockSweet(
        @PathVariable String id,
        @RequestBody RestockRequest request
) {
    return inventoryService.restockSweet(id, request.getQuantity());
}

}