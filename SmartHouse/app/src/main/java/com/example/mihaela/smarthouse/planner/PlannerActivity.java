package com.example.mihaela.smarthouse.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;
import java.util.List;

public class PlannerActivity extends AppCompatActivity {
    List<PlannerListItem> plannerItemsList = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        plannerItemsList.add(new PlannerListItem("Plan 0", true));
        plannerItemsList.add(new PlannerListItem("Plan 1", true));
        plannerItemsList.add(new PlannerListItem("Plan 2", false));
        plannerItemsList.add(new PlannerListItem("Plan 3", true));

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new PlannerListAdapter(this, (ArrayList<PlannerListItem>)plannerItemsList));
    }
}
