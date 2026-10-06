package com.vamsi.ecommerce.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vamsi.ecommerce.model.Product;

public class ProductRepository {
    private Map<Integer, Product> products;
    private int nextId = 1;

    public ProductRepository() {
        this.products = new HashMap<>();
    }

    public int nextId() {
        return nextId++;
    }

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Optional<Product> findById(int id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
