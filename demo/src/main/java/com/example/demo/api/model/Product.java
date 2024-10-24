package com.example.demo.api.model;

public class Product {
    private final String name;
    private final String capacity;
    private final String type;
    private final String style;
    private String discount;
    private String price;
    private final String year;
    private final String imageUrl;
    private final String category;

    public Product(String name, String capacity, String imageUrl, String style, String discount, String type, String price, String year, String category) {
        this.name = name;
        this.capacity = capacity;
        this.style = style;
        this.discount = discount;
        this.type = type;
        this.price = price;
        this.year = year;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public String getStyle() {
        return style;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }
}
