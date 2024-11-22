package com.sauliyo15.productstorage.service;

import com.sauliyo15.productstorage.model.Product;
import com.sauliyo15.productstorage.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @Test
    void getProducts_test() {

        //Given
        Product product1 = new Product();
        Product product2 = new Product();

        List<Product> productList = List.of(product1, product2);

        //When
        when(productRepository.findAll()).thenReturn(productList);

        //Then
        List<Product> result = productService.getAllProducts();
        assertEquals(productList, result);
    }

    @Test
    void getProductById_test() throws Exception {

        //Given
        int productId = 1;

        Product product = new Product();

        //When
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        //Then
        Optional<Product> result = productService.getProductById(productId);
        assertEquals(result.get(), product);
    }

}
