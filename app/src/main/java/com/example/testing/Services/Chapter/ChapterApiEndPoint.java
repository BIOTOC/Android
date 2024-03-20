package com.example.testing.Services.Chapter;

import com.example.testing.Models.BaseResult;
import com.example.testing.Models.Chapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChapterApiEndPoint {
    @GET("api/v1/chapter/get-by-id/{id}")
    Call<BaseResult<Chapter>> getChapterById(@Path("id") int storyId);
}
