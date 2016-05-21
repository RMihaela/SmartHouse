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
import com.example.mihaela.smarthouse.smart_unit.SmartWashingMachine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RGI3
 */
public class MasinaSpalatAlert implements Alert {

    @Override
    public void JsonAnalyser(JSONArray jsonArray, String TargetURL) {
        String id=new String();
        String nume=new String();
        String stare=new String();
        String temperatura=new String();
        String rotatii=new String();
        String program=new String();
        String message=new String();
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject x=jsonArray.getJSONObject(i);
                id=x.get("id").toString();
                nume=x.get("nume").toString();
                stare=x.get("stare").toString();
                temperatura=x.get("temperatura").toString();
                rotatii=x.get("rotatii").toString();
                program=x.get("program").toString();
                message=null;
                if(program=="matase"||program=="bumbac"||program=="lana"||program=="sintetic") {
                    if (Integer.parseInt(temperatura) > 60){
                        ASmartUnit smUnit = new SmartWashingMachine(id, nume);
                        smUnit.resetToDefault(id);
                        String alertMessage = String.format("The water temperature for the washing machine was set too high for the program '%s'. The values have been reset.", program);
                        NotificationsActivity.newNotifications.add(new NotificationsListItem("Alert", alertMessage));
                        AlertsManager.displayAlert(alertMessage);
                    }
                }
                else  if(program == "poliester"){
                    if(Integer.parseInt(temperatura)>80){
                        ASmartUnit smUnit = new SmartWashingMachine(id, nume);
                        smUnit.resetToDefault(id);
                        String alertMessage = "The water temperature for the washing machine was set too high for the program 'poliester'. The values have been reset.";
                        NotificationsActivity.newNotifications.add(new NotificationsListItem("Alert", alertMessage));
                        AlertsManager.displayAlert(alertMessage);
                    }
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
