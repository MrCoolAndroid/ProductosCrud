package com.productos.ProductosCrud.controller;

import com.productos.ProductosCrud.model.Product;
import com.productos.ProductosCrud.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * Controlador para manejar las operaciones relacionadas con la gestión de productos.
 */
@Slf4j
@RestController
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos.")
@RequiredArgsConstructor
@RequestMapping("/api/products/")
public class ProductController {
    private final ProductService productService;

    /**
     * Obtiene una lista de todos los productos.
     *
     * @return Lista de productos.
     */
    @Operation(summary = "Obtener todos los productos.", description = "Retorna una lista de todos los productos registrados.")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
        })
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        log.info("Se obtuvo una lista de {} productos.", products.size());
        return ResponseEntity.ok(products);
    }


    /**
     * Obtiene un producto por su ID.
     *
     * @param id ID del producto a obtener.
     * @return Detalles del producto.
     */
    @Operation(summary = "Obtener un producto por su ID.", description = "Retorna un producto obtenido por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenido correctamente.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró el producto."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@Parameter(description = "ID del producto a obtener.", required = true, example = "1")
                                                        @PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            log.info("Se obtuvo el producto {}.", product.get().getName());
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Agrega un producto.
     *
     * @param product El producto a agregar.
     * @return El estado de la operacion.
     */
    @Operation(summary = "Agrega un producto.", description = "Retorna el estado de la operacion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado correctamente.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Optional<Product>> createProduct(@Parameter(description = "Datos del producto a crear.", required = true)
                                                           @RequestBody Product product) {
        try {
            Optional<Product> newProduct = productService.createProduct(product);

            if (newProduct.isPresent()) {
                log.info("Se agrego el producto {}.", newProduct.get().getName());
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


    /**
     * Modifica un producto.
     *
     * @param id El ID del producto a modificar.
     * @param product Los detalles producto a modificar.
     * @return El estado de la operacion.
     */
    @Operation(summary = "Modifica un producto.", description = "Retorna el estado de la operacion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto modificado correctamente.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> updateProduct(@Parameter(description = "ID del producto a modificar.", required = true, example = "1")
                                                           @PathVariable Long id,
                                                           @Parameter(description = "Detalles del producto a modificar.", required = true)
                                                           @RequestBody Product product) {
        Optional<Product> updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct.isPresent()) {
            log.info("Se modifico el producto {}.", updatedProduct.get().getName());
            return ResponseEntity.ok(updatedProduct);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Elimina un producto.
     *
     * @param id El ID del producto a eliminar.
     * @return El estado de la operacion.
     */
    @Operation(summary = "Elimina un producto.", description = "Retorna el estado de la operacion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "ID del producto a eliminar.", required = true, example = "1")
                                              @PathVariable Long id) {
        log.info("Se elimino el producto con ID {}.", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
