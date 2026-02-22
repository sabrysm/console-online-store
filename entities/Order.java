package oop.project.onlineshop.entities;

import java.io.Serializable;
import java.util.List;

public interface Order extends Serializable {
    String SUCCESS_MESSAGE = "Thanks a lot for your purchase. Details about order delivery are sent to your email.";

    boolean isCreditCardNumberValid(String userInput);

    void setCreditCardNumber(String userInput);

    void setProducts(List<Product> products);

    void setCustomerId(int customerId);

    int getCustomerId();

}
