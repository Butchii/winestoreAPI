package com.example.demo.api.model;

public class Product {
    private String name;
    private String capacity;
    private String type;
    private String style;
    private String discount;
    private String price;
    private String year;
    private String imageUrl;
    private String category;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String cost) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public void setCategory(String category) {
        this.category = category;
    }

}
