package com.example.testing.Services.Story;

import com.example.testing.Models.BaseResult;
import com.example.testing.Models.PaginationResult;
import com.example.testing.Models.Story;
import com.example.testing.Services.Story.Request.FilterStoryRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StoryApiEndPoint {
    @GET("api/v1/story/get-all")
    Call<BaseResult<List<Story>>> getAllStory();

    @GET("api/v1/story/get-by-id/{id}")
    Call<BaseResult<Story>> getStoryById(@Path("id") int storyId);

    @POST("api/v1/story/get-by-filter")
    Call<PaginationResult<List<Story>>> filterStoryByName(@Body FilterStoryRequest request);
}
