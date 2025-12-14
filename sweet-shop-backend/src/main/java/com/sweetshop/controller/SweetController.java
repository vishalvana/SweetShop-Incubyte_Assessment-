package com.sweetshop.controller;

import com.sweetshop.model.Sweet;
import com.sweetshop.service.SweetService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class SweetController {

    private final SweetService sweetService;

    @PostMapping
    public Sweet addSweet(@RequestBody Sweet sweet) {
        return sweetService.addSweet(sweet);
    }

    @PutMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Sweet updateSweet(@PathVariable String id,
                             @RequestBody Sweet sweet) {
        return sweetService.updateSweet(id, sweet);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<Sweet> getAllSweets() {
        return sweetService.getAllSweets();
    }

    @GetMapping("/search/name")
    @PreAuthorize("permitAll()")
    public List<Sweet> searchByName(@RequestParam String name) {
        return sweetService.searchByName(name);
    }

    @GetMapping("/search/price")
    @PreAuthorize("permitAll()")
    public List<Sweet> searchByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return sweetService.searchByPriceRange(min, max);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSweet(@PathVariable String id) {
        sweetService.deleteSweet(id);
    }
}
