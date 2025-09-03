package com.br.productmanager.domain.port.output;

import com.br.productmanager.domain.model.Product;
import com.br.productmanager.domain.model.enums.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Optional<Product> save(Product product);
    Optional<List<Product>> findAll();
    Optional<List<Product>> findAllByCategory(ProductCategory category);
    Optional<Product> findById(UUID id);
    Optional<Product> update(UUID id, Product newProduct);
    void deleteById(UUID id);
    boolean existsProductByName(String name);
    boolean existsById(UUID id);
}
