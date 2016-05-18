package com.example.mihaela.smarthouse.command_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import com.example.mihaela.smarthouse.R;

/**
 * Created by Marian on 5/15/2016.
 */
public class NumberPickerActivity extends AppCompatActivity {

    NumberPicker numPick = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        this.setupPicker();
    }

    private void setupPicker(){
        numPick = (NumberPicker)findViewById(R.id.numberPicker);
        numPick.setMaxValue(40);
        numPick.setMinValue(0);
        numPick.setWrapSelectorWheel(false);
    }

    public void returnToCmdCenter(View v){
        String result = ""+numPick.getValue();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
