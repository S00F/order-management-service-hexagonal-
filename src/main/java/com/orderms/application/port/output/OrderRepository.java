package com.orderms.application.port.output;

import com.orderms.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(String orderId);
    List<Order> findAll();
    void deleteById(String orderId);
    boolean existsById(String orderId);
    List<Order> findByCustomerId(String customerId);
}