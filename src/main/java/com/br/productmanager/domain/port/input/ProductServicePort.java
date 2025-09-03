package com.br.productmanager.domain.port.input;

import com.br.productmanager.domain.model.enums.ProductCategory;
import com.br.productmanager.infraestructure.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface ProductServicePort extends CrudService<ProductEntity, UUID> {
    List<ProductEntity> findAllByCategory(ProductCategory category);
}
