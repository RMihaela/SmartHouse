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

        String group_names[] = { "GROUP A", "GROUP B", "GROUP C", "GROUP D",
                "GROUP E", "GROUP F", "GROUP G", "GROUP H" };

        String country_names[] = { "Brazil", "Mexico", "Croatia", "Cameroon",
                "Netherlands", "chile", "Spain", "Australia", "Colombia",
                "Greece", "Ivory Coast", "Japan", "Costa Rica", "Uruguay",
                "Italy", "England", "France", "Switzerland", "Ecuador",
                "Honduras", "Agrentina", "Nigeria", "Bosnia and Herzegovina",
                "Iran", "Germany", "United States", "Portugal", "Ghana",
                "Belgium", "Algeria", "Russia", "Korea Republic" };

        ArrayList<StatsListHeader> list = new ArrayList<StatsListHeader>();

        ArrayList<StatsListItem> ch_list;

        int size = 4;
        int j = 0;

        for (String group_name : group_names) {
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

            size = size + 4;
        }

        return list;
    }

}
