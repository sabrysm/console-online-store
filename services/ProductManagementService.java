package oop.project.onlineshop.services;

import oop.project.onlineshop.entities.Product;

public interface ProductManagementService {

    Product[] getProducts();

    Product getProductById(int productIdToAddToCart);

}
