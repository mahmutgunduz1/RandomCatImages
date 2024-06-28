package com.example.retrofittry1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VeriApi {

   @GET("images/search?limit=10")
    Call<List<Models>> getData();
}
