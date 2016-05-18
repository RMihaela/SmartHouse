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
public class SmartOutdoorLights extends ASmartUnit {
    private Context context;
    public SmartOutdoorLights(String id, String name,Context context){
        super(id, name);
        this.context=(context);
        this.initialise();
    }

    @Override
    public void updateServerData(Boolean status) {
        String url ="http://52.38.78.32:8080/api/sensors/yard/default/set/lightson/";
        String stat=status?"true":"false";
        url+=stat;
        WebServiceManager.getInstance(context).startPOSTRequest(url);

    }

    @Override
    public void openEditorActivity() {

    }

    @Override
    public void initialise() {
        String url ="http://52.38.78.32:8080/api/sensors/yard/default";
        WebServiceManager.getInstance(getContext()).startGETRequest(url, this, "parseServerData");

    }
    public void setServerOutdoorLights(Boolean status){
        String url ="http://52.38.78.32:8080/api/sensors/yard/default/set/lightson";
        String stat=status?"true":"false";
        url+=stat;
    }
    public void method(JSONObject obj){
        //dummy method
    }
    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
           String status=responseObject.getString("lightson");
            if(status.equals("true")){
                this.setStatus(true);
            }
            else{
                this.setStatus(false);
            }
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

    public Context getContext() {
        return context;
    }


}
