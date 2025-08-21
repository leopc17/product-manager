package com.br.productmanager.service.impl;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.exception.ProductAlreadyExistsException;
import com.br.productmanager.exception.ProductNotFoundException;
import com.br.productmanager.model.entity.Product;
import com.br.productmanager.repository.ProductRepository;
import com.br.productmanager.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAllByCategory(ProductCategory category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(UUID id) {
        Optional<Product> productOptional = repository.findById(id);

        if (productOptional.isEmpty()) {
            throw new NoSuchElementException("id not found.");
        }

        return productOptional.get();
    }

    @Override
    public Product create(Product product) {
        if (repository.existsProductByName(product.getName())) {
            throw new ProductAlreadyExistsException("product with name " + product.getName() + " already exists");
        }

        return repository.save(product);
    }

    @Override
    public Product update(UUID id, Product newProduct) {
        if (repository.existsProductByName(newProduct.getName())) {
            throw new ProductAlreadyExistsException("product with " + newProduct.getName() + " already exists");
        }

        Optional<Product> productOptional = repository.findById(id);

        if (productOptional.isEmpty()) {
            throw new NoSuchElementException("id not found.");
        }

        Product product = productOptional.get();
        BeanUtils.copyProperties(newProduct, product);

        return repository.save(product);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
