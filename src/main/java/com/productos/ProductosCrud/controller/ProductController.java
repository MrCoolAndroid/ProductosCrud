package com.productos.ProductosCrud.controller;

import com.productos.ProductosCrud.model.Product;
import com.productos.ProductosCrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/products", "/api/products/"})
public class ProductController {
    private final ProductService productService;

    // Obtener todos los Productos
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    // Obtener un Producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo Producto
    @PostMapping
    public ResponseEntity<Optional<Product>> createProduct(@RequestBody Product product) {
        try {
            Optional<Product> newProduct = productService.createProduct(product);

            if (newProduct.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar un Producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Elimina un Producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
