package com.abhi.paymentservice.payment_service.dto;

import java.util.List;

public class PaymentRequest {
    private List<ProductDTO> products;

    public PaymentRequest(List<ProductDTO> products) {
        this.products = products;
    }

    public PaymentRequest() {
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
