package com.example.demo.api.controller;

import com.example.demo.api.model.Order;
import com.example.demo.api.model.Product;
import com.example.demo.service.ProductService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public ArrayList<Product> getProduct() {
        return productService.getProducts();
    }

    @GetMapping("/product")
    @ResponseBody
    public ArrayList<Product> getProductsBySearchValue(@RequestParam String value) throws IOException {
        return productService.loadProductsBySearchValue(value);
    }


    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody Order newOrder) throws IOException, ParseException {
        productService.createOrder(newOrder);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}


