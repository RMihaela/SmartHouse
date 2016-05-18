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
public class SmartWashingMachine extends  ASmartUnit {
    private int temperature = 30;
    private int rotations = 700;
    private String program = "silk";
    private final Context context;

    public SmartWashingMachine(String id, String name,Context context){
        super(id, name);
        this.context=context;
        this.initialise();
    }

    @Override
    public void updateServerData(Boolean status) {

    }

    @Override
    public void initialise() {
        String url = ASmartUnit.urlstub+"c_masina_spalat/" + this.getId();
        WebServiceManager.getInstance(context).startGETRequest(url, this, "parseServerData");
    }
    public void method(JSONObject obj){
        //dummy method
    }
    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
            Integer stare=Integer.parseInt(responseObject.getString("stare"));
            Integer temperature=Integer.parseInt(responseObject.getString("temperatura"));
            Integer rpm=Integer.parseInt(responseObject.getString("rotatii"));
            String program=responseObject.getString("program");
            this.setStatus(stare==1 ? true :false);
            this.setTemperature(temperature);
            this.setProgram(program);
            this.setRotations(rpm);
            this.setDisplayStatus(this.isStatus()?this.getProgram():"OFF");

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

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getRotations() {
        return rotations;
    }

    public void setRotations(int rotations) {
        this.rotations = rotations;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
