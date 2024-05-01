package com.example.demo.api.controller;

import com.example.demo.api.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){this.orderService = orderService;}

    @PostMapping(value="/createOrder")
    @ResponseBody
    public void createOrder(@RequestBody Order newOrder) throws IOException {
        orderService.createOrder(newOrder);
    }
}
