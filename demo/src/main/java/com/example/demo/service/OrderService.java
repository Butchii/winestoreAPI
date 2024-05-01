package com.example.demo.service;

import com.example.demo.api.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class OrderService {

    public void createOrder(Order newOrder) throws IOException, JSONException {
        File orderDatabase = new File("D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\orderDatabase.json");

        if (!orderDatabase.exists()) {
            orderDatabase.createNewFile();
        }
        JSONArray orderDatabaseArray = new JSONArray(orderDatabase);

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
    }
}
