package com.codic.ecommerceproject.service;

import com.codic.ecommerceproject.dto.Purchase;
import com.codic.ecommerceproject.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
