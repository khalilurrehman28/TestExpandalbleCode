package com.example.khalilurrehman.testexpandalblecode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

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

    List<FrameworkDataLs> frameworkDataLs;
    HashMap<FrameworkDataLs, List<FrameSub>> frameworkDataLsListHashMap;
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameworkDataLs = new ArrayList<>();
        expandableListView = findViewById(R.id.expandedlist);
        frameworkDataLsListHashMap = new HashMap<>();
        getFrameworkData();
    }

    private void getFrameworkData() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<FrameworkRepsonse> userCall = service.getFrameworkTitles(1);
        userCall.enqueue(new Callback<FrameworkRepsonse>() {
            @Override
            public void onResponse(Call<FrameworkRepsonse> call, Response<FrameworkRepsonse> response) {
                Log.d("getListStatus", " " + response.body().getStatus());
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Log.d("getMessage", "" + response.body().getMsg());
                        List<FrameworkDataLs> frameworkDataList = response.body().getData();
                        for (FrameworkDataLs item : frameworkDataList) {
                            frameworkDataLs.add(item);
                            //List<FrameSub> frameSubList = item.getFrameSub();
                            frameworkDataLsListHashMap.put(item,item.getFrameSub());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FrameworkRepsonse> call, Throwable t) {
                //hidepDialog();
                Log.d("onFailure", t.toString());
            }
        });
    }
}
