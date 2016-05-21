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
public class SmartPool extends ASmartUnit {
    private Double ph = 7.0;
    private Double alkalinity = 10.0;
    private Double hardness = 20.0;
    private Double waterTemperature = 23.0;
    private final Context context;

    public SmartPool(String id, String name,Context context){
        super(id, name);
        this.context=context;
        this.initialise();
    }

    @Override
    public void resetToDefault(String unitID) {

    }

    public void setServerPH(Integer ph){
        String url ="http://52.38.78.32:8080/api/sensors/pool/default/set/ph/";
        String phToSet=ph.toString();
        url+=phToSet;
    }
    public void setServerAlkalinity(Integer alk){
        String url ="http://52.38.78.32:8080/api/sensors/pool/default/set/alkalinity/";
        String alkToSet=alk.toString();
        url+=alkToSet;
    }
    public void setServerHardness(Integer hardness){
        String url ="http://52.38.78.32:8080/api/sensors/pool/default/set/hardness/";
        String hardnessToSet=hardness.toString();
        url+=hardnessToSet;
    }
    public void setServerTemperature(Integer temperature){
        String url ="http://52.38.78.32:8080/api/sensors/pool/default/set/watertemperature/";
        String temperatureToSet=temperature.toString();
        url+=temperatureToSet;
    }
    public void setServerCover(Boolean covered){
        this.setStatus(covered);
        String url ="http://52.38.78.32:8080/api/sensors/pool/default/set/covered/";
        Integer value=covered?1:0;
        url+=value.toString();

    }
    public void method(JSONObject obj){
        //dummy method
    }

    @Override
    public void updateServerData(Boolean status) {
        String url ="http://52.38.78.32:8080/api/sensors/pool/default/set/covered/";
        Integer value=status?1:0;
        url+=value.toString();
        WebServiceManager.getInstance(context).startPOSTRequest(url);

    }

    @Override
    public void openEditorActivity() {

    }

    @Override
    public void initialise() {
        String url ="http://52.38.78.32:8080/api/sensors/pool/default";
        WebServiceManager.getInstance(getContext()).startGETRequest(url, this, "parseServerData");
    }

    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
            Double ph=Double.parseDouble(responseObject.getString("ph"));
            Double alkalinity=Double.parseDouble(responseObject.getString("alkalinity"));
            Double hardness=Double.parseDouble(responseObject.getString("hardness"));
            Double waterTemperature=Double.parseDouble(responseObject.getString("watertemperature"));
            String status=responseObject.getString("covered");
            if(status.equals("true")){
                this.setStatus(true);
            }
            else{
                this.setStatus(false);
            }
            this.ph=ph;
            this.alkalinity=alkalinity;
            this.hardness=hardness;
            this.waterTemperature=waterTemperature;
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

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getAlkalinity() {
        return alkalinity;
    }

    public void setAlkalinity(Double alkalinity) {
        this.alkalinity = alkalinity;
    }

    public Double getHardness() {
        return hardness;
    }

    public void setHardness(Double hardness) {
        this.hardness = hardness;
    }

    public Double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }



    public Context getContext() {
        return context;
    }
}
