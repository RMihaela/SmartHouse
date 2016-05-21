package com.example.mihaela.smarthouse.smart_unit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.editor_activities.AirConditioningEditor;
import com.example.mihaela.smarthouse.editor_activities.WaterSystemEditor;
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
    public final Context context;

    public SmartWaterSystem(String id, String name,Context context){
        super(id, name);
        this.context=context;
        this.initialise();
    }

    public SmartWaterSystem (String id, String name){
        super(id, name);
        this.context = null;
    }

    @Override
    public void updateServerData(Boolean status) {

    }
    public void updateServerData(Integer temperature,Integer pressure){
        this.setPressure(pressure);
        this.setTemperature(temperature);

        JSONObject obj=new JSONObject();
        try {
            obj.put("temperatura",temperature);
            obj.put("presiune",pressure);

            String url = ASmartUnit.urlstub+"c_apa/" + this.getId();

            WebServiceManager.getInstance(context).startPUTRequest(url,obj,this,"method");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void openEditorActivity() {
        WaterSystemEditor.setSmartUnit(this);
        Intent intent = new Intent(context, WaterSystemEditor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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
    public void resetToDefault(String unitID) {
        updateServerData(20, 2);
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
