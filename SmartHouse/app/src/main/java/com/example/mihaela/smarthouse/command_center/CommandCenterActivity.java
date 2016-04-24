package com.example.mihaela.smarthouse.command_center;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;

public class CommandCenterActivity extends AppCompatActivity {
    private CmdListAdapter ExpAdapter;
    private ArrayList<CmdListHeader> ExpListItems;
    private ExpandableListView ExpandList;

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

        ArrayList<CmdSpinnerListItem> ch_list;

        int size = 4;

        for(String group_name : group_names){
            int j = 0;
            CmdListHeader gru = new CmdListHeader();
            gru.setName(group_name);

            ch_list = new ArrayList<CmdSpinnerListItem>();
            for(; j < size; j++){
                CmdSpinnerListItem ch = new CmdSpinnerListItem();
                ch.setTitle(item_names[j]);
                ch.setStatus(20);
                ch_list.add(ch);
            }

            gru.setItems(ch_list);
            list.add(gru);

        }

        return list;
    }
}
