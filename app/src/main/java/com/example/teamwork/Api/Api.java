package com.example.teamwork.Api;

import com.example.teamwork.models.retro_Object;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("photos")
    Call<ArrayList<retro_Object>> getData();
}
