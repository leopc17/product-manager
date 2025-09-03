package com.br.productmanager.application.service;

import com.br.productmanager.domain.model.Product;
import com.br.productmanager.domain.model.enums.ProductCategory;
import com.br.productmanager.infraestructure.exception.ProductAlreadyExistsException;
import com.br.productmanager.infraestructure.exception.ProductNotFoundException;
import com.br.productmanager.infraestructure.entity.ProductEntity;
import com.br.productmanager.domain.port.output.ProductRepositoryPort;
import com.br.productmanager.domain.port.input.ProductServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductServicePort {

    private final ProductRepositoryPort repository;

    @Autowired
    public ProductServiceImpl(ProductRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductEntity> findAllByCategory(ProductCategory category) {
        Optional<List<Product>> productsModel = repository.findAllByCategory(category);

        if (productsModel.isEmpty()) {
            throw new ProductNotFoundException("Products not found");
        }

        List<ProductEntity> productsEntity = new ArrayList<>();
        for (Product p : productsModel.get()) {
            productsEntity.add(this.toEntity(p));
        }

        return productsEntity;
    }

    @Override
    public List<ProductEntity> findAll() {
        Optional<List<Product>> productsModel = repository.findAll();

        if (productsModel.isEmpty()) {
            throw new ProductNotFoundException("Products not found");
        }

        List<ProductEntity> productsEntity = new ArrayList<>();
        for (Product p : productsModel.get()) {
            productsEntity.add(this.toEntity(p));
        }

        return productsEntity;
    }

    @Override
    public ProductEntity findById(UUID id) {
        Optional<Product> productModel = repository.findById(id);

        if (productModel.isEmpty()) {
            throw new ProductNotFoundException("id not found");
        }

        return toEntity(productModel.get());
    }

    @Override
    public ProductEntity create(ProductEntity product) {
        if (repository.existsProductByName(product.getName())) {
            throw new ProductAlreadyExistsException("product with name " + product.getName() + " already exists");
        }

        Optional<Product> productModel = repository.save(toDomain(product));

        if (productModel.isEmpty()) {
            throw new ProductNotFoundException("id not found");
        }

        return toEntity(productModel.get());
    }

    @Override
    public ProductEntity update(UUID id, ProductEntity newProduct) {
        if (repository.existsProductByName(newProduct.getName())) {
            throw new ProductAlreadyExistsException("product with " + newProduct.getName() + " already exists");
        }

        Optional<Product> productModel = repository.findById(id);

        if (productModel.isEmpty()) {
            throw new ProductNotFoundException("id not found");
        }

        ProductEntity product = toEntity(productModel.get());
        BeanUtils.copyProperties(newProduct, product);

        productModel = repository.save(toDomain(product));

        if (productModel.isEmpty()) {
            throw new ProductNotFoundException("id not found");
        }

        return toEntity(productModel.get());
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("id not found");
        }

        repository.deleteById(id);
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getCategory()
        );
    }

    private ProductEntity toEntity(Product domain) {
        return new ProductEntity(
                domain.getId(),
                domain.getName(),
                domain.getPrice(),
                domain.getDescription(),
                domain.getCategory()
        );
    }
}
