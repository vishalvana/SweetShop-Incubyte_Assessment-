package com.sweetshop.service.impl;

import com.sweetshop.exception.InvalidOperationException;
import com.sweetshop.exception.ResourceNotFoundException;
import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import com.sweetshop.service.SweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetServiceImpl implements SweetService {

    private final SweetRepository sweetRepository;

    @Override
    public Sweet addSweet(Sweet sweet) {
        validateSweet(sweet);
        return sweetRepository.save(sweet);
    }

    @Override
    public Sweet updateSweet(String sweetId, Sweet updatedSweet) {
        Sweet existing = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Sweet not found"));

        validateSweet(updatedSweet);

        existing.setName(updatedSweet.getName());
        existing.setDescription(updatedSweet.getDescription());
        existing.setPrice(updatedSweet.getPrice());
        existing.setImageUrl(updatedSweet.getImageUrl());
        existing.setTag(updatedSweet.getTag());
        existing.setQuantity(updatedSweet.getQuantity());

        return sweetRepository.save(existing);
    }

    @Override
    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    @Override
    public List<Sweet> searchByName(String name) {
        return sweetRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Sweet> searchByPriceRange(BigDecimal min, BigDecimal max) {
        if (min.compareTo(max) > 0) {
            throw new InvalidOperationException("Min price cannot be greater than max price");
        }
        return sweetRepository.findByPriceBetween(min, max);
    }

    @Override
    public void deleteSweet(String sweetId) {
        if (!sweetRepository.existsById(sweetId)) {
            throw new ResourceNotFoundException("Sweet not found");
        }
        sweetRepository.deleteById(sweetId);
    }

    // -------------------------
    // Business Rule Validation
    // -------------------------
    private void validateSweet(Sweet sweet) {
        if (sweet.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidOperationException("Price cannot be negative");
        }

        if (sweet.getQuantity() < 0) {
            throw new InvalidOperationException("Quantity cannot be negative");
        }

        if (sweet.getName() == null || sweet.getName().isBlank()) {
            throw new InvalidOperationException("Sweet name is required");
        }

        // Validate image URL if provided
        if (sweet.getImageUrl() != null && !sweet.getImageUrl().isBlank()) {
            try {
                URL url = new URL(sweet.getImageUrl());
                String protocol = url.getProtocol();
                if (!protocol.equals("http") && !protocol.equals("https")) {
                    throw new InvalidOperationException("Image URL must use http or https");
                }
            } catch (MalformedURLException e) {
                throw new InvalidOperationException("Image URL is not a valid URL");
            }
        }
    }
}
