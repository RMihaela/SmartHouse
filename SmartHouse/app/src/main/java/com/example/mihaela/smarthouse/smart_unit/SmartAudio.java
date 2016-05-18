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
public class SmartAudio extends ASmartUnit{
    private int volume = 10;
    private String song = new String();
    private final Context context;

    public SmartAudio (String id, String name ,Context context){
        super(id, name);
        this.context=context;
        this.initialise();

    }
    public void updateServerData(Integer volume,String song,Boolean status){
        this.setVolume(volume);
        this.setSong(song);
        this.setStatus(status);
        Integer st=status?1:0;
        JSONObject obj=new JSONObject();
        try {
            obj.put("volum",volume);
            obj.put("melodie",song);
            obj.put("stare",st);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateServerData(Boolean status) {

    }

    @Override
    public void openEditorActivity() {

    }

    @Override
    public void initialise() {
        String url = ASmartUnit.urlstub+"c_sistem_audio/" + this.getId();
        WebServiceManager.getInstance(context).startGETRequest(url, this, "parseServerData");
    }
    public void method(JSONObject obj){
        //dummy method
    }

    @Override
    public void parseServerData(JSONObject responseObject) {
        try {
            Integer stare=Integer.parseInt(responseObject.getString("stare"));
            String song=responseObject.getString("melodie");
            this.setSong(song);
            this.setStatus(stare==1 ? true :false);
            this.setDisplayStatus(this.isStatus()?this.getVolume().toString():"OFF");
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

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
