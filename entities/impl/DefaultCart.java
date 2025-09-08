package oop.project.onlineshop.entities.impl;

import oop.project.onlineshop.entities.Cart;
import oop.project.onlineshop.entities.Product;

import java.util.Arrays;

public class DefaultCart implements Cart {

    private Product[] products;
    private int size;
    private static final int DEFAULT_CART_SIZE = 10;

    {
        products = new Product[DEFAULT_CART_SIZE];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addProduct(Product product) {
        if (size + 1 == products.length) grow();
        products[size++] = product;
    }

    @Override
    public Product[] getProducts() {
        int actualSize = 0;
        for (Product p : products) {
            if (p != null) actualSize++;
        }
        Product[] nonNullProducts = new Product[actualSize];
        int counter = 0;
        for (Product p : products) {
            if (p != null) {
                nonNullProducts[counter++] = p;
            }
        }

        return nonNullProducts;
    }

    @Override
    public void clear() {
        products = new Product[0];
        size = 0;
    }

    private void grow() {
        int newLength = size + size >> 1; // increase capacity by 50%
        products = Arrays.copyOf(products, newLength);
    }
}
