package com.example.khalilurrehman.testexpandalblecode.Network;


import com.example.khalilurrehman.testexpandalblecode.Models.FrameworkRepsonse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    //get_evidence_category_request
    @FormUrlEncoded
    @POST("get_evidence_category_request")
    Call<FrameworkRepsonse> getFrameworkTitles(@Field("CATEGORY_ID") int CATEGORY_ID);


}