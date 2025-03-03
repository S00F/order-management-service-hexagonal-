package com.orderms.application.port.input;

import com.orderms.domain.model.Order;
import com.orderms.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderUseCase {
    Order createOrder(Order order);
    Optional<Order> getOrder(String orderId);
    List<Order> getAllOrders();
    Order updateOrder(Order order);
    void deleteOrder(String orderId);
    Order updateOrderStatus(String orderId, OrderStatus status);
    List<Order> getOrdersByCustomerId(String customerId);
    Optional<Order> processOrder(String orderId);
    boolean validateOrder(Order order);
    BigDecimal calculateOrderTotal(Order order);
}