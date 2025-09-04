package oop.project.onlineshop.services;

import oop.project.onlineshop.entities.Order;

public interface OrderManagementService {

    void addOrder(Order order);

    Order[] getOrdersByUserId(int userId);

    Order[] getOrders();

}
