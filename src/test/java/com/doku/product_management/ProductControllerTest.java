package com.doku.product_management;

import com.doku.product_management.entity.Product;
import com.doku.product_management.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldAddProduct() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Product\", \"price\":100.0, \"quantity\":10}"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Product product = new Product();
        product.setName("Delete Product");
        product.setPrice(new BigDecimal(50));
        product.setQuantity(5);
        product = productRepository.save(product);

        mockMvc.perform(delete("/products/" + product.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
