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
public class ProductService {

    private final String orderDatabasePath = "D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\orderDatabase.json";
    private final ArrayList<Product> productList;

    public ProductService() throws IOException, JSONException {
        productList = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() throws IOException, JSONException {
        JSONArray productArray = getJsonArray("D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\database.json");

        for (int i = 0; i < productArray.length(); i++) {
            JSONObject productObject = productArray.getJSONObject(i);
            String productName = (String) productObject.get("name");
            String productCapacity = (String) productObject.get("capacity");
            String productStyle = (String) productObject.get("style");
            String productDiscount = (String) productObject.get("discount");
            String productType = (String) productObject.get("type");
            String productPrice = (String) productObject.get("price");
            String productYear = (String) productObject.get("year");

            Product newProduct = new Product(productName, productCapacity, productStyle, productDiscount, productType, productPrice, productYear);
            productList.add(newProduct);
        }
    }

    public ArrayList<Product> loadProductsBySearchValue(String value) throws IOException {

        JSONArray productArray = getJsonArray("D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\database.json");
        for (int i = 0; i < productArray.length(); i++) {
            JSONObject productObject = productArray.getJSONObject(i);
            String productType = (String) productObject.get("type");
            String productName = (String) productObject.get("name");
            String productCapacity = (String) productObject.get("capacity");
            String productStyle = (String) productObject.get("style");
            String productDiscount = (String) productObject.get("discount");
            String productCost = (String) productObject.get("price");
            String productYear = (String) productObject.get("year");
            String capitalizedValue = value.substring(0, 1).toUpperCase() + value.substring(1);
            if (productName.contains(value) || productName.contains(capitalizedValue) || productType.contains(capitalizedValue) || productType.contains(value)) {
                Product newProduct = new Product(productName, productCapacity, productStyle, productDiscount, productType, productCost, productYear);
                productList.add(newProduct);
            }
        }
        return productList;
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

    public void createOrder(Order newOrder) throws IOException, JSONException {
        JSONArray orderArray = getJsonArray(orderDatabasePath);

        PrintWriter pw = new PrintWriter(new FileOutputStream(orderDatabasePath));

        final ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(newOrder);
        JSONObject newOrderJSON = new JSONObject(json);
        orderArray.put(newOrderJSON);
        try {
            pw.write(orderArray.toString());
            pw.flush();
            pw.close();

            System.out.println("Successfully appended!");
        } catch (JSONException ignored) {
            System.out.println("Error!");
        }
    }
}