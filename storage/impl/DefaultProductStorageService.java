package oop.project.onlineshop.storage.impl;

import oop.project.onlineshop.entities.Product;
import oop.project.onlineshop.entities.impl.DefaultProduct;
import oop.project.onlineshop.storage.ProductStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultProductStorageService implements ProductStorageService {
    private static final String RESOURCES_FOLDER = "resources";
    private static final String PRODUCTS_INFO = "products.csv";
    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int CATEGORY_INDEX = 2;
    private static final int PRICE_INDEX = 3;
    private static final String DELIMITER = ",";

    static {
        try {
            createStorageIfNotExists();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createStorageIfNotExists() throws IOException {
        Path path = Path.of(RESOURCES_FOLDER, PRODUCTS_INFO);
        if (!Files.exists(path))
            Files.createFile(path);
    }

    @Override
    public List<Product> loadProducts() {
        try (var stream = Files.lines(Path.of(RESOURCES_FOLDER, PRODUCTS_INFO))) {
            return stream
                    .filter(Objects::nonNull)
                    .filter(line -> !line.isEmpty())
                    .map(line -> {
                        String[] values = line.split(DELIMITER);
                        return new DefaultProduct(
                                Integer.parseInt(values[ID_INDEX]),
                                values[NAME_INDEX],
                                values[CATEGORY_INDEX],
                                Double.parseDouble(values[PRICE_INDEX]));
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
