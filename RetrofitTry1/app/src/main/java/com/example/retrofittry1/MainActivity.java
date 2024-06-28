package com.example.retrofittry1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    RecyclerView rcv;
    rycAdapter rycAdapterr;
    List<Models> models;
    Retrofit retrofit;
    private String BASE_URL="https://api.thecatapi.com/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // https://api.thecatapi.com/v1/images/search?limit=10

        Gson gson= new GsonBuilder().setLenient().create();

        retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        rcv = findViewById(R.id.rcv);
        loadData();
    }


    private void loadData(){

        VeriApi  veriApi = retrofit.create(VeriApi.class);
        Call<List<Models>> call= veriApi.getData();

        call.enqueue(new Callback<List<Models>>() {
            @Override
            public void onResponse(Call<List<Models>> call, Response<List<Models>> response) {






                if (response.isSuccessful()){
                    List<Models>  modelsList =response.body();
                    models= new ArrayList<>(modelsList);




                    int spanCount = 2; // İki sütun kullanmak için
                    rcv.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));

                    // Adapter'i RecyclerView'e bağlayın
                    rycAdapterr = new rycAdapter(modelsList);
                    rcv.setAdapter(rycAdapterr);
                }

            }

            @Override
            public void onFailure(Call<List<Models>> call, Throwable t) {
            t.printStackTrace();
            }
        });




    }
}