package com.example.mihaela.smarthouse.stats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smart_unit.ASmartUnit;
import smart_unit.SmartAudio;
import smart_unit.SmartBlinds;
import smart_unit.SmartCookingMachine;
import smart_unit.SmartFridge;
import smart_unit.SmartGarageDoor;
import smart_unit.SmartIndoorLights;
import smart_unit.SmartOutdoorLights;
import smart_unit.SmartPool;
import smart_unit.SmartSprinklers;
import smart_unit.SmartTV;
import smart_unit.SmartVacuumCleaner;
import smart_unit.SmartWashingMachine;
import smart_unit.SmartWaterSystem;
import smart_unit.SmartWeatherSystem;
import smart_unit.SmartYardDoor;
//Andrei

public class StatsActivity extends AppCompatActivity {

    private StatsListAdapter ExpAdapter;
    private ArrayList<StatsListHeader> ExpListItems = new ArrayList<>();
    private ExpandableListView ExpandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        ExpandList = (ExpandableListView) findViewById(R.id.exp_list);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new StatsListAdapter(StatsActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

    }

    public static ArrayList<StatsListHeader> SetStandardGroups() {
        Map<String, List<ASmartUnit>> outdoorMap = new HashMap<>();
        Map<String, List<ASmartUnit>> indoorMap = new HashMap<>();

        //Kitchen
        List<ASmartUnit> aSmartUnitList = new ArrayList<>();
        ASmartUnit tv = new SmartTV("0d4036", "TV");
        aSmartUnitList.add(tv);
        ASmartUnit blinds = new SmartBlinds("798dd7", "Blinds");
        aSmartUnitList.add(blinds);
        ASmartUnit indoorLights = new SmartIndoorLights("465207", "Lights");
        aSmartUnitList.add(indoorLights);
        ASmartUnit vacuum = new SmartVacuumCleaner("0c6d56", "Vacuum Cleaner");
        aSmartUnitList.add(vacuum);
        ASmartUnit waterSystem = new SmartWaterSystem("05f6e3", "Water System");
        aSmartUnitList.add(waterSystem);
        ASmartUnit cookingMachine = new SmartCookingMachine("796de4", "Cooking Machine");
        aSmartUnitList.add(cookingMachine);
        ASmartUnit fridge = new SmartFridge("77c80f", "Fridge");
        aSmartUnitList.add(fridge);

        indoorMap.put("Kitchen", aSmartUnitList);

        //Bedroom
        aSmartUnitList = new ArrayList<>();
        tv = new SmartTV("2979f7", "TV");
        aSmartUnitList.add(tv);
        blinds = new SmartBlinds("9bb6ed", "Blinds");
        aSmartUnitList.add(blinds);
        indoorLights = new SmartIndoorLights("c303df", "Lights");
        aSmartUnitList.add(indoorLights);
        vacuum = new SmartVacuumCleaner("29073f", "Vaccum Cleaner");
        aSmartUnitList.add(vacuum);

        indoorMap.put("Bedroom", aSmartUnitList);

        //Living
        aSmartUnitList = new ArrayList<>();
        tv = new SmartTV("e1fdf1", "TV");
        aSmartUnitList.add(tv);
        blinds = new SmartBlinds("bca0cf", "Blinds");
        aSmartUnitList.add(blinds);
        indoorLights = new SmartIndoorLights("c6936a", "Lights");
        aSmartUnitList.add(indoorLights);
        vacuum = new SmartVacuumCleaner("7b6c00", "Vaccum Cleaner");
        aSmartUnitList.add(vacuum);
        ASmartUnit audio =  new SmartAudio("8c59e2", "Audio");
        aSmartUnitList.add(audio);

        indoorMap.put("Living", aSmartUnitList);

        //Bathroom
        aSmartUnitList = new ArrayList<>();
        blinds = new SmartBlinds("bce4eb", "Blinds");
        aSmartUnitList.add(blinds);
        indoorLights = new SmartIndoorLights("e6f43f", "Lights");
        aSmartUnitList.add(indoorLights);
        ASmartUnit washingMachine = new SmartWashingMachine("26bd1a", "Washing Machine");
        aSmartUnitList.add(washingMachine);
        waterSystem = new SmartWaterSystem("9bf53d", "Water System");
        aSmartUnitList.add(waterSystem);

        indoorMap.put("Bathroom", aSmartUnitList);

        //Weather
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartWheather = new SmartWeatherSystem("1", "Temperature");
        aSmartUnitList.add(smartWheather);
        smartWheather = new SmartWeatherSystem("2", "Humidity");
        aSmartUnitList.add(smartWheather);
        smartWheather = new SmartWeatherSystem("3", "Wind speed");
        aSmartUnitList.add(smartWheather);
        smartWheather = new SmartWeatherSystem("4", "Precipitation");
        aSmartUnitList.add(smartWheather);

        outdoorMap.put("Weather", aSmartUnitList);

        //Pool
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartPool = new SmartPool("5", "Ph");
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("6", "Hardness");
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("7", "Temperature");
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("8","Alkalinity");
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("9","Cover");
        aSmartUnitList.add(smartPool);

        outdoorMap.put("Pool", aSmartUnitList);

        //Yard
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartYard = new SmartYardDoor("10", "Yard door");
        aSmartUnitList.add(smartYard);
        smartYard = new SmartGarageDoor("11", "Garage door");
        aSmartUnitList.add(smartYard);
        smartYard = new SmartOutdoorLights("12", "Lights");
        aSmartUnitList.add(smartYard);
        smartYard = new SmartSprinklers("13", "Sprinklers");
        aSmartUnitList.add(smartYard);

        outdoorMap.put("Yard",aSmartUnitList);

        //Garden
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartGarden = new SmartSprinklers("14", "Sprinklers");
        aSmartUnitList.add(smartGarden);

        outdoorMap.put("Garden", aSmartUnitList);

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
                String className = smartUnit.getClass().getSimpleName();
                String status=new String();

                switch (className){
                    case "SmartWeatherSystem":
                        switch (smartUnit.getName()){
                            case "Temperature":
                                status = ((SmartWeatherSystem)smartUnit).getTemperature().toString();
                                break;
                            case "Humidity":
                                status = ((SmartWeatherSystem)smartUnit).getHumidity().toString();
                                break;
                            case "Wind speed":
                                status = ((SmartWeatherSystem)smartUnit).getWindSpeed().toString();
                                break;
                            case "Precipitation":
                                status = ((SmartWeatherSystem)smartUnit).getPrecipitations().toString();
                                break;
                        }
                        break;
                    case "SmartPool":
                        switch (smartUnit.getName()) {
                            case "Ph":
                                status = ((SmartPool)smartUnit).getPh().toString();
                                break;
                            case "Hardness":
                                status = ((SmartPool)smartUnit).getHardness().toString();
                                break;
                            case "Temperature":
                                status = ((SmartPool)smartUnit).getWaterTemperature().toString();
                                break;
                            case "Alkalinity":
                                status = ((SmartPool)smartUnit).getAlkalinity().toString();
                                break;
                            case "Cover":
                                status = smartUnit.getStringStatus();
                                break;
                        }
                        break;
                    case "SmartYardDoor" :
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartGarageDoor" :
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartOutdoorLights" :
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartSprinklers" :
                        status=smartUnit.getStringStatus();
                        break;
                }
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
                String className = smartUnit.getClass().getSimpleName();
                String status=new String();
                switch (className){
                    case "SmartTV":
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartBlinds" :
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartIndoorLights":
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartVacuumCleaner":
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartWaterSystem":
                        status=((SmartWaterSystem)(smartUnit)).getTemperature().toString()+"°C";
                        break;
                    case "SmartCookingMachine":
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartFridge":
                        status=((SmartFridge)(smartUnit)).getTemperature().toString()+"°C";
                        break;
                    case "SmartAudio" :
                        status=smartUnit.getStringStatus();
                        break;
                    case "SmartWashingMachine":
                        status=smartUnit.getStringStatus();
                        break;
                }

                ch.setStatus(status);
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);
        }

        return list;
    }

    public void setExpAdapter(StatsListAdapter newAdapter){
        ExpAdapter = newAdapter;
    }

    public void setExpListItems(ArrayList<StatsListHeader> itemsList){
        ExpListItems = itemsList;
    }

    public void addItem(StatsListHeader newItem){
        ExpListItems.add(newItem);
    }

    public void addItemsList(ArrayList<StatsListHeader> newItems){
        ExpListItems.addAll(newItems);
    }

    public StatsListHeader getItem(int index){
        return (StatsListHeader)ExpListItems.get(index);
    }

    public ArrayList<StatsListHeader> getItemsList(int begin, int end){
        ArrayList<StatsListHeader> newList = new ArrayList<>();
        int i;

        for(i = begin; i<end;i++)
            newList.add((StatsListHeader)ExpListItems.get(i));

        return newList;
    }

    public StatsListAdapter getExpAdapter(){
        return ExpAdapter;
    }

    public ArrayList<StatsListHeader> getExpListItems(){
        return ExpListItems;
    }

}
