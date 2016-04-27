package com.example.mihaela.smarthouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.notifications.NotificationsActivity;
import com.example.mihaela.smarthouse.planner.PlannerActivity;
import com.example.mihaela.smarthouse.stats.StatsActivity;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    public void openStats(View v){
        startActivity(new Intent(HomeScreen.this, StatsActivity.class));
    }

    public void openCommandCenter(View v){
        startActivity(new Intent(HomeScreen.this, CommandCenterActivity.class));
    }

    public void openPlanner(View v){
        startActivity(new Intent(HomeScreen.this, PlannerActivity.class));
    }

    public void openNotifications(View v){
        startActivity(new Intent(HomeScreen.this, NotificationsActivity.class));
    }
}
