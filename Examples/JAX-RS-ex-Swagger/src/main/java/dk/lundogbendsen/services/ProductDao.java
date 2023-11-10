package dk.lundogbendsen.services;

import dk.lundogbendsen.rest.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;


import java.util.*;
import java.util.logging.Logger;

@Singleton
public class ProductDao {
    private List<Product> list = new ArrayList<Product>();
    Logger log = Logger.getLogger(ProductDao.class.getName());


    Integer nextId = 0;


    @PostConstruct
    public void init() {
        String[] productNames = {"keyboard", "printer", "mouse", "cpu", "monitor", "power supply", "laptop", "ram", "usb", "cable"};
        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            list.add(product);
            product.setId(nextId++);
            product.setName(productNames[(int) (Math.random() * productNames.length)]);
            product.setPrice((int) (Math.random() * 2000));
            product.setProfit(product.getPrice() * 0.1);
        }
        log.info("created products: " + list);
    }

    public void add(Product p) {
        if (p.getId() == null) {
            throw new IllegalArgumentException("Cannot add product with an existing id!");
        }
        p.setId(nextId++);
        list.add(p);
        log.info("added product: " + p);
    }

    public void remove(Product p) {
        list.remove(p);
        log.info("removed product: " + p);
    }

    public Optional<Product> findById(Integer id) {
        log.info("find product by id: " + id);
        for (Product p : list) {
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Product> getAll() {
        log.info("get all products: " + list.size());
        return Collections.unmodifiableList(list);
    }


}
