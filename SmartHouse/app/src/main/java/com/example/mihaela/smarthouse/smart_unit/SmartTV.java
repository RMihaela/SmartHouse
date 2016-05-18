package com.example.mihaela.smarthouse.smart_unit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.editor_activities.TvEditor;
import com.example.mihaela.smarthouse.managers.WebServiceManager;
import com.example.mihaela.smarthouse.stats.StatsActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartTV extends ASmartUnit {
    private final Context context;
    private String channel = "-";
    private int volume = 10;
    private final String urlstub = "http://192.168.0.105:6543/api/c_tv/";

    public void parseServerData(JSONObject responseObject) {
        try {
            Integer stare=Integer.parseInt(responseObject.getString("stare"));
            Integer volum=Integer.parseInt(responseObject.getString("volum"));
            String canal=responseObject.getString("canal");
            this.setChannel(canal);
            this.setVolume(volum);
            this.setStatus(stare==1 ? true :false);
            this.setDisplayStatus(this.isStatus()?this.getChannel():"OFF");
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
        //updateServerData(this.volume,"MTV",true);

    }
    public void method(JSONObject obj){
        //dummy method
    }
    public void updateServerData(Integer volume,String channel,Boolean status){
        this.setVolume(volume);
        this.setChannel(channel);
        this.setStatus(status);
        Integer st=status?1:0;
        JSONObject obj=new JSONObject();
        try {
            obj.put("volum",volume);
            obj.put("canal",channel);
            obj.put("stare",st);
            String url = ASmartUnit.urlstub+"c_tv/" + this.getId();
            WebServiceManager.getInstance(context).startPUTRequest(url,obj,this,"method");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateServerData(Boolean status) {

    }

    @Override
    public void openEditorActivity() {
        TvEditor.setSmartUnit(this);
        Intent intent = new Intent(context, TvEditor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void initialise(){
        String url = ASmartUnit.urlstub+"c_tv/" + this.getId();
        WebServiceManager.getInstance(context).startGETRequest(url, this, "parseServerData");
    }
    public SmartTV(String id, String name, Context context) {
        super(id, name);
        this.context = context;
        this.initialise();

    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
