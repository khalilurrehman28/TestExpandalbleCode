package com.example.khalilurrehman.testexpandalblecode.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalilurrehman.testexpandalblecode.Models.FrameSub;
import com.example.khalilurrehman.testexpandalblecode.Models.FrameworkDataLs;
import com.example.khalilurrehman.testexpandalblecode.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zerones on 04-Oct-17.
 */

public class MyCategoriesExpandableListAdapter extends BaseExpandableListAdapter {

    //private final ArrayList<ArrayList<HashMap<String, String>>> childItems;
    private ArrayList<HashMap<String, String>> parentItems;
    //private final ArrayList<HashMap<String, String>> childItems;
    private LayoutInflater inflater;
    private Activity activity;
    private HashMap<String, String> child;
    private int count = 0;
    //private boolean isFromMyCategoriesFragment;
    private List<FrameworkDataLs> frameworkDataLs;
    Context ctx;
    private HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap;

    public MyCategoriesExpandableListAdapter(Context ctx,Activity activity,List<FrameworkDataLs> frameworkDataLs,HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap) {
       this.frameworkDataLs = frameworkDataLs;
       this.frameworkDataLsListHashMap = frameworkDataLsListHashMap;
       this.ctx = ctx;
       inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return frameworkDataLs.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.frameworkDataLsListHashMap.get(this.frameworkDataLs.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean b, View convertView, ViewGroup viewGroup) {
         final ViewHolderParent viewHolderParent;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_list_layout_choose_categories, null);
            viewHolderParent = new ViewHolderParent();
            viewHolderParent.tvMainCategoryName = convertView.findViewById(R.id.tvMainCategoryName);
            viewHolderParent.cbMainCategory = convertView.findViewById(R.id.cbMainCategory);
            viewHolderParent.ivCategory = convertView.findViewById(R.id.ivCategory);
            convertView.setTag(viewHolderParent);
        } else {
            viewHolderParent = (ViewHolderParent) convertView.getTag();
        }

        if (frameworkDataLs.get(groupPosition).getSelected()) {
            viewHolderParent.cbMainCategory.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderParent.cbMainCategory.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderParent.cbMainCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderParent.cbMainCategory.isChecked()) {
                    frameworkDataLs.get(groupPosition).setSelected(true);
                    for (int i = 0; i < frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).size(); i++) {
                        frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(i).setSelected(true);
                    }
                    //Toast.makeText(ctx, "Checked", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                } else {
                    frameworkDataLs.get(groupPosition).setSelected(false);
                    for (int i = 0; i < frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).size(); i++) {
                        frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(i).setSelected(false);
                    }
                    //Toast.makeText(ctx, "Unchecked", Toast.LENGTH_SHORT).show();

                    notifyDataSetChanged();
                }
            }
        });
        viewHolderParent.tvMainCategoryName.setText(frameworkDataLs.get(groupPosition).getFRAMEWORKTITLE());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean b, View convertView, ViewGroup viewGroup) {
        final ViewHolderChild viewHolderChild;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_list_layout_choose_category, null);
            viewHolderChild = new ViewHolderChild();
            viewHolderChild.tvSubCategoryName = convertView.findViewById(R.id.tvSubCategoryName);
            viewHolderChild.cbSubCategory = convertView.findViewById(R.id.cbSubCategory);
            viewHolderChild.viewDivider = convertView.findViewById(R.id.viewDivider);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
        if (frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(childPosition).getSelected()) {
            viewHolderChild.cbSubCategory.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderChild.cbSubCategory.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderChild.cbSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderChild.cbSubCategory.isChecked()) {
                    count = 0;
                    frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(childPosition).setSelected(true);
                    notifyDataSetChanged();
                } else {
                    count = 0;
                    frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(childPosition).setSelected(false);
                    notifyDataSetChanged();
                }

                for (int i = 0; i < frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).size(); i++) {
                    if (frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(i).getSelected()) {
                        count++;
                    }
                }
                if (count == frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).size()) {
                    frameworkDataLs.get(groupPosition).setSelected(true);
                    notifyDataSetChanged();
                } else {
                    frameworkDataLs.get(groupPosition).setSelected(false);
                    notifyDataSetChanged();
                }
            }
        });
        viewHolderChild.tvSubCategoryName.setText(frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(childPosition).getFRAMEWORKSUB());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }
    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
    private class ViewHolderParent {
        TextView tvMainCategoryName;
        CheckBox cbMainCategory;
        ImageView ivCategory;
    }
    private class ViewHolderChild {
        TextView tvSubCategoryName;
        CheckBox cbSubCategory;
        View viewDivider;
    }
}
