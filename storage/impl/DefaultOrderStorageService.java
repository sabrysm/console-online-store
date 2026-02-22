package oop.project.onlineshop.storage.impl;

import oop.project.onlineshop.entities.Order;
import oop.project.onlineshop.storage.OrderStorageService;

import java.io.*;
import java.util.List;

public class DefaultOrderStorageService implements OrderStorageService {
    private static final String RESOURCES_FOLDER = "resources";
    private static final String ORDERS_INFO = "orders.csv";
    private static DefaultOrderStorageService instance;

    public DefaultOrderStorageService() {

    }

    public static DefaultOrderStorageService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderStorageService();
        }
        return instance;
    }

    @Override
    public void saveOrders(List<Order> orders) {
        try (var oos = new ObjectOutputStream(new FileOutputStream(RESOURCES_FOLDER + File.separator + ORDERS_INFO))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> loadOrders() {
        try (var ois = new ObjectInputStream(new FileInputStream(RESOURCES_FOLDER + File.separator + ORDERS_INFO))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
