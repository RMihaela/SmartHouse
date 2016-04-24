package com.example.mihaela.smarthouse.stats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    private StatsListAdapter ExpAdapter;
    private ArrayList<StatsListHeader> ExpListItems;
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

    public ArrayList<StatsListHeader> SetStandardGroups() {

        String group_names[] = { "Room1", "Room2", "Room3", "Room4", "Room5" };

        String country_names[] = { "Sensor1", "Sensor2", "Sensor3", "Sensor4" };

        ArrayList<StatsListHeader> list = new ArrayList<StatsListHeader>();

        ArrayList<StatsListItem> ch_list;

        int size = 4;

        for (String group_name : group_names) {
            int j = 0;
            StatsListHeader gru = new StatsListHeader();
            gru.setName(group_name);

            ch_list = new ArrayList<StatsListItem>();
            for (; j < size; j++) {
                StatsListItem ch = new StatsListItem();
                ch.setTitle(country_names[j]);
                ch.setStatus("status");
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);
        }

        return list;
    }

}
