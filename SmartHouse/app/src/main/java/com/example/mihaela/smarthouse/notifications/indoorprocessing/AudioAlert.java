/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.notifications.indoorprocessing;

import com.example.mihaela.smarthouse.managers.AlertsManager;
import com.example.mihaela.smarthouse.notifications.NotificationsActivity;
import com.example.mihaela.smarthouse.notifications.NotificationsListItem;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;
import com.example.mihaela.smarthouse.smart_unit.SmartAudio;
import com.example.mihaela.smarthouse.smart_unit.SmartWaterSystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RGI3
 */
public class AudioAlert implements Alert{
    @Override
    public void JsonAnalyser(JSONArray jsonArray, String TargetURL) {
        String id=new String();
        String nume=new String();
        String stare=new String();
        String volum=new String();
        String melodie=new String();
        String message=new String();
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject x=jsonArray.getJSONObject(i);
                id=x.get("id").toString();
                nume=x.get("nume").toString();
                stare=x.get("stare").toString();
                volum=x.get("volum").toString();
                melodie=x.get("melodie").toString();
                message=null;
                if(Integer.parseInt(volum)>34){
                    ASmartUnit smUnit = new SmartAudio(id, nume);
                    smUnit.resetToDefault(id);
                    String alertMessage = "The audio system's volume was set too high. The values have been reset.";
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
