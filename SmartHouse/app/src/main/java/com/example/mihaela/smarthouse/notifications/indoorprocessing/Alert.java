/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.notifications.indoorprocessing;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author RGI3
 */
public interface Alert {

    public void JsonAnalyser(JSONArray jsonArray, String TargetURL);
    public void sendMessage(JSONObject response, String TargetURL);
    
}
