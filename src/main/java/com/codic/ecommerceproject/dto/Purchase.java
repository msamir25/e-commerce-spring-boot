package com.codic.ecommerceproject.dto;

import com.codic.ecommerceproject.entity.Address;
import com.codic.ecommerceproject.entity.Customer;
import com.codic.ecommerceproject.entity.Order;
import com.codic.ecommerceproject.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;


}
