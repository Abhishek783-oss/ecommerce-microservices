package com.abhi.orderservice.order_service.controller;

import com.abhi.orderservice.order_service.model.Order;
import com.abhi.orderservice.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.placeOrder(order));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
    @PutMapping("/cancel/{userId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long userId){
        orderService.cancelOrder(userId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<String> checkoutCart(@PathVariable Long userId)
    {
        boolean success = orderService.checkoutCart(userId);
        return success?ResponseEntity.ok("Checkout Successful"):ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Checkout failed.");
    }

}
