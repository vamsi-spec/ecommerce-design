package com.vamsi.ecommerce.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.vamsi.ecommerce.repository.ProductRepository;
import com.vamsi.ecommerce.util.Validator;
import com.vamsi.ecommerce.exception.NotFoundException;
import com.vamsi.ecommerce.model.Product;

public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(String name,String category,BigDecimal price,int stock) {
        int id = productRepository.nextId();
        Product product = new Product(id, name, category, price, stock);
        productRepository.save(product);
        return product;
    }

    private Product findOrThrow(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        return product.get();
    }

    public Product updateProduct(int id,String name,String category,BigDecimal price) {
        Product product = findOrThrow(id);
        
        product.changeDetails(name, category);
        product.changePrice(price);
        productRepository.save(product);
        return product;
    }

    public Product restock(int id,int quantity) {
        Product product = findOrThrow(id);

        product.increaseStock(quantity);
        productRepository.save(product);
        return product;
    }

    public void deactivateProduct(int id) {
        Product product = findOrThrow(id);

        product.deactivate();
        productRepository.save(product);
    }

    public Product getProduct(int id) {
        Product product = findOrThrow(id);

        return product;
    }

    public Product getActiveProduct(int id) {
        Product product = findOrThrow(id);
        if(!product.isActive()) {
            throw new NotFoundException("Product with id " + id + " is not active");
        }
        return product;
    }

    public List<Product> browse() {
        return productRepository.findAll().stream().filter(Product :: isActive).sorted(Comparator.comparing(Product :: getName)).toList();
    }

    public List<Product> searchByName(String keyword) {
        Validator.requireNonBlank(keyword, "keyword");

        return productRepository.findAll().stream().filter(Product :: isActive).filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())).toList();
    }

    public List<Product> filterByCategory(String category) {
        Validator.requireNonBlank(category, "category");

        String term = category.toLowerCase();

        return productRepository.findAll().stream().filter(Product :: isActive).filter(product -> product.getCategory().toLowerCase().equals(term)).toList();
    }
    
}
