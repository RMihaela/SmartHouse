package com.example.mihaela.smarthouse.editor_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.command_center.CommandCenterActivity;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;
import com.example.mihaela.smarthouse.smart_unit.SmartCookingMachine;
import com.example.mihaela.smarthouse.smart_unit.SmartTV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian on 5/18/2016.
 */
public class CookingMachineEditor extends AppCompatActivity {
    private static ASmartUnit currentSmartUnit = null;

    NumberPicker numPick = null;

    Switch switchButton = null;

    public static void setSmartUnit(ASmartUnit smartUnit){
        currentSmartUnit = smartUnit;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_machine);

        numPick = (NumberPicker)findViewById(R.id.numberPicker);
        numPick.setMaxValue(220);
        numPick.setMinValue(100);
        numPick.setWrapSelectorWheel(false);

        switchButton = (Switch)findViewById(R.id.mainSwitch);
        if (currentSmartUnit != null)
            switchButton.setChecked(currentSmartUnit.isStatus());


    }

    public void returnToCmdCenter(View v){
        ((SmartCookingMachine)currentSmartUnit).updateServerData(numPick.getValue(),switchButton.isChecked());
        CommandCenterActivity.getInstance().updateSmartUnit(currentSmartUnit);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
