package com.example.testing.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Model.BaseResult;
import com.example.testing.Models.Category;
import com.example.testing.Models.CategoryAdapter;
import com.example.testing.Models.Story;
import com.example.testing.R;
import com.example.testing.Services.ApiServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private View mView;
    private MainActivity mMainActivity;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mMainActivity = (MainActivity) getActivity();

        bindingView();
        bindingAction();

        return mView;
    }

    private void bindingView() {
        rcvCategory = mView.findViewById(R.id.rcv_category);
    }

    private void bindingAction(){
        setUpRecyclerView();
        setUpData();
    }

    private void setUpRecyclerView() {
        categoryAdapter = new CategoryAdapter(requireContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        rcvCategory.setAdapter(categoryAdapter);
    }

    // Setting up data
    private void setUpData() {
        ApiServices.getStoryApiEndPoint().getAllStory().enqueue(new Callback<BaseResult<List<Story>>>() {
            @Override
            public void onResponse(Call<BaseResult<List<Story>>> call, Response<BaseResult<List<Story>>> response) {
                if (response.isSuccessful()) {
                    BaseResult<List<Story>> result = response.body();
                    if (result != null) {
                        Log.d("API_Response", "Status Code: " + result.getStatusCode());
                        Log.d("API_Response", "Message: " + result.getMessage());
                        Log.d("API_Response", "Message: " + result.getData());

                        List<Story> stories = result.getData();
                        if (stories != null) {
                            for (Story story : stories) {
                                Log.d("API_Response", "Story ID: " + story.getId());
                                Log.d("API_Response", "Story Title: " + story.getName());
                                // Đặt code xử lý dữ liệu ở đây nếu cần
                            }
                        } else {
                            Log.d("API_Response", "No data received");
                        }
                    } else {
                        Log.d("API_Response", "Response body is null");
                    }
                } else {
                    Log.d("API_Response", "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BaseResult<List<Story>>> call, Throwable t) {
                Log.e("API_Response", "API call failed: " + t.getMessage(), t);
            }
        });

    }


    // Handling book item click
    private void onBookItemClick(Story story) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("Id", story.getId());
        startActivity(intent);
    }

    private List<Category> getListCategory(List<Story> listBook) {

//        final List<Story> listBook = new ArrayList<>();
//
//        try {
//            ApiServices.getStoryApiEndPoint().getAllStory().enqueue(new Callback<BaseResult<List<Story>>>() {
//                @Override
//                public void onResponse(Call<BaseResult<List<Story>>> call, Response<BaseResult<List<Story>>> response) {
//                    BaseResult<List<Story>> result = response.body();
//                    if (Integer.parseInt(result.getStatusCode()) == 200) {
//                        listBook.addAll(result.getData());
//                    } else {
//                        Toast.makeText(mMainActivity, result.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<BaseResult<List<Story>>> call, Throwable t) {
//                    Toast.makeText(mMainActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (Exception e) {
//            Toast.makeText(mMainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

        List<Category> list = new ArrayList<>();
        list.add(new Category("Category 1", listBook));
        list.add(new Category("Category 2", listBook));
        list.add(new Category("Category 3", listBook));
        list.add(new Category("Category 4", listBook));
        return list;
    }

}

