package com.example.mihaela.smarthouse.smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartAC extends ASmartUnit {
    private int temperature = 20;
    private int intensity = 1;

    public SmartAC (String id, String name){
        super(id, name);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
