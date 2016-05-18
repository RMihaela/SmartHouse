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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian on 5/18/2016.
 */
public class TvEditor extends AppCompatActivity {
    private static ASmartUnit currentSmartUnit = null;
    Spinner opts = null;
    NumberPicker numPick = null;
    String option = null;
    Switch switchButton = null;

    public static void setSmartUnit(ASmartUnit smartUnit){
        currentSmartUnit = smartUnit;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        opts = (Spinner) findViewById(R.id.optSpinner);
        List<String> categories = new ArrayList<String>();
        categories.add("MTV");
        categories.add("KissTV");
        categories.add("TVR1");
        categories.add("AniMax");
        categories.add("Discovery");
        categories.add("Eurosport");

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
        numPick.setMaxValue(40);
        numPick.setMinValue(0);
        numPick.setWrapSelectorWheel(false);

        switchButton = (Switch)findViewById(R.id.switch1);
        if (currentSmartUnit != null)
            switchButton.setChecked(currentSmartUnit.isStatus());


    }

    public void returnToCmdCenter(View v){

        String result = ""+numPick.getValue();
        ((SmartTV)currentSmartUnit).updateServerData(Integer.parseInt(result),option,switchButton.isChecked());
        CommandCenterActivity.getInstance().updateSmartUnit(currentSmartUnit);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("option", option);
        returnIntent.putExtra("value", result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
