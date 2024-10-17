package com.productos.ProductosCrud.service;

import com.productos.ProductosCrud.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> getProductById(Long id);
    Optional<Product> createProduct(Product product);
    Optional<Product> updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
