package com.codic.ecommerceproject.dao;

import com.codic.ecommerceproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer ,Long> {

}
