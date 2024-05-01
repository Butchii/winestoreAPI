package com.example.demo.service;

import com.example.demo.api.model.Order;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class OrderService {

    public void createOrder(Order newOrder) throws IOException, JSONException{
        File orderDatabase = new File("D:\\Wichtig\\IntellijWorkspace\\api\\demo\\src\\main\\resources\\orderDatabase.json");
        if(!orderDatabase.exists()){
            orderDatabase.createNewFile();
        }
        JSONObject newOrderObject = new JSONObject();
    }
}
