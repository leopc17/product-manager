package com.br.productmanager.infraestructure.adapter.repository;

import com.br.productmanager.domain.model.Product;
import com.br.productmanager.domain.model.enums.ProductCategory;
import com.br.productmanager.domain.port.output.ProductRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Product> save(Product product) {
        return null;
    }

    @Override
    public Optional<List<Product>> findAll() {
        return null;
    }

    @Override
    public Optional<List<Product>> findAllByCategory(ProductCategory category) {
        return null;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return null;
    }

    @Override
    public Optional<Product> update(UUID id, Product newProduct) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public boolean existsProductByName(String name) {
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }
}
