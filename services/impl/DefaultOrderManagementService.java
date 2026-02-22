package oop.project.onlineshop.services.impl;

import oop.project.onlineshop.entities.Order;
import oop.project.onlineshop.services.OrderManagementService;
import oop.project.onlineshop.storage.OrderStorageService;
import oop.project.onlineshop.storage.impl.DefaultOrderStorageService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultOrderManagementService implements OrderManagementService {

    private static DefaultOrderManagementService instance;
    private final OrderStorageService orderStorageService;

    private List<Order> orders;

    {
        orderStorageService = DefaultOrderStorageService.getInstance();
        orders = orderStorageService.loadOrders();
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
        orders.add(order);
        orderStorageService.saveOrders(orders);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orders.stream()
                .filter(Objects::nonNull)
                .filter(order -> order.getCustomerId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrders() {
        if (orders == null || orders.isEmpty()) {
            orders = orderStorageService.loadOrders();
        }
        return orders;
    }

}
