package com.sweetshop.service;

import com.sweetshop.model.Sweet;

import java.math.BigDecimal;
import java.util.List;

public interface SweetService {

    Sweet addSweet(Sweet sweet);

    Sweet updateSweet(String sweetId, Sweet updatedSweet);

    List<Sweet> getAllSweets();

    List<Sweet> searchByName(String name);

    List<Sweet> searchByPriceRange(BigDecimal min, BigDecimal max);

    void deleteSweet(String sweetId);
}
