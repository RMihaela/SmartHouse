package com.example.mihaela.smarthouse.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.managers.AlertsManager;
import com.example.mihaela.smarthouse.notifications.NotificationsListItem;

import java.util.ArrayList;
import java.util.List;

/*
Frida
 */

public class PlannerActivity extends AppCompatActivity {
    public static List<PlannerListItem> newPlans = new ArrayList<>();
    public List<PlannerListItem> plannerItemsList = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
        AlertsManager.setContext(this);
        plannerItemsList.addAll(newPlans);

        setListView((ListView) findViewById(R.id.listView));
        getListView().setAdapter(new PlannerListAdapter(this, (ArrayList<PlannerListItem>) getPlannerItemsList()));
    }

    @Override
    public void finish() {
        clearDisabledPlans();
        super.finish();
    }

    private void clearDisabledPlans(){
        List<PlannerListItem> toRemove=new ArrayList<>();
        for (PlannerListItem listItem : newPlans){
            if (!listItem.isStarted()){
                toRemove.add(listItem);
            }
        }
        newPlans.removeAll(toRemove);
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

    public static void addPlan(String title, boolean isActive){
        PlannerListItem listItem = new PlannerListItem(title, isActive);
        newPlans.add(listItem);
    }



}
