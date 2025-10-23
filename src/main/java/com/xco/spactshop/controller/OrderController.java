package com.xco.spactshop.controller;

import com.xco.spactshop.model.Cart;
import com.xco.spactshop.model.Order;
import com.xco.spactshop.model.Product;
import com.xco.spactshop.service.CartService;
import com.xco.spactshop.service.OrderService;
import com.xco.spactshop.service.ProductService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CartService cartService, ProductService productService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        Cart cart = cartService.findByUserId(request.getUserId()).orElse(null);
        if (cart == null || cart.getItems().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setDate(Instant.now());
        order.setStatus("EM_PROCESSAMENTO");

        List<Order.OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    Product product = productService.findById(cartItem.getProductId()).orElse(null);
                    if (product == null) {
                        throw new IllegalStateException("Product not found");
                    }
                    Order.OrderItem orderItem = new Order.OrderItem();
                    orderItem.setProductId(cartItem.getProductId());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setUnitPrice(product.getPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setItems(orderItems);

        BigDecimal totalValue = orderItems.stream()
                .map(item -> item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalValue(totalValue);

        return ResponseEntity.ok(orderService.save(order));
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam String userId) {
        return orderService.findByUserId(userId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id, @RequestBody UpdateOrderStatusRequest request) {
        return orderService.findById(id)
                .map(order -> {
                    order.setStatus(request.getStatus());
                    return ResponseEntity.ok(orderService.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Data
    static class CreateOrderRequest {
        private String userId;
    }

    @Data
    static class UpdateOrderStatusRequest {
        private String status;
    }
}
