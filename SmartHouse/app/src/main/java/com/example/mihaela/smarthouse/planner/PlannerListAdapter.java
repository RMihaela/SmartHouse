package com.example.mihaela.smarthouse.planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;

/**
 * Created by Mihaela on 17.04.2016.
 */
public class PlannerListAdapter extends BaseAdapter {

    Context context;
    ArrayList<PlannerListItem> plannerItemsList;
    private static LayoutInflater inflater = null;

    public PlannerListAdapter(Context context, ArrayList<PlannerListItem> plannerItemsList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.plannerItemsList = plannerItemsList;
        setInflater((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public static LayoutInflater getInflater() {
        return inflater;
    }

    public static void setInflater(LayoutInflater inflater) {
        PlannerListAdapter.inflater = inflater;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return plannerItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return plannerItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = getInflater().inflate(R.layout.planner_list_item, null);

        PlannerListItem plannerItem = plannerItemsList.get(position);

        TextView title = (TextView) vi.findViewById(R.id.title);
        title.setText(plannerItem.getTitle());
        Switch switchButton = (Switch) vi.findViewById(R.id.switchButton);
        switchButton.setChecked(plannerItem.isStarted());
        return vi;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
