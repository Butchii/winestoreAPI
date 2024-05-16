package com.example.demo.api.controller;

import com.example.demo.api.model.Order;
import com.example.demo.api.model.Product;
import com.example.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/orders")
    @ResponseBody
    public ArrayList<Order> getOrders(){
        return databaseService.getOrders();
    }
}


