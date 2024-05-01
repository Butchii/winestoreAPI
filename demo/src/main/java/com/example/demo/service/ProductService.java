package com.example.demo.service;

import com.example.demo.api.model.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class ProductService {

    private final ArrayList<Product> productList;

    public ProductService() throws IOException, JSONException {
        productList = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() throws IOException, JSONException {
        JSONArray productArray = getJsonArray();

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

        JSONArray productArray = getJsonArray();
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

    private static JSONArray getJsonArray() throws IOException, JSONException {
        File newFile = new File("D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\database.json");

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
}
