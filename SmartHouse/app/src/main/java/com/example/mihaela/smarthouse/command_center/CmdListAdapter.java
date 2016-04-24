package com.example.mihaela.smarthouse.command_center;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.mihaela.smarthouse.R;


import java.util.ArrayList;

/**
 * Created by Mihaela on 24.04.2016.
 */
public class CmdListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<CmdListHeader> groups;

    public CmdListAdapter(Context context, ArrayList<CmdListHeader> groups) {
        this.context = context;
        this.groups = groups;
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

        CmdListItem child = (CmdListItem) getChild(groupPosition, childPosition);


        if(child.getClass().getSimpleName().equals("CmdButtonListItem")) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context
                        .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.cmd_expandable_list_button_item, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.cmdTitleLabel);
            TextView status = (TextView) convertView.findViewById(R.id.cmdStatusLabel);


            title.setText(child.getClass().getSimpleName());
            //title.setText(child.getTitle().toString());
            status.setText(child.getStatus());

        }

        else
        if(child.getClass().getSimpleName().equals("CmdSwitchListItem")) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context
                        .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.cmd_expandable_list_switch_item, null);
            }
            TextView title = (TextView) convertView.findViewById(R.id.cmdSwitchTitleLabel);

            //title.setText(child.getTitle().toString());
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
