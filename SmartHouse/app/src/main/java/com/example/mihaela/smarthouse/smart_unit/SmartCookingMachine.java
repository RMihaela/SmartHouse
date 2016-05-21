package com.example.mihaela.smarthouse.smart_unit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.editor_activities.CookingMachineEditor;
import com.example.mihaela.smarthouse.managers.WebServiceManager;
import com.example.mihaela.smarthouse.stats.StatsActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartCookingMachine extends ASmartUnit {
    private int temperature = 180;
    private final Context context;

    public SmartCookingMachine(String id, String name, Context context) {
        super(id, name);
        this.context = context;
        this.initialise();
    }

    public SmartCookingMachine (String id, String name){
        super(id, name);
        this.context = null;
    }

    @Override
    public void updateServerData(Boolean status) {

    }

    @Override
    public void openEditorActivity() {
        CookingMachineEditor.setSmartUnit(this);
        Intent intent = new Intent(context, CookingMachineEditor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void initialise() {
        String url = ASmartUnit.urlstub + "c_aragaz/" + this.getId();
        WebServiceManager.getInstance(context).startGETRequest(url, this, "parseServerData");
    }

    public void updateServerData(Integer temperature, Boolean status) {
        this.setTemperature(temperature);
        this.setStatus(status);
        Integer st = status ? 1 : 0;
        JSONObject obj = new JSONObject();
        try {
            obj.put("temperatura", temperature);
            obj.put("stare", st);
            String url = ASmartUnit.urlstub + "c_aragaz/" + this.getId();
            WebServiceManager.getInstance(context).startPUTRequest(url, obj, this, "method");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void resetToDefault(String unitID) {
        updateServerData(60, false);
    }

    public void method(JSONObject obj) {
        //dummy method
    }

    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
            Integer stare = Integer.parseInt(responseObject.getString("stare"));
            Integer temperature = Integer.parseInt(responseObject.getString("temperatura"));
            this.setDisplayStatus(this.isStatus() ? this.getTemperature().toString() + "°C" : "OFF");
            this.setStatus(stare == 1 ? true : false);
            this.setTemperature(temperature);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StatsActivity instance = StatsActivity.getInstance();
        CommandCenterActivity instance2 = CommandCenterActivity.getInstance();

        if (instance == null) {
            instance2.updateSmartUnit(this);
        } else if (instance2 == null) {
            instance.updateSmartUnit(this);
        } else {
            instance.updateSmartUnit(this);
            instance2.updateSmartUnit(this);
        }
        Log.i("JSON response", responseObject.toString());
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
