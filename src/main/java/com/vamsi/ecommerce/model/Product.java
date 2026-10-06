package com.vamsi.ecommerce.model;

import java.math.BigDecimal;

import com.vamsi.ecommerce.exception.InsufficientStockException;
import com.vamsi.ecommerce.util.Validator;

public class Product {
    private final int id;
    private String name;
    private String category;
    private BigDecimal price;
    private int stock;
    private boolean active;

    public Product(int id, String name, String category, BigDecimal price, int stock) {
        this.id = Validator.requirePositive(id, "id");
        this.name = Validator.requireNonBlank(name, "name");
        this.category = Validator.requireNonBlank(category, "category");
        this.price = Validator.requirePositive(price, "price");
        this.stock = Validator.requireNonNegative(stock, "stock");
        this.active = true;
    }

    public void decreaseStock(int quantity) {
        Validator.requirePositive(quantity, "quantity");

        if (this.stock < quantity) {
            throw new InsufficientStockException("Not enough stock for " + name + ". Requested: " + quantity + ", Available: " + this.stock);
        }
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        Validator.requirePositive(quantity, "quantity");

        this.stock += quantity;
    }

    public void changePrice(BigDecimal newPrice) {
        Validator.requirePositive(newPrice, "newPrice");
        this.price = newPrice;
    }

    public void deactivate() {
        this.active = false;
    }

    public void changeDetails(String name, String category) {
        this.name = Validator.requireNonBlank(name, "name");
        this.category = Validator.requireNonBlank(category, "category");
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public boolean isActive() {
        return this.active;
    }
}
