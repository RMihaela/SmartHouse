package com.example.mihaela.smarthouse.smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartWeatherSystem extends ASmartUnit {
    private Integer temperature = 30;
    private Integer windSpeed = 20;
    private Integer humidity = 20;
    private Integer precipitations = 0;

    public SmartWeatherSystem(String id, String name){
        super(id, name);
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Integer getPrecipitations() {
        return precipitations;
    }

    public void setPrecipitations(int precipitations) {
        this.precipitations = precipitations;
    }
}
