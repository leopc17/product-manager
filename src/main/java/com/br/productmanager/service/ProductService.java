package com.br.productmanager.service;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService extends CrudService<Product, UUID> {
    List<Product> findAllByCategory(ProductCategory category);
}
