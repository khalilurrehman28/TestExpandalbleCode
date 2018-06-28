package com.example.khalilurrehman.testexpandalblecode.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.khalilurrehman.testexpandalblecode.Models.FrameSub;
import com.example.khalilurrehman.testexpandalblecode.Models.FrameworkDataLs;
import com.example.khalilurrehman.testexpandalblecode.R;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private List<FrameworkDataLs> frameworkDataLs;
    private HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap;
    private Context context;

    public CustomExpandableListAdapter(Context context, List<FrameworkDataLs> frameworkDataLs, HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap){
        this.context = context;
        this.frameworkDataLs = frameworkDataLs;
        this.frameworkDataLsListHashMap = frameworkDataLsListHashMap;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.frameworkDataLsListHashMap.get(this.frameworkDataLs.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        FrameSub childTextClass =  (FrameSub) getChild(groupPosition, childPosition);
        final String childText = childTextClass.getFRAMEWORKSUB();
        TextView txtListChild;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child, null);

        }else{

        }

        txtListChild = (TextView) convertView
                .findViewById(R.id.paramName);

        //CheckBox checkBox = convertView.findViewById(R.id.checkbox);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.frameworkDataLsListHashMap.get(this.frameworkDataLs.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.frameworkDataLs.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.frameworkDataLs.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
        FrameworkDataLs headerTitleClass = (FrameworkDataLs) getGroup(groupPosition);
        String headerTitle = headerTitleClass.getFRAMEWORKTITLE();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
