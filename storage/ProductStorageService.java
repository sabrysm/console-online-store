package oop.project.onlineshop.storage;

import oop.project.onlineshop.entities.Product;

import java.util.List;

public interface ProductStorageService {
    List<Product> loadProducts();
}
