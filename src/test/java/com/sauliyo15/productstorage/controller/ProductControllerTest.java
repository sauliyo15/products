package com.sauliyo15.productstorage.controller;

import com.sauliyo15.productstorage.model.Product;
import com.sauliyo15.productstorage.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts_test() throws Exception {

        // Given
        Product product1 = new Product(1, "Product A", "description A", 10.99);
        Product product2 = new Product(2, "Product B", "description B", 15.99);
        List<Product> productList = List.of(product1, product2);

        // When
        when(productService.getAllProducts()).thenReturn(productList);

        // Then
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product A"))
                .andExpect(jsonPath("$[0].description").value("description A"))
                .andExpect(jsonPath("$[0].price").value(10.99))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product B"))
                .andExpect(jsonPath("$[1].description").value("description B"))
                .andExpect(jsonPath("$[1].price").value(15.99));
    }

    @Test
    void getProductById_found() throws Exception {
        // Given
        Product product = new Product(1, "Product A", "description A", 10.99);
        when(productService.getProductById(1)).thenReturn(Optional.of(product));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product A"))
                .andExpect(jsonPath("$.description").value("description A"))
                .andExpect(jsonPath("$.price").value(10.99));
    }

    @Test
    void getProductById_notFound() throws Exception {
        // Given
        when(productService.getProductById(99)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/products/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
