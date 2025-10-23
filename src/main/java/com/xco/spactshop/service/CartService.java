package com.xco.spactshop.service;

import com.xco.spactshop.model.Cart;
import com.xco.spactshop.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<Cart> findByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
