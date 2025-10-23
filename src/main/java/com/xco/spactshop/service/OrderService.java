package com.xco.spactshop.service;

import com.xco.spactshop.model.Order;
import com.xco.spactshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findByUserId(String userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
