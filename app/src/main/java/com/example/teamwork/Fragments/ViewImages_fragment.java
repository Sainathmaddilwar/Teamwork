package com.example.teamwork.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamwork.Adapter.ViewImage_Adapter;
import com.example.teamwork.Api.Api;
import com.example.teamwork.R;
import com.example.teamwork.models.retro_Object;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewImages_fragment extends Fragment {
     RecyclerView recyclerView;
     ViewImage_Adapter viewImage_adapter;
     ArrayList<retro_Object> objectArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.vimage,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        objectArrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api jsonApi=retrofit.create(Api.class);
        Call<ArrayList<retro_Object>> call=jsonApi.getData();
        call.enqueue(new Callback<ArrayList<retro_Object>>() {
            @Override
            public void onResponse(Call<ArrayList<retro_Object>> call, Response<ArrayList<retro_Object>> response) {
                objectArrayList=response.body();
                viewImage_adapter=new ViewImage_Adapter(getContext(),objectArrayList);
                recyclerView.setAdapter(viewImage_adapter);
                viewImage_adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<retro_Object>> call, Throwable t) {

            }
        });


        return view;
    }



}
