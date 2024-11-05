package com.example.microserviceProduct.Controller;

import com.example.microserviceProduct.configurations.ApplicationPropertiesConfiguration;
import com.example.microserviceProduct.dao.ProductDao;
import com.example.microserviceProduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController implements HealthIndicator {
    @Autowired
    ApplicationPropertiesConfiguration appProperties;
    @Autowired
    private ProductDao productDao;

    @GetMapping(value = "/Produits")
    public List<Product> getProducts() {
        System.out.println("********** ProductController listeDesProduits()");
        List<Product> products = productDao.findAll();
        if (products.isEmpty()) {
            throw new RuntimeException("Aucun Produit n'est disponible a la liste!");
        }
        return products;
    }


    @Override
    public Health health() {
        return null;
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }
}
