package com.br.productmanager.repository;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategory(ProductCategory category);

    boolean existsProductByName(String name);
}
