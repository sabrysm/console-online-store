package oop.project.onlineshop.services.impl;

import oop.project.onlineshop.entities.Order;
import oop.project.onlineshop.services.OrderManagementService;

import java.util.Arrays;

public class DefaultOrderManagementService implements OrderManagementService {

    private static final int DEFAULT_ORDER_CAPACITY = 10;

    private static DefaultOrderManagementService instance;

    private Order[] orders;
    private int size;

    {
        orders = new Order[DEFAULT_ORDER_CAPACITY];
        size = 0;
    }

    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        if (order == null) return;
        if (orders.length == size) grow();
        orders[size++] = order;
    }

    private void grow() {
        orders = new Order[size + size >> 1];
        orders = Arrays.copyOf(orders, size);
    }

    @Override
    public Order[] getOrdersByUserId(int userId) {
        int userOrdersSize = 0;
        for (Order o : orders) {
            if (o.getCustomerId() == userId) userOrdersSize++;
        }
        Order[] userOrders = new Order[userOrdersSize];
        int nextOrderIndex = 0;
        for (Order o : orders) {
            if (o.getCustomerId() == userId) {
                    userOrders[nextOrderIndex++] = o;
            }
        }
        return userOrders;
    }

    @Override
    public Order[] getOrders() {
        return Arrays.copyOf(orders, size);
    }

    void clearServiceState() {
        size = 0;
        orders = new Order[DEFAULT_ORDER_CAPACITY];
        instance = null;
    }

}
