/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.machine_learning;

import android.content.Context;
import android.util.Log;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.home_activity.HomeScreen;
import com.example.mihaela.smarthouse.planner.PlannerActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Monica
 */
public class NeuralNetwork {

    private List<Double> inputLayer;
    private List<Neuron> hiddenLayer;
    private List<Double> desiredOutput;
    private boolean startLearn;
    private List<Double> outputLayer;
    private boolean trainLoaded;

    public String suggestions;
    public int outputSize;

    private final JSONParser parser = new JSONParser();
    private JSONObject config;
    private JSONObject fileInput;

    public NeuralNetwork(boolean learn, JSONObject inputObj) throws IOException, ParseException {
        startLearn = false;
        inputLayer = new ArrayList<>();
        outputLayer = new ArrayList<>();
        hiddenLayer = new ArrayList<>();
        desiredOutput = new ArrayList<>();
        trainLoaded = false;
        startLearn = learn;
        fileInput = inputObj;

        config = (JSONObject) parser.parse(getConfigJsonString());
    }

    private String getConfigJsonString() throws IOException {
        InputStream is = HomeScreen.instance.getResources().openRawResource(R.raw.config);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        return writer.toString();
    }
//    private String getNeuronsJsonString() throws IOException {
//        InputStream is = HomeScreen.instance.getResources().openRawResource(R.raw.neurons);
//        Writer writer = new StringWriter();
//        char[] buffer = new char[1024];
//        try {
//            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            int n;
//            while ((n = reader.read(buffer)) != -1) {
//                writer.write(buffer, 0, n);
//            }
//        } finally {
//            is.close();
//        }
//        return writer.toString();
//    }

    private void configInputLayer() {
        JSONObject inputobj = (JSONObject) config.get("input");
        List<Double> input = new ArrayList<>();
        for (Iterator iterator = inputobj.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            JSONArray arr = (JSONArray) inputobj.get(key);
            for (int i = 0; i < arr.size(); i++) {
                input.add(0.0);
            }
        }
        this.setInputLayer(input);
    }

    private void configOutputLayer() {
        JSONArray outputRetea = (JSONArray) config.get("output");
        this.outputSize = outputRetea.size();
        this.addOutputLayer(outputRetea.size());
        if (startLearn == true) {
            for (int i = 0; i < outputRetea.size(); i++) {
                desiredOutput.add(-1.0);
            }
        }
    }

    private void setInputLayer(List<Double> input) {
        for (int i = 0; i < input.size(); i++) {
            inputLayer.add(input.get(i));
        }
    }

    public List<Double> getInputLayer() {
        return inputLayer;
    }
    
    public List<Double> getOutputLayer() {
        return outputLayer;
    }

    public void addOutputLayer(int num) {
        for (int i = 0; i < num; i++) {
            outputLayer.add(0.0);
        }
    }

    public void addNeurons() throws IOException, ParseException {
        Object obj = parser.parse(loadJsonFromResources("neurons.json"));
        JSONObject jsonObject = (JSONObject) obj;
        trainLoaded = jsonObject.size() > 0;
        if (outputLayer.isEmpty()) {
            Log.i("M a c h i n e Learning", "Add neurons error! Empty output layer..");
            return;
        }
        for (int i = 0; i < outputLayer.size(); i++) {
            Neuron neuron = new Neuron(inputLayer);
            if (trainLoaded == false) {
                Log.i("M a c h i n e Learning", "Randomize weights");
                neuron.randomize();
            } else {
                String label = "n_" + i;
                List<Double> weights = new ArrayList<>();
                //weights = trainLoad[label];
                JSONArray wg = (JSONArray) jsonObject.get(label);

                for (int k = 0; k < wg.size(); k++) {
                    double w = (double) wg.get(k);
                    weights.add(w);
                }

                if (weights.isEmpty()) {
                    neuron.randomize();
                } else {
                    neuron.setWeights(weights);
                }
            }
            hiddenLayer.add(neuron);
        }
    }

    public void setDesiredOutput(List<Double> output) {
        for (int i = 0; i < output.size(); i++) {
            desiredOutput.add(output.get(i));
        }
    }

    public List<Double> getDesiredOutput() {
        return desiredOutput;
    }

    public void updateWeights(List<Neuron> trainingSet) {
        if (trainingSet.size() != hiddenLayer.size()) {
            Log.i("M a c h i n e Learning", "train erorr. more neurons than required!");
            return;
        }
        for (int i = 0; i < trainingSet.size(); i++) {
            hiddenLayer.set(i, trainingSet.get(i));
        }
    }

    private void saveState() throws IOException {
        JSONObject obj = new JSONObject();
        for (int i = 0; i < hiddenLayer.size(); i++) {
            String label = "n_" + i;
            Neuron neur = hiddenLayer.get(i);
            JSONArray list = new JSONArray();
            for (int j = 0; j < neur.getWeights().size(); j++) {
                list.add(neur.getWeights().get(j));
            }
            obj.put(label, list);
        }
        saveStringToFileStorage(obj.toJSONString(), "neurons.json");
    }

    private void saveStringToFileStorage(String string, String filename){
        FileOutputStream fos;
        try {
            fos = HomeScreen.instance.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(string);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String loadJsonFromResources(String filename){
        FileInputStream fis = null;
        String outputString;
        try {
            fis = HomeScreen.instance.openFileInput(filename);
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
        outputString = outputString.substring(outputString.indexOf('{'));
//        Log.i("### FOS loadedString###", outputString);
        return outputString;
    }

    private void saveOutput() throws IOException {
        JSONObject saveOutput = new JSONObject();
        JSONArray outputRetea = (JSONArray) config.get("output");
        JSONArray outList = new JSONArray();
        for (int i = 0; i < outputLayer.size(); i++) {
            if (outputLayer.get(i) == 1.0) {
                outList.add(outputRetea.get(i));
            }
        }
        saveOutput.put("suggestions", outList);
        this.parseOutput(outList);
    }

    private void parseOutput(JSONArray jsonArray){
        for (int i = 0; i < jsonArray.size(); ++i){
            PlannerActivity.addPlan(jsonArray.get(i).toString(), true);
        }
    }

    private void setNetworkParams() throws IOException, ParseException {
        JSONObject paramRetea = fileInput;
        JSONObject inputconfig = (JSONObject) config.get("input");
        int offset = 0;
        for (Iterator it = paramRetea.keySet().iterator(); it.hasNext();) {
            String inKey = (String) it.next();
            JSONArray inArr = (JSONArray) inputconfig.get(inKey);
            int inputInd = inArr.indexOf(paramRetea.get(inKey));
            inputInd = inputInd + offset;
            inputLayer.set(inputInd, 1.0);
            offset = offset + inArr.size();
        }
    }

    private int setLearnParams(String action, JSONObject inputRetea) throws IOException, ParseException {

        JSONObject inputobj = (JSONObject) config.get("input");
        JSONArray outputRetea = (JSONArray) config.get("output");

        int idx = outputRetea.indexOf(action);
        int offset = 0;
        if (idx >= 0) {
            desiredOutput.set(idx, 1.0);
            for (Iterator it = inputRetea.keySet().iterator(); it.hasNext();) {
                String inKey = (String) it.next();
                //System.out.println(inputRetea.get(inKey));
                JSONArray inArr = (JSONArray) inputobj.get(inKey);
                //System.out.println(inArr);
                int inputInd = inArr.indexOf(inputRetea.get(inKey));
                inputInd = inputInd + offset;

                inputLayer.set(inputInd, 1.0);

                offset = offset + inArr.size();
            }
            return 1;
        }
        return -1;
    }

    public void start(String jsArrayString) throws IOException, ParseException {

        this.configInputLayer();
        this.configOutputLayer();
        this.addNeurons();

        if (startLearn == true) {
            JSONArray paramRetea = (JSONArray) parser.parse(jsArrayString);
            for( int iterator = 0; iterator < paramRetea.size(); iterator++ ) {
                JSONObject actionObj = (JSONObject) paramRetea.get(iterator);
                Iterator it = actionObj.keySet().iterator();
                String action = (String) it.next();
                JSONObject inputRetea = (JSONObject) actionObj.get(action);
                for( int i = 0; i < inputLayer.size(); i++ ) {
                    inputLayer.set(i, 0.0);
                }
                for( int i = 0; i < desiredOutput.size(); i++ ) {
                    desiredOutput.set(i, -1.0);
                }
                if(this.setLearnParams(action, inputRetea) == 1) {
                    LearningRule learningRule = new LearningRule(this);
                    learningRule.setTrainingSet(hiddenLayer);
                    learningRule.learn();
                    updateWeights(learningRule.getTrainingSet());
                    saveState();
                }

            }

        } else {
            this.setNetworkParams();
            for (int i = 0; i < hiddenLayer.size(); i++) {
                double output = hiddenLayer.get(i).compute();
                outputLayer.set(i, output);
            }
            //System.out.println(outputLayer);
            saveOutput();
        }
    }


}
