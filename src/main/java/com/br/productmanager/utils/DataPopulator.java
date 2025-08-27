package com.br.productmanager.utils;

import com.br.productmanager.enums.ProductCategory;
import com.br.productmanager.model.entity.Product;
import com.br.productmanager.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataPopulator {

    private final ProductRepository productRepository;

    @Autowired
    public DataPopulator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        Product p1 = new Product(
                "Wireless Optical Mouse",
                BigDecimal.valueOf(24.99),
                "A compact wireless mouse with optical tracking and ergonomic design. " +
                        "Ideal for home or office use. Compatible with most laptops and desktops via USB receiver.",
                ProductCategory.ELECTRONICS
        );
        productRepository.save(p1);

        Product p2 = new Product(
                "Milk Chocolate Bar",
                BigDecimal.valueOf(2.49),
                "A smooth and creamy milk chocolate bar made with high-quality cocoa " +
                        "and fresh milk. Perfect as a snack or gift.",
                ProductCategory.FOOD
        );
        productRepository.save(p2);

        Product p3 = new Product(
                "Genuine Leather Belt",
                BigDecimal.valueOf(22.00),
                "Classic men's leather belt made from genuine cowhide. Durable, stylish, " +
                        "and adjustable. Suitable for both casual and formal wear.",
                ProductCategory.CLOTHING
        );
        productRepository.save(p3);

        Product p4 = new Product(
                "Herbal Shampoo Bottle",
                BigDecimal.valueOf(9.99),
                "Refreshing herbal shampoo formulated with natural extracts to " +
                        "nourish hair and scalp. Suitable for all hair types. Comes in a 250ml bottle.",
                ProductCategory.COSMETICS
        );
        productRepository.save(p4);
    }
}
