package com.example.mihaela.smarthouse.smart_unit;

import org.json.JSONObject;

/**
 * Created by Frida on 15-May-16.
 */
public abstract class ASmartUnit {
    private String id = new String();
    private String name = new String();
    public static String urlstub="http://192.168.43.211:6543/api/";
    private String displayStatus=new String() ;
    private boolean status = false;

    public  abstract  void resetToDefault(String unitID);

    public ASmartUnit(){

    }

    public ASmartUnit(String id, String name){
        this.setId(id);
        this.setName(name);
        this.setStatus(false);
    }

    public abstract  void updateServerData(Boolean status);

    public abstract void openEditorActivity();

    public abstract void initialise();

    public abstract void parseServerData(JSONObject responseObject);


    public String getStringStatus(){
        return status? "ON" : "OFF";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }
}
