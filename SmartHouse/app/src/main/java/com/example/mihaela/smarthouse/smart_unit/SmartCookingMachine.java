package com.example.mihaela.smarthouse.smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartCookingMachine extends ASmartUnit {
    private int temperature = 180;

    public SmartCookingMachine(String id, String name){
        super(id, name);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
