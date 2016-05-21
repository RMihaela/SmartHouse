package com.example.mihaela.smarthouse.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.managers.AlertsManager;

import java.util.ArrayList;
import java.util.List;

/*
Frida
 */

public class PlannerActivity extends AppCompatActivity {
    private List<PlannerListItem> plannerItemsList = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
        AlertsManager.setContext(this);
        getPlannerItemsList().add(new PlannerListItem("Plan 0", true));
        getPlannerItemsList().add(new PlannerListItem("Plan 1", true));
        getPlannerItemsList().add(new PlannerListItem("Plan 2", false));
        getPlannerItemsList().add(new PlannerListItem("Plan 3", true));

        setListView((ListView) findViewById(R.id.listView));
        getListView().setAdapter(new PlannerListAdapter(this, (ArrayList<PlannerListItem>) getPlannerItemsList()));
    }

    public List<PlannerListItem> getPlannerItemsList() {
        return plannerItemsList;
    }

    public void setPlannerItemsList(List<PlannerListItem> plannerItemsList) {
        this.plannerItemsList = plannerItemsList;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void addPlanerItem(PlannerListItem plannItemList){
        this.plannerItemsList.add(plannItemList);
    }



}
