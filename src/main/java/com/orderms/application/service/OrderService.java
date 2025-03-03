package com.orderms.application.service;

import com.orderms.application.port.input.OrderUseCase;
import com.orderms.application.port.output.OrderRepository;
import com.orderms.domain.model.Order;
import com.orderms.domain.model.OrderStatus;
import com.orderms.domain.exception.OrderNotFoundException;
import com.orderms.infrastructure.validator.OrderValidator;
import com.orderms.infrastructure.validator.ValidationResult;
import com.orderms.infrastructure.validator.OrderValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService implements OrderUseCase {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    @Override
    public Order createOrder(Order order) {
        ValidationResult validationResult = orderValidator.validate(order);
        if (!validationResult.isValid()) {
            throw new OrderValidationException(validationResult.getErrors());
        }

        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(calculateOrderTotal(order));
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrder(String orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Order order) {
        ValidationResult validationResult = orderValidator.validate(order);
        if (!validationResult.isValid()) {
            throw new OrderValidationException(validationResult.getErrors());
        }

        if (!orderRepository.existsById(order.getOrderId())) {
            throw new OrderNotFoundException("Order not found with id: " + order.getOrderId());
        }

        order.setUpdatedAt(LocalDateTime.now());
        order.setTotalAmount(calculateOrderTotal(order));
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Optional<Order> processOrder(String orderId) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    ValidationResult validationResult = orderValidator.validate(order);
                    if (validationResult.isValid()) {
                        order.setStatus(OrderStatus.CONFIRMED);
                        order.setUpdatedAt(LocalDateTime.now());
                        return orderRepository.save(order);
                    }
                    throw new OrderValidationException(validationResult.getErrors());
                });
    }

    @Override
    public boolean validateOrder(Order order) {
        ValidationResult validationResult = orderValidator.validate(order);
        return validationResult.isValid();
    }

    @Override
    public BigDecimal calculateOrderTotal(Order order) {
        return order.getItems().stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}