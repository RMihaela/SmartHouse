package com.example.mihaela.smarthouse.home_activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.machine_learning.MachineLearningMain;
import com.example.mihaela.smarthouse.managers.AlertsManager;
import com.example.mihaela.smarthouse.notifications.NotificationsActivity;
import com.example.mihaela.smarthouse.notifications.indoorprocessing.DataAnalyser;
import com.example.mihaela.smarthouse.planner.PlannerActivity;
import com.example.mihaela.smarthouse.stats.StatsActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeScreen extends AppCompatActivity {
    public static HomeScreen instance;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_home_screen);
        AlertsManager.setContext(this);
        NotificationsActivity.initNewNotifList();
        MachineLearningMain ml = new MachineLearningMain();
        ml.startNeuralNetworks(this);
        DataAnalyser dataAn = new DataAnalyser();
        dataAn.setContext(this.getApplicationContext());
        dataAn.loop(30000); //1 min timer

    }




    @Override
    protected void onResume() {
        super.onResume();
        AlertsManager.setContext(this);
    }


    public void openStats(View v) {
        startActivity(new Intent(HomeScreen.this, StatsActivity.class));
    }

    public void openCommandCenter(View v) {
        startActivity(new Intent(HomeScreen.this, CommandCenterActivity.class));
    }

    public void openPlanner(View v) {
        startActivity(new Intent(HomeScreen.this, PlannerActivity.class));
    }

    public void openNotifications(View v) {
        startActivity(new Intent(HomeScreen.this, NotificationsActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "HomeScreen Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.example.mihaela.smarthouse.home_activity/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "HomeScreen Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.example.mihaela.smarthouse.home_activity/http/host/path")
//        );
////        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }
}
