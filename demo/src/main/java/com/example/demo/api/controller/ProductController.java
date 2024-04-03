package com.example.demo.api.controller;

import com.example.demo.api.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public ArrayList<Product> getProduct(){
        return productService.getProducts();
    }

    @GetMapping("/product")
    public ArrayList<Product> getProductsBySearchValue(@RequestParam String value) throws IOException {
        return productService.getProductsBySearchValue(value);
    }

    @GetMapping("/get-text")
    public String getText(){
        return "hello-woprld";
    }
}


