package com.example.khalilurrehman.testexpandalblecode;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.khalilurrehman.testexpandalblecode.Adapter.CustomExpandableListAdapter;
import com.example.khalilurrehman.testexpandalblecode.Adapter.MyCategoriesExpandableListAdapter;
import com.example.khalilurrehman.testexpandalblecode.Models.FrameSub;
import com.example.khalilurrehman.testexpandalblecode.Models.FrameworkDataLs;
import com.example.khalilurrehman.testexpandalblecode.Models.FrameworkRepsonse;
import com.example.khalilurrehman.testexpandalblecode.Network.APIService;
import com.example.khalilurrehman.testexpandalblecode.Network.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG;
    List<FrameworkDataLs> frameworkDataLs1;
    List<FrameworkDataLs> frameworkDataLsStorage;
    HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap;
    HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMapStorage;
    ExpandableListView expandableListView;
    CustomExpandableListAdapter adapter;
    FloatingActionButton fab,fab1;
    MyCategoriesExpandableListAdapter myCategoriesExpandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = getLocalClassName();
        fab = findViewById(R.id.fab);
        frameworkDataLs1 = new ArrayList<>();
        expandableListView = findViewById(R.id.expandedlist);
        frameworkDataLsListHashMap = new HashMap<>();
        frameworkDataLsListHashMapStorage = new HashMap<>();
        frameworkDataLsStorage = new ArrayList<>();

        fab1 = findViewById(R.id.fab1);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                Toast.makeText(getApplicationContext(),
                        frameworkDataLs1.get(groupPosition).getFRAMEWORKTITLE() + " Collapsed",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        getFrameworkData();

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(MainActivity.this, frameworkDataLs1.get(groupPosition).getFRAMEWORKTITLE()+"----"+frameworkDataLsListHashMap.get(frameworkDataLs1.get(groupPosition)).get(childPosition).getFRAMEWORKSUB(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this,this,frameworkDataLs1,frameworkDataLsListHashMap);
        expandableListView.setAdapter(myCategoriesExpandableListAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (FrameworkDataLs item: frameworkDataLs1) {
                    Log.d(TAG, "onClick: "+ item.getSelected());
                    //if (item.getSelected()){
                    int count = 0;
                        List<FrameSub> frameSubList = new ArrayList<>();
                        for (FrameSub itemsFrame: frameworkDataLsListHashMap.get(item)) {
                            if (itemsFrame.getSelected()){
                                Log.d(TAG, "onClick: 1 -->"+ itemsFrame.getFRAMEWORKSUB()+"---"+itemsFrame.getSelected());
                                FrameSub fs = new FrameSub(itemsFrame.getFRAMEWORKSUB(),itemsFrame.getFRAMEWORKID(),itemsFrame.getFRAMEWORKTITLE(),itemsFrame.getCATEGORYID(),itemsFrame.getMarks(),itemsFrame.getSCORE(),itemsFrame.getREMARK(),true);
                                frameSubList.add(fs);
                                count++;
                            }
                        }
                        if (count>0){
                            FrameworkDataLs frameworkDataLs = new FrameworkDataLs(item.getFRAMEWORKTITLE(),item.getCATEGORYID(),item.getFRAMEWORKID(),true);
                            frameworkDataLsStorage.clear();
                            frameworkDataLsListHashMapStorage.clear();
                            frameworkDataLsStorage.add(frameworkDataLs);
                            frameworkDataLsListHashMapStorage.put(frameworkDataLs,frameSubList);
                        }
                    //}
                }
                showNewList(frameworkDataLsStorage,frameworkDataLsListHashMapStorage);
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameworkDataLs1.clear();
                frameworkDataLsListHashMap.clear();
                myCategoriesExpandableListAdapter.notifyDataSetChanged();
                getFrameworkData();
            }
        });


    }

    private void showNewList(List<FrameworkDataLs> frameworkDataLsStorage, HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMapStorage) {
        for (FrameworkDataLs item: frameworkDataLsStorage) {
            Log.d(TAG, "onClick: ShowList ->"+ item.getSelected());
            for (FrameSub itemsFrame: frameworkDataLsListHashMapStorage.get(item)) {
                if (itemsFrame.getSelected()){
                    Log.d(TAG, "onClick: onClick: ShowList 1 -> -->"+ itemsFrame.getFRAMEWORKSUB()+"---"+itemsFrame.getSelected());
                }
            }
        }
    }

    private void getFrameworkData() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<FrameworkRepsonse> userCall = service.getFrameworkTitles(1,1);
        userCall.enqueue(new Callback<FrameworkRepsonse>() {
            @Override
            public void onResponse(Call<FrameworkRepsonse> call, Response<FrameworkRepsonse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<FrameworkDataLs> frameworkDataList = response.body().getData();
                        for (FrameworkDataLs item : frameworkDataList) {
                                /*String frameworktitle, String categoryid, String frameworkid,boolean isSelected*/
                                FrameworkDataLs frameworkDataLs = new FrameworkDataLs(item.getFRAMEWORKTITLE(),item.getCATEGORYID(),item.getFRAMEWORKID(),false);
                                frameworkDataLs1.add(frameworkDataLs);
                                /*String frameworksub, String frameworkid, String frameworktitle, Object categoryid, Integer marks, String score, String remark,boolean isSelected*/
                                List<FrameSub> frameSubList = new ArrayList<>();
                                for (FrameSub item1: item.getFrameSub()) {
                                    FrameSub fs = new FrameSub(item1.getFRAMEWORKSUB(),item1.getFRAMEWORKID(),item1.getFRAMEWORKTITLE(),item1.getCATEGORYID(),item1.getMarks(),item1.getSCORE(),item1.getREMARK(),false);
                                    frameSubList.add(fs);
                                }
                                frameworkDataLsListHashMap.put(frameworkDataLs,frameSubList);

                                //myCategoriesExpandableListAdapter.notifyDataSetChanged();
                            }
                       /* }else{
                            for (FrameworkDataLs item : frameworkDataList) {
                                List<FrameSub> frameSubList = new ArrayList<>();
                                for (FrameSub item1: item.getFrameSub()) {
                                    for (FrameSub itemSubPrev: frameworkDataLsListHashMap.get(item)) {
                                        FrameSub fs = new FrameSub(item1.getFRAMEWORKSUB(), item1.getFRAMEWORKID(), item1.getFRAMEWORKTITLE(), item1.getCATEGORYID(), item1.getMarks(), item1.getSCORE(), item1.getREMARK(), false);
                                        frameSubList.add(fs);
                                    }
                                }

                                FrameworkDataLs frameworkDataLs = new FrameworkDataLs(item.getFRAMEWORKTITLE(),item.getCATEGORYID(),item.getFRAMEWORKID(),false);
                                frameworkDataLs1.add(frameworkDataLs);
                                frameworkDataLsListHashMap.put(frameworkDataLs,frameSubList);

                                myCategoriesExpandableListAdapter.notifyDataSetChanged();
                            }
                        }*/
                        updateUiItems(frameworkDataLs1,frameworkDataLsListHashMap);
                    }
                }
            }

            @Override
            public void onFailure(Call<FrameworkRepsonse> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void updateUiItems(List<FrameworkDataLs> frameworkDataLs1, HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap) {
        for (FrameworkDataLs items: frameworkDataLs1) {
            Log.d(TAG, "updateUiItems: main"+items.getCATEGORYID());
            for (FrameworkDataLs itemPrev: frameworkDataLsStorage) {
                if (itemPrev.getCATEGORYID().equals(items.getCATEGORYID())){
                    Log.d(TAG, "updateUiItems: main Storage "+itemPrev.getCATEGORYID());
                    int count = 0;
                    for (FrameSub itemSub: frameworkDataLsListHashMap.get(items)) {
                        Log.d(TAG, "updateUiItems: Sub "+itemSub.getFRAMEWORKID());
                        for (FrameSub itemSubPrev: frameworkDataLsListHashMapStorage.get(itemPrev)) {
                            Log.d(TAG, "updateUiItems: Sub Storage "+itemSubPrev.getFRAMEWORKID());
                            if (itemSub.getFRAMEWORKID().equals(itemSubPrev.getFRAMEWORKID())){
                                Log.d(TAG, "updateUiItems: "+itemSubPrev.getFRAMEWORKSUB());
                                itemSub.setSelected(true);
                                count++;
                            }else{
                                itemSub.setSelected(false);
                            }
                        }
                    }
                    if (count == frameworkDataLsListHashMap.get(items).size()){
                        items.setSelected(true);
                    }
                }
            }
            myCategoriesExpandableListAdapter.notifyDataSetChanged();
        }
    }

}
