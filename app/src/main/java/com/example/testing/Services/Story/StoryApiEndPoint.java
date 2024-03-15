package com.example.testing.Services.Story;

import com.example.testing.Model.BaseResult;
import com.example.testing.Models.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoryApiEndPoint {
    @GET("api/v1/story/get-all")
    Call<BaseResult<List<Story>>> getAllStory();
}
