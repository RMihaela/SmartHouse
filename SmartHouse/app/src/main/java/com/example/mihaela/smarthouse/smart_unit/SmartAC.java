package com.example.mihaela.smarthouse.smart_unit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.editor_activities.AirConditioningEditor;
import com.example.mihaela.smarthouse.editor_activities.TvEditor;
import com.example.mihaela.smarthouse.managers.WebServiceManager;
import com.example.mihaela.smarthouse.stats.StatsActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartAC extends ASmartUnit {
    private Integer temperature = 20;
    private final Context context;
    private Integer intensity = 1;

    public SmartAC (String id, String name,Context context){
        super(id, name);
        this.context=context;
        this.initialise();

    }

    public SmartAC (String id, String name){
        super(id, name);
        this.context = null;
    }

    public void updateServerData(Integer temperature,Integer intensity,Boolean status){
        this.setIntensity(intensity);
        this.setTemperature(temperature);
        this.setStatus(status);
        Integer st=status?1:0;
        JSONObject obj=new JSONObject();
        try {
            obj.put("temperatura",temperature);
            obj.put("intensitate",intensity);
            obj.put("stare",st);
            String url = ASmartUnit.urlstub+"c_aer_conditionat/" + this.getId();

            WebServiceManager.getInstance(context).startPUTRequest(url,obj,this,"method");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void method(JSONObject obj){
        //dummy method
    }

    @Override
    public void resetToDefault(String unitID) {
        updateServerData(20, 1, true);
    }

    @Override
    public void updateServerData(Boolean status) {

    }

    @Override
    public void openEditorActivity() {
        AirConditioningEditor.setSmartUnit(this);
        Intent intent = new Intent(context, AirConditioningEditor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void initialise() {
        String url = ASmartUnit.urlstub+"c_aer_conditionat/" + this.getId();

        WebServiceManager.getInstance(context).startGETRequest(url, this, "parseServerData");
    }

    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
            Integer stare = Integer.parseInt(responseObject.getString("stare"));
            Integer temperature = Integer.parseInt(responseObject.getString("temperatura"));
            Integer intensity = Integer.parseInt(responseObject.getString("intensitate"));
            this.setIntensity(intensity);
            this.setStatus(stare == 1 ? true : false);
            this.setTemperature(temperature);
            this.setDisplayStatus(this.isStatus() ? this.getTemperature().toString() + "°C" : "OFF");

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
        // updateServerData(this.temperature+20,this.intensity+5,!this.isStatus());
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }
}
