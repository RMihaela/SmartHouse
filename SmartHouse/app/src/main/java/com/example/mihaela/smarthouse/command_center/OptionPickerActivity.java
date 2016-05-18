package com.example.mihaela.smarthouse.command_center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mihaela.smarthouse.R;

/**
 * Created by Marian on 5/18/2016.
 */
public class OptionPickerActivity extends AppCompatActivity {

    ListView list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_picker);

        String[] options = {"option1", "option2", "option3", "option4", "option5", "option6", "option7"};

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.opt_list_item, options);

        list=(ListView)findViewById(R.id.listViewOptPicker);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=(String)parent.getItemAtPosition(position);
                Log.i("Opt List","item click detected on item:"+position+" with id "+id+" with option "+data);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",data);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
