package org.example.productcatalogservice.services;

import org.example.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductDetailsById(Long id);

    Product replaceProduct(Long id, Product inputProduct);

    List<Product> getAllProducts();
}
