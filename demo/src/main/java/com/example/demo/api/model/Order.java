package com.example.demo.api.model;

import java.util.ArrayList;

public class Order {
    private final String orderID;
    private final String createdOn;
    private String shippedOn;
    private final String shippingAddress;
    private final String customerName;
    private final String customerPhone;
    private final String customerMail;
    private final ArrayList<Product> orderedProducts;

    public Order(String orderID, String createdOn, String shippedOn, String shippingAddress, String customerName, String customerPhone, String customerMail, ArrayList<Product> orderedProducts) {
        this.orderID = orderID;
        this.createdOn = createdOn;
        this.shippedOn = shippedOn;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerMail = customerMail;
        this.shippingAddress = shippingAddress;
        this.orderedProducts = orderedProducts;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getShippedOn() {
        return shippedOn;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setShippedOn(String shippedOn) {
        this.shippedOn = shippedOn;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public String getOrderID() {
        return orderID;
    }
}
