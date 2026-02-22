package oop.project.onlineshop.storage;

import oop.project.onlineshop.entities.Order;

import java.util.List;

public interface OrderStorageService {

    void saveOrders(List<Order> orders);

    List<Order> loadOrders();
}
