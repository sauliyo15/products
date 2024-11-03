package com.sauliyo15.productstorage.service;

import com.sauliyo15.productstorage.model.Product;
import com.sauliyo15.productstorage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Async
    public CompletableFuture<List<Product>> getAllProducts() {
        long starTime = System.currentTimeMillis();
        List<Product> productList = productRepository.findAll();
        long endTime = System.currentTimeMillis();
        return CompletableFuture.completedFuture(productList);
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}

