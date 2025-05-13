package com.ndiaye.product.controller;

import com.ndiaye.product.dto.ProductRequest;
import com.ndiaye.product.entity.Category;
import com.ndiaye.product.entity.Product;
import com.ndiaye.product.repository.CategoryRepository;
import com.ndiaye.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductService service, CategoryRepository categoryRepository) {
        this.service = service;
        this.categoryRepository = categoryRepository;
    }

    // ✔️ GET all products
    @GetMapping
    public List<Product> all() {
        return service.findAll();
    }

    // ✔️ GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✔️ POST create product
    @PostMapping
    public Product save(@Valid @RequestBody ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        product.setCategory(category);

        return service.save(product);
    }

    // ✔️ PUT update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return service.findById(id).map(existing -> {
            existing.setName(request.getName());
            existing.setPrice(request.getPrice());

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

            existing.setCategory(category);

            Product updated = service.save(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // ✔️ DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.findById(id).map(product -> {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
