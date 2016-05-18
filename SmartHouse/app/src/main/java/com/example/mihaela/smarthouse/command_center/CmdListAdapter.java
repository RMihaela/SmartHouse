package com.example.mihaela.smarthouse.command_center;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.managers.WebServiceManager;
import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mihaela on 24.04.2016.
 */
public class CmdListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<CmdListHeader> groups = new ArrayList<>();

    public CmdListAdapter(Context context, ArrayList<CmdListHeader> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }
    /*
    type 0 == CmdButtonListItem
         1 == CmdSwitchListItem
     */
    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (getChild(groupPosition, childPosition).getClass().getSimpleName().equals("CmdButtonListItem"))
            return 0;
        return 1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<CmdListItem> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        int childType = getChildType(groupPosition, childPosition);

        final CmdListItem child = (CmdListItem) getChild(groupPosition, childPosition);

        if (childType == 0) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context
                        .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.cmd_expandable_list_button_item, null);
            }
            TextView titleTextView = (TextView) convertView.findViewById(R.id.cmdTitleLabel);
            TextView statusTextView = (TextView) convertView.findViewById(R.id.cmdStatusLabel);

//            Log.d("CREATION", "TITLE: " + child.getTitle().toString() + " STATUS: " + statusTextView.toString());
            titleTextView.setText(child.getTitle().toString());
            statusTextView.setText(child.getStatus());

        } else {
            if (child.getClass().getSimpleName().equals("CmdSwitchListItem")) {
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) context
                            .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.cmd_expandable_list_switch_item, null);
                }
                TextView title = (TextView) convertView.findViewById(R.id.cmdSwitchTitleLabel);
                title.setText(child.getTitle().toString());
                final Switch switchButton = (Switch) convertView.findViewById(R.id.cmdSwitchButton);
                switchButton.setChecked(child.getStatus().equalsIgnoreCase("on"));
                switchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        child.setStatus(switchButton.isChecked() ? "ON" : "OFF");
                        ASmartUnit smUnit = child.getSmartUnit();
                        smUnit.updateServerData(switchButton.isChecked());

                    }
                });

            }
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<CmdListItem> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        CmdListHeader group = (CmdListHeader) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.stats_expandable_list_header, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.titleLabel);
        tv.setText(group.getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
