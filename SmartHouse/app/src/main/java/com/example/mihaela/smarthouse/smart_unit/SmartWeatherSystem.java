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
public class SmartWeatherSystem extends ASmartUnit {
    private Context context;
    private Double temperature = 30.0;
    private Double windSpeed = 20.0;
    private Double humidity = 20.0;
    private Double precipitations = 0.0;

    public SmartWeatherSystem(String id, String name,Context context){
        super(id, name);
        this.setContext(context);
        this.initialise();
    }

    @Override
    public void updateServerData(Boolean status) {

    }

    @Override
    public void openEditorActivity() {

    }

    @Override
    public void initialise() {
        String url ="http://52.38.78.32:8080/api/sensors/weather/default";
        WebServiceManager.getInstance(getContext()).startGETRequest(url, this, "parseServerData");

    }
    public void method(JSONObject obj){
        //dummy method
    }
    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
            Double temperature=Double.parseDouble(responseObject.getString("temperature"));
            Double windSpeed=Double.parseDouble(responseObject.getString("windspeed"));
            Double humidity=Double.parseDouble(responseObject.getString("humidity"));
            Double precipitations=Double.parseDouble(responseObject.getString("precipitation"));
            this.setTemperature(temperature);
            this.setWindSpeed(windSpeed);
            this.setHumidity(humidity);
            this.setPrecipitations(precipitations);
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
        Log.i("JSON response weather", responseObject.toString());

    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPrecipitations() {
        return precipitations;
    }

    public void setPrecipitations(Double precipitations) {
        this.precipitations = precipitations;
    }

    public Context getContext() {
        return context;
    }


    public void setContext(Context context) {
        this.context = context;
    }
}
