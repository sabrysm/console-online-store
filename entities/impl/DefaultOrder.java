package oop.project.onlineshop.entities.impl;

import oop.project.onlineshop.entities.Order;
import oop.project.onlineshop.entities.Product;

import java.util.Arrays;
import java.util.Objects;

public class DefaultOrder implements Order {

    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
    private static int countOrders;
    private int orderId;

    private String creditCardNumber;
    private Product[] products;
    private int customerId;
    
    @Override
    public boolean isCreditCardNumberValid(String creditCardNumber) {
        return creditCardNumber.matches("\\d+") && creditCardNumber.length() == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER; // No Space allowed
    }

    @Override
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    @Override
    public void setProducts(Product[] products) {
        this.products = Arrays.copyOf(products, products.length);
        this.orderId = ++countOrders;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public int getCustomerId() {
        return this.customerId;
    }
    
    @Override
    public String toString() {
        return "Order #" + orderId + "\n" +
                String.join("\n-", Arrays.stream(products).filter(Objects::nonNull).map(Product::getProductName).toList());
    }
}

