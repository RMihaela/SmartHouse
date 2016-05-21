/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.notifications.indoorprocessing;


import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.managers.AlertsManager;
import com.example.mihaela.smarthouse.notifications.NotificationsActivity;
import com.example.mihaela.smarthouse.notifications.NotificationsListItem;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;
import com.example.mihaela.smarthouse.smart_unit.SmartAC;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RGI3
 */
public class AerConditionatAlert implements Alert{
    @Override
    public void JsonAnalyser(JSONArray jsonArray, String TargetURL) {
        String id=new String();
        String nume=new String();
        String stare=new String();
        String temperatura=new String();
        String intensitate=new String();
        String message=new String();
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject x=jsonArray.getJSONObject(i);
                id=x.get("id").toString();
                nume=x.get("nume").toString();
                stare=x.get("stare").toString();
                temperatura=x.get("temperatura").toString();
                intensitate=x.get("intensitate").toString();
                message=null;
                if(Integer.parseInt(temperatura) < 15) {
                    switch (id) {
                        case "29293b":
                            nume = "kitchen";
                            break;
                        case "936378":
                            nume = "bedroom";
                            break;
                        case "e04e63":
                            nume = "living";
                            break;
                    }
                    ASmartUnit smUnit = new SmartAC(id, nume);
                    smUnit.resetToDefault(id);
                    String alertMessage = String.format("The temperature for the AC unit in the %s was set too low. The values have been reset.", nume);
                    NotificationsActivity.newNotifications.add(new NotificationsListItem("Alert", alertMessage));
                    AlertsManager.displayAlert(alertMessage);
                }

                if(message!=null){
                    JSONObject response= new JSONObject();
                    response.put("message", message);
                    sendMessage(response, TargetURL);
                }
            } catch (JSONException ex) {
                Logger.getLogger(MasinaSpalatAlert.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

 

    @Override
    public void sendMessage(JSONObject response, String TargetURL) {
        
    }
}
