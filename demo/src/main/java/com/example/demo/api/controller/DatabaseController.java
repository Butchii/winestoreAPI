package com.example.demo.api.controller;

import com.example.demo.api.model.Order;
import com.example.demo.api.model.Product;
import com.example.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@RestController
public class DatabaseController {
    private final DatabaseService databaseService;

    @Autowired
    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping(value = "/products")
    public ArrayList<Product> getProducts() {
        return databaseService.getProducts();
    }

    @GetMapping("/product")
    @ResponseBody
    public ArrayList<Product> getProductsBySearchValue(@RequestParam String value) throws IOException {
        return databaseService.loadProductsBySearchValue(value);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/orders")
    @ResponseBody
    public ArrayList<Order> getOrders() {
        return databaseService.getOrders();
    }

    @PostMapping("/createOrder/{newOrder}")
    public void createOrder(@RequestBody Order newOrder) throws IOException {
        databaseService.createOrder(newOrder);
    }

    @GetMapping("/getProductImage")
    @ResponseBody
    public byte[] getProductImage(@RequestParam String imageURL) throws IOException {
        InputStream in = getClass().getResourceAsStream(imageURL);
        assert in != null;
        return org.apache.commons.io.IOUtils.toByteArray(in);
    }

    @GetMapping("/getOrderById")
    @ResponseBody
    public Order getOrderById(@RequestParam String id) throws IOException{
        return databaseService.getOrderById(id);
    }
}


