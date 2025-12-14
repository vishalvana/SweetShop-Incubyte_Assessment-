package com.sweetshop.service.impl;

import com.sweetshop.exception.InvalidOperationException;
import com.sweetshop.exception.ResourceNotFoundException;
import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import com.sweetshop.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final SweetRepository sweetRepository;

    @Override
    public Sweet purchaseSweet(String sweetId, int quantity) {
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        if (quantity <= 0) {
            throw new RuntimeException("Invalid purchase quantity");
        }

        if (sweet.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        sweet.setQuantity(sweet.getQuantity() - quantity);
        return sweetRepository.save(sweet);
    }

   @Override
    public Sweet restockSweet(String sweetId, int quantity) {
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new RuntimeException("Sweet not found"));

        sweet.setQuantity(sweet.getQuantity() + quantity);
        return sweetRepository.save(sweet);
    }
}
