package com.example.khalilurrehman.testexpandalblecode;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.khalilurrehman.testexpandalblecode.Adapter.CustomExpandableListAdapter;
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
    List<FrameworkDataLs> frameworkDataLs;
    HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap;
    ExpandableListView expandableListView;
    CustomExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = getLocalClassName();
        frameworkDataLs = new ArrayList<>();
        expandableListView = findViewById(R.id.expandedlist);
        frameworkDataLsListHashMap = new HashMap<>();
        adapter = new CustomExpandableListAdapter(this,frameworkDataLs,frameworkDataLsListHashMap);
        expandableListView.setAdapter(adapter);

        getFrameworkData();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //finish();
                Log.d(TAG, "run: "+frameworkDataLsListHashMap.get(frameworkDataLs.get(2)).get(1).getFRAMEWORKSUB());

            }
        }, 2000L); //3000 L = 3 detik

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                Toast.makeText(getApplicationContext(),
                        frameworkDataLs.get(groupPosition).getFRAMEWORKTITLE() + " Collapsed",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                //Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, frameworkDataLs.get(groupPosition).getFRAMEWORKTITLE()+"----"+frameworkDataLsListHashMap.get(frameworkDataLs.get(groupPosition)).get(childPosition).getFRAMEWORKSUB(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void getFrameworkData() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<FrameworkRepsonse> userCall = service.getFrameworkTitles(1,1);
        userCall.enqueue(new Callback<FrameworkRepsonse>() {
            @Override
            public void onResponse(Call<FrameworkRepsonse> call, Response<FrameworkRepsonse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<FrameworkDataLs> frameworkDataList = response.body().getData();
                        for (FrameworkDataLs item : frameworkDataList) {
                            frameworkDataLs.add(item);
                            frameworkDataLsListHashMap.put(item,item.getFrameSub());
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FrameworkRepsonse> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
