package com.example.mihaela.smarthouse.smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartFridge extends ASmartUnit {
    private Integer temperature = 6;

    public SmartFridge(String id, String name){
        super(id, name);
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
