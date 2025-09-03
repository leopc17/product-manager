package com.br.productmanager.infraestructure.adapter.repository;

import com.br.productmanager.domain.model.Product;
import com.br.productmanager.domain.model.enums.ProductCategory;
import com.br.productmanager.infraestructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    boolean existsProductByName(String name);

    List<ProductEntity> findAllByCategory(ProductCategory category);
}
