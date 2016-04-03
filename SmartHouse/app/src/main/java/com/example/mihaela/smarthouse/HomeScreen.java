package com.example.mihaela.smarthouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    public void openStats(View v){
        startActivity(new Intent(HomeScreen.this, Stats.class));
    }

    public void openCommandCenter(View v){
        startActivity(new Intent(HomeScreen.this, CommandCenter.class));
    }

    public void openPlanner(View v){
        startActivity(new Intent(HomeScreen.this, Planner.class));
    }

    public void openNotifications(View v){
        startActivity(new Intent(HomeScreen.this, Notifications.class));
    }
}
