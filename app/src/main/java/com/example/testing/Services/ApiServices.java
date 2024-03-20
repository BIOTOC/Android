package com.example.testing.Services;

import com.example.testing.Services.Category.CategoryApiEndPoint;
import com.example.testing.Services.Story.StoryApiEndPoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {
    private static final String BASE_URL = "https://storyappbe.azurewebsites.net/";

    private static ApiServices instance = null;

    public StoryApiEndPoint storyApiEndPoint;

    public CategoryApiEndPoint categoryApiEndPoint;

    private ApiServices() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        storyApiEndPoint = retrofit.create(StoryApiEndPoint.class);

        categoryApiEndPoint = retrofit.create(CategoryApiEndPoint.class);

    }

    private static ApiServices getInstance() {
        if (instance == null) {
            instance = new ApiServices();
        }
        return instance;
    }

    public static StoryApiEndPoint getStoryApiEndPoint() {
        return getInstance().storyApiEndPoint;
    }

    public  static CategoryApiEndPoint getCategoryApiEndPoint()  {
        return getInstance().categoryApiEndPoint;
    }
}
