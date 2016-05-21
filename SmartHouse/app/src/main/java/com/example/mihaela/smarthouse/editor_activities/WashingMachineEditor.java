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
import com.example.mihaela.smarthouse.smart_unit.SmartTV;
import com.example.mihaela.smarthouse.smart_unit.SmartWashingMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian on 5/18/2016.
 */
public class WashingMachineEditor extends AppCompatActivity {
    private static ASmartUnit currentSmartUnit = null;

    Spinner opts = null;
    NumberPicker numPick = null;
    String option = null;
    Switch switchButton = null;
    String[] pickerValues = new String[]{"0","300","600","800","1000"};

    public static void setSmartUnit(ASmartUnit smartUnit){
        currentSmartUnit = smartUnit;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washing_machine);

        opts = (Spinner) findViewById(R.id.optSpinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Silk");
        categories.add("Wool");
        categories.add("Quick");
        categories.add("Coloured");
        categories.add("Synthetic");
        categories.add("Cotton");
        categories.add("Dry");
        categories.add("Delicate");
        categories.add("Eco");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opts.setAdapter(dataAdapter);
        option = opts.getItemAtPosition(0).toString();
        opts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                option = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        numPick = (NumberPicker)findViewById(R.id.numberPickerOpt);
        numPick.setMaxValue(4);
        numPick.setMinValue(0);
        numPick.setDisplayedValues(pickerValues);
        numPick.setWrapSelectorWheel(false);

        switchButton = (Switch)findViewById(R.id.switch1);
        if (currentSmartUnit != null)
            switchButton.setChecked(currentSmartUnit.isStatus());


    }

    public void returnToCmdCenter(View v){

        int result = Integer.parseInt(pickerValues[numPick.getValue()]);
        ((SmartWashingMachine)currentSmartUnit).updateServerData(result,option,switchButton.isChecked());
        CommandCenterActivity.getInstance().updateSmartUnit(currentSmartUnit);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
