package com.abhi.orderservice.order_service.service;

import com.abhi.orderservice.order_service.dto.CartItem;
import com.abhi.orderservice.order_service.model.Order;
import com.abhi.orderservice.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    public boolean checkoutCart(Long userId){
        try{
            String cartServiceUrl = "http://localhost:8083/api/cart/"+userId;
            ResponseEntity<CartItem[]> response =restTemplate.getForEntity(cartServiceUrl, CartItem[].class);
            if(response.getStatusCode().is2xxSuccessful()&&response.getBody()!=null) {
                CartItem[] cartItems = response.getBody();
                for (CartItem item : cartItems) {
                    Order order = new Order(); // no-arg constructor

                    order.setUserId(item.getUserId());
                    order.setProductId(item.getProductId());
                    order.setQuantity(item.getQuantity());
                    order.setPrice(item.getPrice());
                    order.setStatus("PENDING");
                    order.setOrderDate(LocalDateTime.now());

                    orderRepository.save(order);
                }
                String deleteCartUrl="http://localhost:8083/api/cart/"+userId;
                restTemplate.delete(deleteCartUrl);
                return true;
            }
                return false;
            }
            catch(Exception e){
                System.out.println("Error while checking out cart: "+e.getMessage());
                return false;
            }

    }
    public Order placeOrder(Order order){
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }
    public List<Order> getOrdersByUser(Long userId){
        return orderRepository.findByUserId(userId);
    }
    public void cancelOrder(Long orderId){
        orderRepository.findById(orderId).ifPresent(order->{
            order.setStatus("CANCELLED");
            orderRepository.save(order);
        });
    }
}
