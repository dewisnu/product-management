package com.doku.product_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProductWithoutId {
    @NotEmpty(message = "The name must not be empty.")
    private String name;

    @Positive(message = "The price must be a positive number.")
    private BigDecimal price;

    @Positive(message = "The quantity must be a positive integer.")
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Positive(message = "The price must be a positive number.") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
