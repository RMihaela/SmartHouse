package com.example.mihaela.smarthouse.smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartPool extends ASmartUnit {
    private Integer ph = 7;
    private Integer alkalinity = 10;
    private Integer hardness = 20;
    private Integer waterTemperature = 23;

    public SmartPool(String id, String name){
        super(id, name);
    }

    public Integer getPh() {
        return ph;
    }

    public void setPh(int ph) {
        this.ph = ph;
    }

    public Integer getAlkalinity() {
        return alkalinity;
    }

    public void setAlkalinity(int alkalinity) {
        this.alkalinity = alkalinity;
    }

    public Integer getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public Integer getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(int waterTemperature) {
        this.waterTemperature = waterTemperature;
    }
}
