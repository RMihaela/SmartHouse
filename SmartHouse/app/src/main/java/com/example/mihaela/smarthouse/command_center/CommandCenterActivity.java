package com.example.mihaela.smarthouse.command_center;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.mihaela.smarthouse.R;
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
import com.example.mihaela.smarthouse.stats.StatsListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Marian

public class CommandCenterActivity extends AppCompatActivity {
    private static CommandCenterActivity instance = null;

    public static CommandCenterActivity getInstance(){
        return instance;
    }

    private CmdListAdapter ExpAdapter;
    private ArrayList<CmdListHeader> ExpListItems;
    private ExpandableListView ExpandList;
<<<<<<< HEAD
    Map<String, List<ASmartUnit>> outdoorMap = new HashMap<>();
    Map<String, List<ASmartUnit>> indoorMap = new HashMap<>();
=======
    public String name = "testare";
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_command_center);

        ExpandList = (ExpandableListView) findViewById(R.id.cmd_exp_list);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new CmdListAdapter(CommandCenterActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
    }

    //TODO: dynamically populate lists
    public ArrayList<CmdListHeader> SetStandardGroups(){

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
        ASmartUnit ac=new SmartAC("29293b","AC",this.getApplicationContext());
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
        ac=new SmartAC("936378","AC",this.getApplicationContext());
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
        ASmartUnit audio =  new SmartAudio("8c59e2", "Audio", this.getApplicationContext());
        aSmartUnitList.add(audio);
        ac=new SmartAC("e04e63","AC",this.getApplicationContext());
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
        smartPool = new SmartPool("8","Alkalinity", this.getApplicationContext());
        aSmartUnitList.add(smartPool);
        smartPool = new SmartPool("9","Cover", this.getApplicationContext());
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

        outdoorMap.put("Yard",aSmartUnitList);

        //Garden
        aSmartUnitList = new ArrayList<>();
        ASmartUnit smartGarden = new SmartSprinklers("14", "Sprinklers", this.getApplicationContext());
        aSmartUnitList.add(smartGarden);

        outdoorMap.put("Garden", aSmartUnitList);

        ArrayList<CmdListHeader> list =new ArrayList<>();

        ArrayList<CmdListItem> ch_list=new ArrayList<>();


        for(String group_name:outdoorMap.keySet()){
            int j=0;
            CmdListHeader gru=new CmdListHeader();
            gru.setName(group_name);

<<<<<<< HEAD

            ch_list=new ArrayList<CmdListItem>();

            for (; j < outdoorMap.get(group_name).size(); j++) {
                ASmartUnit smartUnit = outdoorMap.get(group_name).get(j);
                String className = smartUnit.getClass().getSimpleName();
                String status=new String();
                String title=new String();
                CmdListItem ch=null;
                switch (className){
                    case "SmartWeatherSystem":
                        switch (smartUnit.getName()){
                            case "Temperature":
                                status = ((SmartWeatherSystem)smartUnit).getTemperature().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                            case "Humidity":
                                status = ((SmartWeatherSystem)smartUnit).getHumidity().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                            case "Wind speed":
                                status = ((SmartWeatherSystem)smartUnit).getWindSpeed().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                            case "Precipitation":
                                status = ((SmartWeatherSystem)smartUnit).getPrecipitations().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                        }
                        break;
                    case "SmartPool":
                        switch (smartUnit.getName()) {
                            case "Ph":
                                status = ((SmartPool)smartUnit).getPh().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                            case "Hardness":
                                status = ((SmartPool)smartUnit).getHardness().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                            case "Temperature":
                                status = ((SmartPool)smartUnit).getWaterTemperature().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                            case "Alkalinity":
                                status = ((SmartPool)smartUnit).getAlkalinity().toString();
                                ch=new CmdButtonListItem();
                                ch.setStatus(status);
                                title=smartUnit.getName();
                                break;
                            case "Cover":
                                status = smartUnit.getStringStatus();
                                ch=new CmdSwitchListItem();
                                title=smartUnit.getName();
                                break;
                        }
                        break;
                    case "SmartYardDoor" :
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                    case "SmartGarageDoor" :
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                    case "SmartOutdoorLights" :
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();

                        title=smartUnit.getName();
                        break;
                    case "SmartSprinklers" :
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                }
                ch.setTitle(title);
                ch.setSmartUnit(smartUnit);
                ch.setId(smartUnit.getId());
=======
            ArrayList<CmdListItem> ch_list = new ArrayList<CmdListItem>();
            ch_list.clear();
            for(int j = 0; j < size; j++){
                CmdListItem ch = new CmdButtonListItem();
                ch.setTitle(item_names[j]);
                ch.setStatus("status");
                ch.setIndex(j);
>>>>>>> origin/master
                ch_list.add(ch);

            }
            gru.setItems(ch_list);
            list.add(gru);
        }

        for(String group_name:indoorMap.keySet()){
            int j=0;
            CmdListHeader gru=new CmdListHeader();
            gru.setName(group_name);


            ch_list=new ArrayList<CmdListItem>();

            for (; j < indoorMap.get(group_name).size(); j++) {
                ASmartUnit smartUnit = indoorMap.get(group_name).get(j);
                String className = smartUnit.getClass().getSimpleName();
                String status=new String();
                String title=new String();
                CmdListItem ch=null;
                switch (className){
                    case "SmartTV":
                        status=((SmartTV)smartUnit).getChannel();
                        ch=new CmdButtonListItem();
                        ch.setStatus(status);
                        title=smartUnit.getName();

                        break;
                    case "SmartBlinds" :
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                    case "SmartIndoorLights":
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                    case "SmartVacuumCleaner":
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                    case "SmartWaterSystem":
                        status=((SmartWaterSystem)(smartUnit)).getTemperature().toString()+"°C";
                        ch=new CmdButtonListItem();
                        ch.setStatus(status);
                        title=smartUnit.getName();
                        break;
                    case "SmartCookingMachine":
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                    case "SmartFridge":
                        status=((SmartFridge)(smartUnit)).getTemperature().toString()+"°C";
                        ch=new CmdButtonListItem();
                        ch.setStatus(status);
                        title=smartUnit.getName();
                        break;
                    case "SmartAudio" :
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                        title=smartUnit.getName();
                        break;
                    case "SmartWashingMachine":
                        status=smartUnit.getStringStatus();
                        ch=new CmdSwitchListItem();
                   
                        title=smartUnit.getName();
                        break;
                    case "SmartAC":
                        status=((SmartAC)smartUnit).getTemperature().toString()+"°C";
                        ch=new CmdButtonListItem();
                        ch.setStatus(status);
                        title=smartUnit.getName();
                        break;
                }
                ch.setSmartUnit(smartUnit);
                ch.setTitle(title);
                ch_list.add(ch);
                ch.setId(smartUnit.getId());


            }
            gru.setItems(ch_list);
            list.add(gru);
        }

        for (CmdListHeader head:list){
            Log.d("CREATION", head.getName() );
            for(CmdListItem item: head.getItems()){
                Log.d("CREATION", item.getTitle()+" "+item.getClass().getSimpleName() );
            }
        }

        return list;
    }

    public void updateSmartUnit(ASmartUnit smartUnit) {
        for(CmdListHeader head:ExpListItems)
            for(CmdListItem item:head.getItems()){
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

    public void switchButtonClicked(View view)
    {
        Log.i("SwitchPressed", view.toString());
    }

    public String getStatusForSmartUnit(ASmartUnit smartUnit){
        String className = smartUnit.getClass().getSimpleName();
        String status = new String();
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
            case "SmartTV":
                status= smartUnit.getDisplayStatus();
                break;
            case "SmartBlinds" :
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartIndoorLights":
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartVacuumCleaner":
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartWaterSystem":
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartCookingMachine":
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartFridge":
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartAudio" :
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartWashingMachine":
                status= smartUnit.getDisplayStatus();

                break;
            case "SmartAC":
                status= smartUnit.getDisplayStatus();

                break;
        }
        return status;
    }

    public void setExpAdapter(CmdListAdapter newAdapter){
        ExpAdapter = newAdapter;
    }

    public void setExpListItems(ArrayList<CmdListHeader> newItems){
        ExpListItems = newItems;
    }

    public void addItem(CmdListHeader item){
        ExpListItems.add(item);
    }

    public void addItemList(ArrayList<CmdListHeader> itemList){
        ExpListItems.addAll(itemList);
    }

    public CmdListAdapter getExpAdapter(){
        return ExpAdapter;
    }

    public ArrayList<CmdListHeader> getExpListItems(){
        return ExpListItems;
    }

    public CmdListHeader getItem(int index){
        return (CmdListHeader)ExpListItems.get(index);
    }

    public ArrayList<CmdListHeader> getItems(int begin, int end){
        ArrayList<CmdListHeader> temp = new ArrayList<>();
        int i;

        for(i = begin; i<end; i++)
            temp.add((CmdListHeader)ExpListItems.get(i));

        return temp;
    }

    public int getIndexOf(CmdListHeader item){
        if(ExpListItems.contains(item))
            return ExpListItems.indexOf(item);
        return -1;
    }

    public void onClick(View v){

        Intent intent = new Intent(this, OptionAndValuePicker.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String option=data.getStringExtra("option");
                String value=data.getStringExtra("value");
                Log.i("Result", "Option "+option+" with value "+value);
            }
        }
    }

}
