package com.sweetshop.service;

import com.sweetshop.model.Sweet;

public interface InventoryService {

    Sweet purchaseSweet(String sweetId, int quantity);

    Sweet restockSweet(String sweetId, int quantity);
}