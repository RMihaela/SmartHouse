/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.machine_learning;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.home_activity.HomeScreen;
import com.example.mihaela.smarthouse.managers.WebServiceManager;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


/**
 *
 * @author Monica
 */
public class MachineLearningMain {
    private HomeScreen homeActivity;
    private JSONArray jArrayCostan = new JSONArray();
    private JSONArray jArrayCostel = new JSONArray();
//    private final Timer timer;
    public MachineLearningMain(){
//        timer = new Timer().scheduleAtFixedRate(startNeuralNetworks, 0, 100000);
    }

    public void startNeuralNetworks(HomeScreen homeActivity){
//        loadJsonFromResources();
        this.homeActivity = homeActivity;
        loadJsonFromServer();
    }

    public void parseJsonCostan(JSONObject responseObj){
        if (!responseObj.isNull("history")){
            try {
                jArrayCostan = responseObj.getJSONArray("history");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("JSON history Costan", jArrayCostan.toString());
        }
        String requestUrl = "http://" + ASmartUnit.ip + ":6543/api/sensors/history";
        WebServiceManager.getInstance(homeActivity.getApplicationContext()).startGETRequest(requestUrl, this, "parseJsonCostel");
    }

    public void parseJsonCostel(JSONObject responseObj){
        if (!responseObj.isNull("history")){
            try {
                jArrayCostel = responseObj.getJSONArray("history");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("JSON history Costel", jArrayCostel.toString());
        }
        mergeHistories(jArrayCostan, jArrayCostel);
    }

    /**
     *  Appends sourceArray at the end of destinationArray.
     * @param sourceArray
     * @param destinationArray
     */

    private void mergeHistories(JSONArray sourceArray, JSONArray destinationArray){
        try {
            for (int i = 0; i < sourceArray.length(); i++) {
                destinationArray.put(sourceArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.i("JSON history merged", destinationArray.toString());
        saveToFileStorage(destinationArray, "trainData.json");
        try{
            NeuralNetwork network = new NeuralNetwork(true, null);
            network.start(destinationArray.toString());
            NeuralNetwork net2 = new NeuralNetwork(false, getInputObject());
            net2.start(destinationArray.toString());
        } catch (Exception ex){
            Log.e("NeuralNetStart Err", ex.getMessage());
        }

    }

    private org.json.simple.JSONObject getInputObject(){
        org.json.simple.JSONObject inputObj = new org.json.simple.JSONObject();
        Date date = new Date();
        String today = android.text.format.DateFormat.format("EEEE", date).toString();
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        inputObj.put("day", today);
        inputObj.put("hour", hour);
        return inputObj;
    }

    private void saveToFileStorage(JSONArray jArray, String filename){
        FileOutputStream fos;
        try {
            fos = homeActivity.openFileOutput(filename, Context.MODE_PRIVATE);
            Log.i("### FOS fileChannel ###", fos.getChannel().toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(jArray.toString());
            Log.i("### FOS saved ###", jArray.toString());
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void loadJsonFromServer() {
        String requestUrl = "http://52.38.78.32:8080/api/sensors/history/";
        WebServiceManager.getInstance(homeActivity.getApplicationContext()).startGETRequest(requestUrl, this, "parseJsonCostan");
    }

    public String loadJsonFromResources(String filename){
        FileInputStream fis = null;
        String outputString = new String();
        try {
            fis = homeActivity.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputString = sb.toString();
        outputString = outputString.substring(outputString.indexOf('['));
//        Log.i("### FOS loadedString###", outputString);
        return outputString;
    }

    public String getSuggestionList(NeuralNetwork net) {
        return net.suggestions;
    }
}
