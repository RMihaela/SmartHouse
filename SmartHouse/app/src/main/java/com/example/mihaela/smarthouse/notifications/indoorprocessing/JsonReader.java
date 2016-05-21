/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.notifications.indoorprocessing;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.FileReader;


public class JsonReader {

    public JSONParser parser = new JSONParser();
    public String id;
    public String code;
        

    public void read(String path) {
        try {
            
            Object obj = parser.parse(new FileReader(path));
 
            JSONObject jsonObject = (JSONObject) obj;
 
            id = (String) jsonObject.get("id");
            code = (String) jsonObject.get("code");
 
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
