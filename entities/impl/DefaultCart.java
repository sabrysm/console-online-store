package oop.project.onlineshop.entities.impl;

import oop.project.onlineshop.entities.Cart;
import oop.project.onlineshop.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultCart implements Cart {

    private List<Product> products;

    {
        products = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return products == null || products.isEmpty();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getProducts() {
        return products.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        products = new ArrayList<>();
    }
}
