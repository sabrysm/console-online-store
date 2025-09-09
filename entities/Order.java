package oop.project.onlineshop.entities;

public interface Order {
    String SUCCESS_MESSAGE = "Thanks a lot for your purchase. Details about order delivery are sent to your email.";

    boolean isCreditCardNumberValid(String userInput);

    void setCreditCardNumber(String userInput);

    void setProducts(Product[] products);

    void setCustomerId(int customerId);

    int getCustomerId();

}
