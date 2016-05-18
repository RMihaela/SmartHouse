package com.example.mihaela.smarthouse.command_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian on 5/18/2016.
 */
public class OptionAndValuePicker extends AppCompatActivity {

    Spinner opts = null;
    NumberPicker numPick = null;
    String option = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_and_val_picker);

        opts = (Spinner) findViewById(R.id.optSpinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Option0");
        categories.add("Option1");
        categories.add("Option2");
        categories.add("Option3");
        categories.add("Option4");
        categories.add("Option5");

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
        numPick.setMaxValue(90);
        numPick.setMinValue(0);
        numPick.setWrapSelectorWheel(false);

    }

    public void returnToCmdCenter(View v){
        String result = ""+numPick.getValue();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("option", option);
        returnIntent.putExtra("value", result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
