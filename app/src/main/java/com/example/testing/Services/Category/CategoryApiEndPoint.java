package com.example.testing.Services.Category;

import com.example.testing.Models.BaseResult;
import com.example.testing.Models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApiEndPoint {
    @GET("api/v1/category/get-all")
    Call<BaseResult<List<Category>>> getAllCategory();


}
