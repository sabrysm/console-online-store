package oop.project.onlineshop.services;

import oop.project.onlineshop.entities.Product;

import java.util.List;

public interface ProductManagementService {

    List<Product> getProducts();

    Product getProductById(int productIdToAddToCart);

}
