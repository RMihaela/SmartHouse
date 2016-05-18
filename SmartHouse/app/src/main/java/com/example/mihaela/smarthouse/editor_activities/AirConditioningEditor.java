package com.example.mihaela.smarthouse.editor_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Switch;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;
import com.example.mihaela.smarthouse.smart_unit.SmartAC;

/**
 * Created by Marian on 5/18/2016.
 */
public class AirConditioningEditor extends AppCompatActivity {
    private static ASmartUnit currentSmartUnit = null;

    NumberPicker temperaturePicker = null;
    NumberPicker intensityPicker = null;
    Switch switchButton = null;

    public static void setSmartUnit(ASmartUnit smartUnit) {
        currentSmartUnit = smartUnit;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);


        temperaturePicker = (NumberPicker) findViewById(R.id.temperaturePicker);
        temperaturePicker.setValue(((SmartAC)currentSmartUnit).getTemperature());
        temperaturePicker.setMaxValue(35);
        temperaturePicker.setMinValue(15);
        temperaturePicker.setWrapSelectorWheel(false);

        intensityPicker = (NumberPicker) findViewById(R.id.pressurePicker);
        intensityPicker.setMaxValue(5);
        intensityPicker.setMinValue(1);
        intensityPicker.setValue(((SmartAC)currentSmartUnit).getIntensity());
        intensityPicker.setWrapSelectorWheel(false);

        switchButton = (Switch) findViewById(R.id.switch1);
        if (currentSmartUnit != null)
            switchButton.setChecked(currentSmartUnit.isStatus());


    }

    public void returnToCmdCenter(View v) {
        ((SmartAC) currentSmartUnit).updateServerData(temperaturePicker.getValue(), intensityPicker.getValue(), switchButton.isChecked());
        CommandCenterActivity.getInstance().updateSmartUnit(currentSmartUnit);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
