package com.abhi.productservice.product_service.repository;

import com.abhi.productservice.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product,Long> {
}
