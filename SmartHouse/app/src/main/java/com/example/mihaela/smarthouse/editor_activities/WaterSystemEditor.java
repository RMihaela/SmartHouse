package com.example.mihaela.smarthouse.editor_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;
import com.example.mihaela.smarthouse.smart_unit.SmartAC;
import com.example.mihaela.smarthouse.smart_unit.SmartWaterSystem;

/**
 * Created by Marian on 5/18/2016.
 */
public class WaterSystemEditor extends AppCompatActivity {
    private static ASmartUnit currentSmartUnit = null;

    NumberPicker temperaturePicker = null;
    NumberPicker pressurePicker = null;

    public static void setSmartUnit(ASmartUnit smartUnit) {
        currentSmartUnit = smartUnit;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_systems);


        temperaturePicker = (NumberPicker) findViewById(R.id.temperaturePicker);
        temperaturePicker.setValue(((SmartWaterSystem)currentSmartUnit).getTemperature());
        temperaturePicker.setMaxValue(60);
        temperaturePicker.setMinValue(10);
        temperaturePicker.setWrapSelectorWheel(false);

        pressurePicker = (NumberPicker) findViewById(R.id.pressurePicker);
        pressurePicker.setMaxValue(5);
        pressurePicker.setMinValue(1);
        pressurePicker.setValue(((SmartWaterSystem)currentSmartUnit).getPressure());
        pressurePicker.setWrapSelectorWheel(false);


    }

    public void returnToCmdCenter(View v) {
        ((SmartWaterSystem) currentSmartUnit).updateServerData(temperaturePicker.getValue(), pressurePicker.getValue());
        CommandCenterActivity.getInstance().updateSmartUnit(currentSmartUnit);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
