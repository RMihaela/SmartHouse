package com.example.mihaela.smarthouse.stats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mihaela.smarthouse.managers.AlertsManager;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;
import com.example.mihaela.smarthouse.smart_unit.SmartAC;
import com.example.mihaela.smarthouse.smart_unit.SmartAudio;
import com.example.mihaela.smarthouse.smart_unit.SmartBlinds;
import com.example.mihaela.smarthouse.smart_unit.SmartCookingMachine;
import com.example.mihaela.smarthouse.smart_unit.SmartFridge;
import com.example.mihaela.smarthouse.smart_unit.SmartGarageDoor;
import com.example.mihaela.smarthouse.smart_unit.SmartIndoorLights;
import com.example.mihaela.smarthouse.smart_unit.SmartOutdoorLights;
import com.example.mihaela.smarthouse.smart_unit.SmartPool;
import com.example.mihaela.smarthouse.smart_unit.SmartSprinklers;
import com.example.mihaela.smarthouse.smart_unit.SmartTV;
import com.example.mihaela.smarthouse.smart_unit.SmartVacuumCleaner;
import com.example.mihaela.smarthouse.smart_unit.SmartWashingMachine;
import com.example.mihaela.smarthouse.smart_unit.SmartWaterSystem;
import com.example.mihaela.smarthouse.smart_unit.SmartWeatherSystem;
import com.example.mihaela.smarthouse.smart_unit.SmartYardDoor;
//Andrei

public class StatsActivity extends AppCompatActivity {
    private static StatsActivity instance = null;

    public static StatsActivity getInstance(){
        return instance;
    }

    private StatsListAdapter ExpAdapter;
    private ArrayList<StatsListHeader> ExpListItems = new ArrayList<>();
    private ExpandableListView ExpandList;
    Map<String, List<ASmartUnit>> outdoorMap = new HashMap<>();
    Map<String, List<ASmartUnit>> indoorMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertsManager.setContext(this);
        instance = this;
        //Kitchen
        List<ASmartUnit> aSmartUnitList = new ArrayList<>();
        ASmartUnit tv = new SmartTV("0d4036", "TV", this.getApplicationContext());
        aSmartUnitList.add(tv);
        ASmartUnit blinds = new SmartBlinds("798dd7", "Blinds", this.getApplicationContext());
        aSmartUnitList.add(blinds);
        ASmartUnit indoorLights = new SmartIndoorLights("465207", "Lights", this.getApplicationContext());
        aSmartUnitList.add(indoorLights);
        ASmartUnit vacuum = new SmartVacuumCleaner("0c6d56", "Vacuum Cleaner", this.getApplicationContext());
        aSmartUnitList.add(vacuum);
        ASmartUnit waterSystem = new SmartWaterSystem("05f6e3", "Water System", this.getApplicationContext());
        aSmartUnitList.add(waterSystem);
        ASmartUnit cookingMachine = new SmartCookingMachine("796de4", "Cooking Machine", this.getApplicationContext());
        aSmartUnitList.add(cookingMachine);
        ASmartUnit fridge = new SmartFridge("77c80f", "Fridge", this.getApplicationContext());
        aSmartUnitList.add(fridge);
        ASmartUnit ac = new SmartAC("29293b", "AC", this.getApplicationContext());
        aSmartUnitList.add(ac);

        indoorMap.put("Kitchen", aSmartUnitList);

        //Bedroom
        aSmartUnitList = new ArrayList<>();
        tv = new SmartTV("2979f7", "TV", this.getApplicationContext());

        aSmartUnitList.add(tv);
        blinds = new SmartBlinds("9bb6ed", "Blinds", this.getApplicationContext());
        aSmartUnitList.add(blinds);
        indoorLights = new SmartIndoorLights("c303df", "Lights", this.getApplicationContext());
        aSmartUnitList.add(indoorLights);
        vacuum = new SmartVacuumCleaner("29073f", "Vaccum Cleaner", this.getApplicationContext());
        aSmartUnitList.add(vacuum);
        ac = new SmartAC("936378", "AC", this.getApplicationContext());
        aSmartUnitList.add(ac);

        indoorMap.put("Bedroom", aSmartUnitList);

        //Living
        aSmartUnitList = new ArrayList<>();
        tv = new SmartTV("e1fdf1", "TV", this.getApplicationContext());

        aSmartUnitList.add(tv);
        blinds = new SmartBlinds("bca0cf", "Blinds", this.getApplicationContext());
        aSmartUnitList.add(blinds);
        indoorLights = new SmartIndoorLights("c6936a", "Lights", this.getApplicationContext());
        aSmartUnitList.add(indoorLights);
        vacuum = new SmartVacuumCleaner("7b6c00", "Vaccum Cleaner", this.getApplicationContext());
        aSmartUnitList.add(vacuum);
        ASmartUnit audio = new SmartAudio("8c59e2", "Audio", this.getApplicationContext());
        aSmartUnitList.add(audio);
        ac = new SmartAC("e04e63", "AC", this.getApplicationContext());
        aSmartUnitList.add(ac);

        indoorMap.put("Living", aSmartUnitList);

        //Bathroom
        aSmartUnitList = new ArrayList<>();
        blinds = new SmartBlinds("bce4eb", "Blinds", this.getApplicationContext());
        aSmartUnitList.add(blinds);
        indoorLights = new SmartIndoorLights("e6f43f", "Lights", this.getApplicationContext());
        aSmartUnitList.add(indoorLights);
        ASmartUnit washingMachine = new SmartWashingMachine("26bd1a", "Washing Machine", this.getApplicationContext());
        aSmartUnitList.add(washingMachine);
        waterSystem = new SmartWaterSystem("9bf53d", "Water System", this.getApplicationContext());
        aSmartUnitList.add(waterSystem);

        indoorMap.put("Bathroom", aSmartUnitList);

        //Weather
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartWheather = new SmartWeatherSystem("1", "Temperature", this.getApplicationContext());
        aSmartUnitList.add(smartWheather);
        smartWheather = new SmartWeatherSystem("2", "Humidity", this.getApplicationContext());
        aSmartUnitList.add(smartWheather);
        smartWheather = new SmartWeatherSystem("3", "Wind speed", this.getApplicationContext());
        aSmartUnitList.add(smartWheather);
        smartWheather = new SmartWeatherSystem("4", "Precipitation", this.getApplicationContext());
        aSmartUnitList.add(smartWheather);

        outdoorMap.put("Weather", aSmartUnitList);

        //Pool
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartPool = new SmartPool("5", "Ph", this.getApplicationContext());
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("6", "Hardness", this.getApplicationContext());
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("7", "Temperature", this.getApplicationContext());
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("8", "Alkalinity", this.getApplicationContext());
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("9", "Cover", this.getApplicationContext());
        aSmartUnitList.add(smartPool);

        outdoorMap.put("Pool", aSmartUnitList);

        //Yard
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartYard = new SmartYardDoor("10", "Yard door", this.getApplicationContext());
        aSmartUnitList.add(smartYard);
        smartYard = new SmartGarageDoor("11", "Garage door", this.getApplicationContext());
        aSmartUnitList.add(smartYard);
        smartYard = new SmartOutdoorLights("12", "Lights", this.getApplicationContext());
        aSmartUnitList.add(smartYard);
        smartYard = new SmartSprinklers("13", "Sprinklers", this.getApplicationContext());
        aSmartUnitList.add(smartYard);

        outdoorMap.put("Yard", aSmartUnitList);

        //Garden
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartGarden = new SmartSprinklers("14", "Sprinklers", this.getApplicationContext());
        aSmartUnitList.add(smartGarden);

        outdoorMap.put("Garden", aSmartUnitList);


        setContentView(R.layout.activity_stats);

        ExpandList = (ExpandableListView) findViewById(R.id.exp_list);

        ExpListItems = SetStandardGroups();
        ExpAdapter = new StatsListAdapter(StatsActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

        Runnable run = new Runnable() {


            public void run() {

                ExpListItems.clear();
                ArrayList<StatsListHeader> list = new ArrayList<StatsListHeader>();

                ArrayList<StatsListItem> ch_list;

                for (String group_name : outdoorMap.keySet()) {
                    int j = 0;
                    StatsListHeader gru = new StatsListHeader();
                    gru.setName(group_name);

                    ch_list = new ArrayList<StatsListItem>();
                    for (; j < outdoorMap.get(group_name).size(); j++) {
                        StatsListItem ch = new StatsListItem();
                        ch.setTitle(outdoorMap.get(group_name).get(j).getName());

                        ASmartUnit smartUnit = outdoorMap.get(group_name).get(j);
                        String id = smartUnit.getId();

                        ch.setId(id);

                        String status = getStatusForSmartUnit(smartUnit);
                        ch.setStatus(status);
                        ch_list.add(ch);
                    }
                    gru.setItems(ch_list);
                    list.add(gru);
                }

                for (String group_name : indoorMap.keySet()) {
                    int j = 0;
                    StatsListHeader gru = new StatsListHeader();
                    gru.setName(group_name);

                    ch_list = new ArrayList<StatsListItem>();
                    for (; j < indoorMap.get(group_name).size(); j++) {
                        StatsListItem ch = new StatsListItem();
                        ch.setTitle(indoorMap.get(group_name).get(j).getName());
                        ASmartUnit smartUnit = indoorMap.get(group_name).get(j);
                        String id = smartUnit.getId();

                        ch.setId(id);
                        String status = getStatusForSmartUnit(smartUnit);
                        ch.setStatus(status);
                        ch_list.add(ch);
                    }
                    gru.setItems(ch_list);
                    list.add(gru);
                }
                ExpListItems.addAll(list);
                ExpAdapter.notifyDataSetChanged();
                ExpandList.invalidateViews();
                ExpandList.refreshDrawableState();
            }

        };

        runOnUiThread(run);


    }

    public ArrayList<StatsListHeader> SetStandardGroups() {

        this.ExpListItems.clear();
        ArrayList<StatsListHeader> list = new ArrayList<>();

        ArrayList<StatsListItem> ch_list;

        for (String group_name : outdoorMap.keySet()) {
            int j = 0;
            StatsListHeader gru = new StatsListHeader();
            gru.setName(group_name);

            ch_list = new ArrayList<StatsListItem>();
            for (; j < outdoorMap.get(group_name).size(); j++) {
                StatsListItem ch = new StatsListItem();
                ch.setTitle(outdoorMap.get(group_name).get(j).getName());
                ASmartUnit smartUnit = outdoorMap.get(group_name).get(j);
                String status = getStatusForSmartUnit(smartUnit);
                ch.setStatus(status);
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);
        }

        for (String group_name : indoorMap.keySet()) {
            int j = 0;
            StatsListHeader gru = new StatsListHeader();
            gru.setName(group_name);

            ch_list = new ArrayList<StatsListItem>();
            for (; j < indoorMap.get(group_name).size(); j++) {
                StatsListItem ch = new StatsListItem();
                ch.setTitle(indoorMap.get(group_name).get(j).getName());

                ASmartUnit smartUnit = indoorMap.get(group_name).get(j);
                String status = getStatusForSmartUnit(smartUnit);
                ch.setStatus(status);
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);
        }

        return list;
    }

    public void updateSmartUnit( ASmartUnit smartUnit) {
        for(StatsListHeader head:ExpListItems)
            for(StatsListItem item:head.getItems()){
                if(item.getId().equals(smartUnit.getId())){
                    String newStatus = getStatusForSmartUnit(smartUnit);
                    item.setStatus(newStatus);
                    Log.i("SU Status Update", item.getStatus());
                    ExpAdapter.notifyDataSetChanged();
                    ExpandList.invalidateViews();
                    ExpandList.refreshDrawableState();
                }
            }

    }

    private String getStatusForSmartUnit(ASmartUnit smartUnit) {
        String className = smartUnit.getClass().getSimpleName();
        String status = new String();
        switch (className) {
//            case "SmartTV":
//                status = ((SmartTV) smartUnit).getChannel();
//                break;
//            case "SmartBlinds":
//                status = smartUnit.getStringStatus();
//                break;
//            case "SmartIndoorLights":
//                status = smartUnit.getStringStatus();
//                break;
//            case "SmartVacuumCleaner":
//                status = smartUnit.getStringStatus();
//                break;
//            case "SmartWaterSystem":
//                status = ((SmartWaterSystem) (smartUnit)).getTemperature().toString() + "°C";
//                break;
//            case "SmartCookingMachine":
//                status = smartUnit.getStringStatus();
//                break;
//            case "SmartFridge":
//                status = ((SmartFridge) (smartUnit)).getTemperature().toString() + "°C";
//                break;
//            case "SmartAudio":
//                status = smartUnit.getStringStatus();
//                break;
//            case "SmartWashingMachine":
//                status = smartUnit.getStringStatus();
//                break;
//            case "SmartAC":
//                //status = ((SmartAC) smartUnit).getTemperature().toString() + "°C";
//                status= smartUnit.getDisplayStatus();
//                break;
            case "SmartWeatherSystem":
                switch (smartUnit.getName()) {
                    case "Temperature":
                        status = ((SmartWeatherSystem) smartUnit).getTemperature().toString();
                        break;
                    case "Humidity":
                        status = ((SmartWeatherSystem) smartUnit).getHumidity().toString();
                        break;
                    case "Wind speed":
                        status = ((SmartWeatherSystem) smartUnit).getWindSpeed().toString();
                        break;
                    case "Precipitation":
                        status = ((SmartWeatherSystem) smartUnit).getPrecipitations().toString();
                        break;
                }
                break;
            case "SmartPool":
                switch (smartUnit.getName()) {
                    case "Ph":
                        status = ((SmartPool) smartUnit).getPh().toString();
                        break;
                    case "Hardness":
                        status = ((SmartPool) smartUnit).getHardness().toString();
                        break;
                    case "Temperature":
                        status = ((SmartPool) smartUnit).getWaterTemperature().toString();
                        break;
                    case "Alkalinity":
                        status = ((SmartPool) smartUnit).getAlkalinity().toString();
                        break;
                    case "Cover":
                        status = smartUnit.getStringStatus();
                        break;
                }
                break;
            case "SmartYardDoor":
                status = smartUnit.getStringStatus();
                break;
            case "SmartGarageDoor":
                status = smartUnit.getStringStatus();
                break;
            case "SmartOutdoorLights":
                status = smartUnit.getStringStatus();
                break;
            case "SmartSprinklers":
                status = smartUnit.getStringStatus();
                break;
            default:
                status= smartUnit.getDisplayStatus();
                break;

        }
        return status;
    }

    public void setExpAdapter(StatsListAdapter newAdapter) {
        ExpAdapter = newAdapter;
    }

    public void setExpListItems(ArrayList<StatsListHeader> itemsList) {
        ExpListItems = itemsList;
    }

    public void addItem(StatsListHeader newItem) {
        ExpListItems.add(newItem);
    }

    public void addItemsList(ArrayList<StatsListHeader> newItems) {
        ExpListItems.addAll(newItems);
    }

    public StatsListHeader getItem(int index) {
        return (StatsListHeader) ExpListItems.get(index);
    }

    public ArrayList<StatsListHeader> getItemsList(int begin, int end) {
        ArrayList<StatsListHeader> newList = new ArrayList<>();
        int i;

        for (i = begin; i < end; i++)
            newList.add((StatsListHeader) ExpListItems.get(i));

        return newList;
    }

    public StatsListAdapter getExpAdapter() {
        return ExpAdapter;
    }

    public ArrayList<StatsListHeader> getExpListItems() {
        return ExpListItems;
    }

}
