package com.abhi.cartservice.cart_service.controller;

import com.abhi.cartservice.cart_service.model.Cart;
import com.abhi.cartservice.cart_service.repository.CartRepository;
import com.abhi.cartservice.cart_service.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart){
        return ResponseEntity.ok(cartService.addToCart(cart));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> getCartByUser(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getCardByuserId(userId));
    }
    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long userId, @PathVariable Long productId){
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<String> clearCart(@PathVariable Long userId){
        cartService.deleteFromCart(userId);
        return ResponseEntity.ok("Cart deleted successfully for user: "+userId);
    }
}
