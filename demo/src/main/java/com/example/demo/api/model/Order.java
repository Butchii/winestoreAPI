package com.example.demo.api.model;

import java.util.ArrayList;

public class Order {
    private String orderID;
    private String createdOn;
    private String shippedOn;
    private String shippingAddress;
    private String customerName;
    private ArrayList<Product> orderedProducts;

    public Order(String orderID, String createdOn, String shippedOn, String shippingAddress, String customerName, ArrayList<Product> orderedProducts) {
        this.orderID = orderID;
        this.createdOn = createdOn;
        this.shippedOn = shippedOn;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.orderedProducts = orderedProducts;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getShippedOn() {
        return shippedOn;
    }

    public void setShippedOn(String shippedOn) {
        this.shippedOn = shippedOn;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(ArrayList<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
