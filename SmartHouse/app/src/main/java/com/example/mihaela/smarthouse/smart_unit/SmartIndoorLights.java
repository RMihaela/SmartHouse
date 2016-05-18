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
public class SmartIndoorLights extends ASmartUnit {
    private int bulbs_no = 3;
    private final Context context;

    public SmartIndoorLights(String id, String name,Context context){
        super(id, name);
        this.context=context;
        this.initialise();
    }
    public void updateServerData(Boolean status){

        this.setStatus(status);
        Integer st=status?1:0;
        JSONObject obj=new JSONObject();
        try {
            obj.put("stare",st);
            String url=this.urlstub+"c_lumini/"+this.getId();
            WebServiceManager.getInstance(context).startPUTRequest(url,obj,this,"method");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void method(JSONObject obj){
        //dummy method
    }
    @Override
    public void initialise() {
        String url = ASmartUnit.urlstub+"c_lumini/" + this.getId();
        WebServiceManager.getInstance(context).startGETRequest(url, this, "parseServerData");
    }

    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
            Integer stare=Integer.parseInt(responseObject.getString("stare"));
            Integer becuri=Integer.parseInt(responseObject.getString("nr_becuri"));
            this.setStatus(stare==1 ? true :false);
            this.setDisplayStatus(this.isStatus()?"ON":"OFF");
            this.setBulbs_no(becuri);
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

    public int getBulbs_no() {
        return bulbs_no;
    }

    public void setBulbs_no(int bulbs_no) {
        this.bulbs_no = bulbs_no;
    }
}
