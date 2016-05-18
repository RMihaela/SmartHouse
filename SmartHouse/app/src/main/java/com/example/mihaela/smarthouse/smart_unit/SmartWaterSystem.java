package com.example.mihaela.smarthouse.smart_unit;

import android.content.Context;
import android.util.Log;

import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.managers.WebServiceManager;
import com.example.mihaela.smarthouse.stats.StatsActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartWaterSystem extends ASmartUnit {
    private Integer temperature = 30;
    private Integer pressure = 1;
    private final Context context;

    public SmartWaterSystem(String id, String name,Context context){
        super(id, name);
        this.context=context;
        this.initialise();
    }

    @Override
    public void updateServerData(Boolean status) {

    }

    @Override
    public void initialise() {
        String url = ASmartUnit.urlstub+"c_apa/" + this.getId();
        WebServiceManager.getInstance(context).startGETRequest(url, this, "parseServerData");
    }
    public void method(JSONObject obj){
        //dummy method
    }
    @Override
    public void parseServerData(JSONObject responseObject) {
        try {

            Integer temperature=Integer.parseInt(responseObject.getString("temperatura"));
            Integer pressure=Integer.parseInt(responseObject.getString("presiune"));

            this.setTemperature(temperature);
            this.setPressure(pressure);
            this.setDisplayStatus(this.getTemperature().toString()+"°C");


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

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
