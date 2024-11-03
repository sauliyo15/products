package com.sauliyo15.productstorage.repository;

import com.sauliyo15.productstorage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}