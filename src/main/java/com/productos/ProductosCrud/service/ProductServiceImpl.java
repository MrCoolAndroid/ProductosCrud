package com.productos.ProductosCrud.service;

import com.productos.ProductosCrud.model.Product;
import com.productos.ProductosCrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> createProduct(Product product) {
        Product productToBeCreated = Product.builder()
                .reference(UUID.randomUUID().toString())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .isActive(true)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        return Optional.of(productRepository.save(productToBeCreated));
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> oldProduct = productRepository.findById(id);
        if (oldProduct.isPresent()) {
            Product productToUpdate = oldProduct.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStock(product.getStock());
            productToUpdate.setUpdatedAt(ZonedDateTime.now());
            return Optional.of(productRepository.save(productToUpdate));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
