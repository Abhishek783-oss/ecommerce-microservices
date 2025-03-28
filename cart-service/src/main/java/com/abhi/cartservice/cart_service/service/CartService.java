package com.abhi.cartservice.cart_service.service;

import com.abhi.cartservice.cart_service.model.Cart;
import com.abhi.cartservice.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public Cart addToCart(Cart cart){
        return cartRepository.save(cart);
    }
    public List<Cart> getCardByuserId(Long userId){
        return cartRepository.findByUserId(userId);
    }
    public void removeFromCart(Long userId, Long productId){
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public void deleteFromCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
