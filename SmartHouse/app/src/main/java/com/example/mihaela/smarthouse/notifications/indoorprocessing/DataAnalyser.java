/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.notifications.indoorprocessing;

import android.content.Context;

import com.example.mihaela.smarthouse.managers.WebServiceManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RGI3
 */
public class DataAnalyser extends TimerTask {
    private Context context;

    public String ip = "192.168.43.211";
    String masSpalatRequestURL = "http://" + ip + ":6543/api/c_masina_spalat";
    String masSpalatTargetURL = "";
    
    String audioRequestURL = "http://" + ip + ":6543/api/c_sistem_audio";
    String audioTargetURL = "";
    
    String aerRequestURL = "http://" + ip + ":6543/api/c_aer_conditionat";
    String aerTargetURL = "";
    
    String apaRequestURL = "http://" + ip + ":6543/api/c_apa";
    String apaTargetURL = "";
    
    String aragazRequestURL = "http://" + ip + ":6543/api/c_aragaz";
    String aragazTargetURL = "";
    
    String frigiderRequestURL = "http://" + ip + ":6543/api/c_frigider";
    String frigiderTargetURL = "";

    public void loop(int time_interval){
        Timer timer = new Timer();
        DataAnalyser dataAn = new DataAnalyser();
        dataAn.setContext(this.context);
        timer.schedule(dataAn, 0, time_interval);
    }
    
    @Override
    public void run() {
       analyse();
    }

    public void analyse() {
//        JSONArray jsonMasina = getJsonFromServer(masSpalatRequestURL);
//        MasinaSpalatAlert masAl = new MasinaSpalatAlert();
//        masAl.JsonAnalyser(jsonMasina, masSpalatTargetURL);
//
//        JSONArray jsonAudio = getJsonFromServer(audioRequestURL);
//        AudioAlert audAl = new AudioAlert();
//        audAl.JsonAnalyser(jsonAudio, audioTargetURL);
//
//        JSONArray jsonAer = getJsonFromServer(aerRequestURL);
//        AerConditionatAlert aerAl = new AerConditionatAlert();
//        aerAl.JsonAnalyser(jsonAer, aerTargetURL);
//
//        JSONArray jsonApa = getJsonFromServer(apaRequestURL);
//        ApaAlert apaAl = new ApaAlert();
//        apaAl.JsonAnalyser(jsonApa, apaTargetURL);
//
//        JSONArray jsonAragaz = getJsonFromServer(aragazRequestURL);
//        AragazAlert araAl = new AragazAlert();
//        araAl.JsonAnalyser(jsonAragaz, aragazTargetURL);

//        JSONArray jsonFrigider = getJsonFromServer(frigiderRequestURL);
//        FrigiderAlert friAl = new FrigiderAlert();
//        friAl.JsonAnalyser(jsonFrigider, frigiderTargetURL);

        getJsonFromServerFridge(frigiderRequestURL);
        getJsonFromServerAudio(audioRequestURL);
        getJsonFromServerAer(aerRequestURL);
        getJsonFromServerAragaz(aragazRequestURL);
        getJsonFromServerApa(apaRequestURL);
        getJsonFromServerMasinaSpalat(masSpalatRequestURL);
    }

    public void parseJsonFridge(JSONArray jArray){
        FrigiderAlert friAl = new FrigiderAlert();
        friAl.JsonAnalyser(jArray, frigiderTargetURL);

        return;
    }
    public void parseJsonAudio(JSONArray jArray){
        AudioAlert friAl = new AudioAlert();
        friAl.JsonAnalyser(jArray, frigiderTargetURL);

        return;
    }
    public void parseJsonAer(JSONArray jArray){
        AerConditionatAlert friAl = new AerConditionatAlert();
        friAl.JsonAnalyser(jArray, frigiderTargetURL);

        return;
    }
    public void parseJsonAragaz(JSONArray jArray){
        AragazAlert friAl = new AragazAlert();
        friAl.JsonAnalyser(jArray, frigiderTargetURL);

        return;
    }
    public void parseJsonApa(JSONArray jArray){
        ApaAlert friAl = new ApaAlert();
        friAl.JsonAnalyser(jArray, frigiderTargetURL);

        return;
    }
    public void parseJsonMasinaSpalat(JSONArray jArray){
        MasinaSpalatAlert friAl = new MasinaSpalatAlert();
        friAl.JsonAnalyser(jArray, frigiderTargetURL);

        return;
    }

    public void getJsonFromServerFridge(String RequestURL) {
        WebServiceManager.getInstance(this.getContext()).startGETArrayRequest(RequestURL, this, "parseJsonFridge");
    }
    public void getJsonFromServerAudio(String RequestURL) {
        WebServiceManager.getInstance(this.getContext()).startGETArrayRequest(RequestURL, this, "parseJsonAudio");
    }
    public void getJsonFromServerAer(String RequestURL) {
        WebServiceManager.getInstance(this.getContext()).startGETArrayRequest(RequestURL, this, "parseJsonAer");
    }
    public void getJsonFromServerAragaz(String RequestURL) {
        WebServiceManager.getInstance(this.getContext()).startGETArrayRequest(RequestURL, this, "parseJsonAragaz");
    }
    public void getJsonFromServerApa(String RequestURL) {
        WebServiceManager.getInstance(this.getContext()).startGETArrayRequest(RequestURL, this, "parseJsonApa");
    }
    public void getJsonFromServerMasinaSpalat(String RequestURL) {
        WebServiceManager.getInstance(this.getContext()).startGETArrayRequest(RequestURL, this, "parseJsonMasinaSpalat");
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
