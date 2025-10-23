package com.xco.spactshop.controller;

import com.xco.spactshop.model.Cart;
import com.xco.spactshop.service.CartService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam String userId) {
        return cartService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    cart.setItems(new ArrayList<>());
                    return ResponseEntity.ok(cartService.save(cart));
                });
    }

    @PostMapping("/items")
    public ResponseEntity<Cart> addCartItem(@RequestBody AddCartItemRequest request) {
        Cart cart = cartService.findByUserId(request.getUserId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(request.getUserId());
            newCart.setItems(new ArrayList<>());
            return newCart;
        });

        cart.getItems().stream()
                .filter(item -> item.getProductId().equals(request.getProductId()))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + request.getQuantity()),
                        () -> {
                            Cart.CartItem newItem = new Cart.CartItem();
                            newItem.setProductId(request.getProductId());
                            newItem.setQuantity(request.getQuantity());
                            cart.getItems().add(newItem);
                        }
                );

        return ResponseEntity.ok(cartService.save(cart));
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Cart> removeCartItem(@RequestParam String userId, @PathVariable String productId) {
        return cartService.findByUserId(userId)
                .map(cart -> {
                    cart.getItems().removeIf(item -> item.getProductId().equals(productId));
                    return ResponseEntity.ok(cartService.save(cart));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Data
    static class AddCartItemRequest {
        private String userId;
        private String productId;
        private int quantity;
    }
}
