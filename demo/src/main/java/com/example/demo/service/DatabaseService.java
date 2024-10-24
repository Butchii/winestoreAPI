package com.example.demo.service;

import com.example.demo.api.model.Order;
import com.example.demo.api.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class DatabaseService {
    private final ArrayList<Product> productList;
    private final ArrayList<Order> orderList;
    private final String productDatabasePath;
    private final String orderDatabasePath;

    public DatabaseService() throws IOException, JSONException {
        productDatabasePath = "D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\database.json";
        orderDatabasePath = "D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\orderDatabase.json";

        productList = new ArrayList<>();
        loadProducts();

        orderList = new ArrayList<>();
        loadOrders();
    }

    private void loadProducts() throws IOException, JSONException {
        JSONArray productArray = getJsonArray(productDatabasePath);

        for (int i = 0; i < productArray.length(); i++) {
            JSONObject productObject = productArray.getJSONObject(i);
            String productName = productObject.getString("name");
            String productCapacity = productObject.getString("capacity");
            String productImageUrl = productObject.getString("imageUrl");
            String productStyle = productObject.getString("style");
            String productDiscount = productObject.getString("discount");
            String productType = productObject.getString("type");
            String productPrice = productObject.getString("price");
            String productYear = productObject.getString("year");
            String productCategory = productObject.getString("category");

            Product newProduct = new Product(productName, productCapacity, productImageUrl, productStyle, productDiscount, productType, productPrice, productYear, productCategory);
            productList.add(newProduct);
        }
    }

    public Order getOrderById(String id) throws IOException {
        JSONArray orderArray = getJsonArray(orderDatabasePath);
        Order order = null;
        for (int i = 0; i < orderArray.length(); i++){
            JSONObject orderObject = orderArray.getJSONObject(i);
            if(orderObject.get("orderID").equals(id)){
                String orderId = (String) orderObject.get("orderID");
                String orderAddress = (String) orderObject.get("shippingAddress");
                String orderShippedOn = (String) orderObject.get("shippedOn");
                String orderCreatedOn = (String) orderObject.get("createdOn");
                String orderCustomerName = (String) orderObject.get("customerName");
                String orderCustomerPhone = (String) orderObject.get("customerPhone");
                String orderCustomerMail = (String) orderObject.get("customerMail");
                JSONArray orderProducts = orderObject.getJSONArray("orderedProducts");
                ArrayList<Product> orderProductsList = new ArrayList<>();

                for (int j = 0; j < orderProducts.length(); j++) {
                    JSONObject product = orderProducts.getJSONObject(j);
                    String productYear = (String) product.get("year");
                    String productPrice = (String) product.get("price");
                    String productImageUrl = (String) product.get("imageUrl");
                    String productName = (String) product.get("name");
                    String productDiscount = (String) product.get("discount");
                    String productStyle = (String) product.get("style");
                    String productType = (String) product.get("type");
                    String productCategory = (String) product.get("category");
                    String productCapacity = (String) product.get("capacity");

                    Product newProduct = new Product(productName, productCapacity, productType, productStyle, productDiscount, productPrice, productYear, productImageUrl, productCategory);
                    orderProductsList.add(newProduct);
                }
                order = new Order(orderId, orderCreatedOn, orderShippedOn, orderAddress, orderCustomerName, orderCustomerPhone, orderCustomerMail, orderProductsList);
                break;
            }
        }
        return order;
    }

    public ArrayList<Product> loadProductsBySearchValue(String value) throws IOException {
        JSONArray productArray = getJsonArray(productDatabasePath);
        ArrayList<Product> customProductList = new ArrayList<>();
        for (int i = 0; i < productArray.length(); i++) {
            JSONObject productObject = productArray.getJSONObject(i);
            String productType = (String) productObject.get("type");
            String productName = (String) productObject.get("name");
            String productImageUrl = (String) productObject.get("imageUrl");
            String productCapacity = (String) productObject.get("capacity");
            String productStyle = (String) productObject.get("style");
            String productDiscount = (String) productObject.get("discount");
            String productCost = (String) productObject.get("price");
            String productYear = (String) productObject.get("year");
            String productCategory = (String) productObject.get("category");

            String capitalizedValue;
            if (value.length() > 1) {
                capitalizedValue = value.substring(0, 1).toUpperCase() + value.substring(1);
            } else {
                capitalizedValue = value.substring(0, 1).toUpperCase();
            }

            if (productName.contains(value) || productName.contains(capitalizedValue) || productType.contains(capitalizedValue) || productType.contains(value)) {
                Product newProduct = new Product(productName, productCapacity, productImageUrl, productStyle, productDiscount, productType, productCost, productYear, productCategory);
                customProductList.add(newProduct);
            }
        }
        return customProductList;
    }

    private static JSONArray getJsonArray(String filename) throws IOException, JSONException {
        File newFile = new File(filename);

        InputStream stream = new FileInputStream(newFile);

        BufferedReader bR = new BufferedReader(new InputStreamReader(stream));
        String line;
        StringBuilder responseStrBuilder = new StringBuilder();
        while ((line = bR.readLine()) != null) {
            responseStrBuilder.append(line);
        }

        stream.close();
        return new JSONArray(responseStrBuilder.toString());
    }

    public ArrayList<Product> getProducts() {
        return productList;
    }

    public ArrayList<Order> getOrders() {
        return orderList;
    }

    public void createOrder(Order newOrder) throws IOException, JSONException {
        File orderDatabase = new File(orderDatabasePath);
        JSONArray orderDatabaseArray = getJsonArray(orderDatabasePath);

        if (!orderDatabase.exists()) {
            orderDatabase.createNewFile();
            orderDatabaseArray = new JSONArray(orderDatabase);
        }

        ObjectMapper mapper = new ObjectMapper();
        String newOrderString = mapper.writeValueAsString(newOrder);
        try {
            JSONObject newOrderObject = new JSONObject(newOrderString);
            orderDatabaseArray.put(newOrderObject);
            FileWriter file = new FileWriter(orderDatabase);
            file.write(orderDatabaseArray.toString());
            file.flush();
            file.close();

            System.out.println("Successfully appended!");
        } catch (JSONException ignored) {
            System.out.println("Error!");
        }
        orderList.add(newOrder);
    }

    public void loadOrders() throws IOException, JSONException {
        JSONArray orderArray = getJsonArray(orderDatabasePath);

        for (int i = 0; i < orderArray.length(); i++) {
            JSONObject orderObject = orderArray.getJSONObject(i);
            String orderId = (String) orderObject.get("orderID");
            String orderAddress = (String) orderObject.get("shippingAddress");
            String orderShippedOn = (String) orderObject.get("shippedOn");
            String orderCreatedOn = (String) orderObject.get("createdOn");
            String orderCustomerName = (String) orderObject.get("customerName");
            String orderCustomerPhone = (String) orderObject.get("customerPhone");
            String orderCustomerMail = (String) orderObject.get("customerMail");

            JSONArray orderProducts = orderObject.getJSONArray("orderedProducts");
            ArrayList<Product> orderProductsList = new ArrayList<>();

            for (int j = 0; j < orderProducts.length(); j++) {
                JSONObject product = orderProducts.getJSONObject(j);
                String productYear = (String) product.get("year");
                String productPrice = (String) product.get("price");
                String productImageUrl = (String) product.get("imageUrl");
                String productName = (String) product.get("name");
                String productDiscount = (String) product.get("discount");
                String productStyle = (String) product.get("style");
                String productType = (String) product.get("type");
                String productCategory = (String) product.get("category");
                String productCapacity = (String) product.get("capacity");

                Product newProduct = new Product(productName, productCapacity, productType, productStyle, productDiscount, productPrice, productYear, productImageUrl, productCategory);
                orderProductsList.add(newProduct);
            }
            Order newOrder = new Order(orderId, orderCreatedOn, orderShippedOn, orderAddress, orderCustomerName, orderCustomerPhone, orderCustomerMail, orderProductsList);
            orderList.add(newOrder);
        }
    }
}