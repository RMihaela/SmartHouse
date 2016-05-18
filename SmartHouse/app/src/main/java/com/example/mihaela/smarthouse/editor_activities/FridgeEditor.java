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
import com.example.mihaela.smarthouse.smart_unit.SmartCookingMachine;
import com.example.mihaela.smarthouse.smart_unit.SmartFridge;

/**
 * Created by Marian on 5/18/2016.
 */
public class FridgeEditor extends AppCompatActivity {
    private static ASmartUnit currentSmartUnit = null;

    NumberPicker numPick = null;

    Switch switchButton = null;

    public static void setSmartUnit(ASmartUnit smartUnit){
        currentSmartUnit = smartUnit;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        numPick = (NumberPicker)findViewById(R.id.numberPicker);
        numPick.setMaxValue(10);
        numPick.setMinValue(1);
        numPick.setWrapSelectorWheel(false);

        switchButton = (Switch)findViewById(R.id.mainSwitch);
        if (currentSmartUnit != null)
            switchButton.setChecked(currentSmartUnit.isStatus());


    }

    public void returnToCmdCenter(View v){
        ((SmartFridge)currentSmartUnit).updateServerData(numPick.getValue(),switchButton.isChecked());
        CommandCenterActivity.getInstance().updateSmartUnit(currentSmartUnit);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
