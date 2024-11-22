package com.sauliyo15.productstorage.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer id) {
        super("Product not found with ID: " + id);
    }

}