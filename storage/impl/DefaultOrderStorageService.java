package oop.project.onlineshop.storage.impl;

import oop.project.onlineshop.entities.Order;
import oop.project.onlineshop.storage.OrderStorageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderStorageService implements OrderStorageService {
    private static final String RESOURCES_FOLDER = "resources";
    private static final String ORDERS_INFO = "orders.csv";
    private static DefaultOrderStorageService instance;

    public DefaultOrderStorageService() {

    }

    static {
        try {
            createFileIfNotExist();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFileIfNotExist() throws IOException {
        Path path = Path.of(RESOURCES_FOLDER, ORDERS_INFO);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
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
        File file = new File(RESOURCES_FOLDER + File.separator + ORDERS_INFO);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try (var ois = new ObjectInputStream(new FileInputStream(RESOURCES_FOLDER + File.separator + ORDERS_INFO))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
