package com.example.mihaela.smarthouse.smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartIndoorLights extends ASmartUnit {
    private int bulbs_no = 3;

    public SmartIndoorLights(String id, String name){
        super(id, name);
    }

    public int getBulbs_no() {
        return bulbs_no;
    }

    public void setBulbs_no(int bulbs_no) {
        this.bulbs_no = bulbs_no;
    }
}
