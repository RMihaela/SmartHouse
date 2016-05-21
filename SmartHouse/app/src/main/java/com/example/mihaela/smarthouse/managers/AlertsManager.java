package com.example.mihaela.smarthouse.managers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by silviu on 21-May-16.
 */
public class AlertsManager {

    private static Context context;

    public static void displayAlert(String alertMessage){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(alertMessage)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AlertsManager.context = context;
    }
}
