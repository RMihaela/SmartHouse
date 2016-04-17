package com.example.mihaela.smarthouse.stats;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mihaela.smarthouse.R;

public class StatsListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<StatsListHeader> groups;

    public StatsListAdapter(Context context, ArrayList<StatsListHeader> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<StatsListItem> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        StatsListItem child = (StatsListItem) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.stats_expandable_list_item, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.titleLabel);
        TextView status = (TextView) convertView.findViewById(R.id.statusLabel);

        title.setText(child.getTitle().toString());
        status.setText(child.getStatus().toString());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<StatsListItem> chList = groups.get(groupPosition).getItems();
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
        StatsListHeader group = (StatsListHeader) getGroup(groupPosition);
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
