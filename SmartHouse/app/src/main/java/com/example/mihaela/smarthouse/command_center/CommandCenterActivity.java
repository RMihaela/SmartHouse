package com.example.mihaela.smarthouse.command_center;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;
//Marian

public class CommandCenterActivity extends AppCompatActivity {
    private CmdListAdapter ExpAdapter;
    private ArrayList<CmdListHeader> ExpListItems;
    private ExpandableListView ExpandList;
    public String name = "testare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_center);

        ExpandList = (ExpandableListView) findViewById(R.id.cmd_exp_list);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new CmdListAdapter(CommandCenterActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
    }

    //TODO: dynamically populate lists
    public ArrayList<CmdListHeader> SetStandardGroups(){
        String group_names[] = {"Room1", "Room2", "Room3", "Room4"};

        String item_names[] = {"SmartUnit1", "SmartUnit2", "SmartUnit3", "SmartUnit4"};

        ArrayList<CmdListHeader> list = new ArrayList<>();

       // ArrayList<CmdListItem> ch_list;

        int size = 4;

        for(String group_name : group_names){
            //int j = 0;
            CmdListHeader gru = new CmdListHeader();
            gru.setName(group_name);

            ArrayList<CmdListItem> ch_list = new ArrayList<CmdListItem>();
            ch_list.clear();
            for(int j = 0; j < size; j++){
                CmdListItem ch = new CmdButtonListItem();
                ch.setTitle(item_names[j]);
                ch.setStatus("status");
                ch.setIndex(j);
                ch_list.add(ch);
            }

            for(int i = 0; i < 3; i++){
                CmdListItem chs = new CmdSwitchListItem();
                chs.setTitle(item_names[i]);
                ch_list.add(chs);
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
