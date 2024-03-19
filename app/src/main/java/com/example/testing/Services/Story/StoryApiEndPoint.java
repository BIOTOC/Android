package com.example.testing.Services.Story;

import com.example.testing.Models.BaseResult;
import com.example.testing.Models.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StoryApiEndPoint {
    @GET("api/v1/story/get-all")
    Call<BaseResult<List<Story>>> getAllStory();

    @GET("api/v1/story/get-by-id/{id}")
    Call<BaseResult<Story>> getStoryById(@Path("id") int storyId);
}
