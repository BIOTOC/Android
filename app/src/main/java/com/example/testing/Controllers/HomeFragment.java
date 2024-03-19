package com.example.testing.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.BaseResult;
import com.example.testing.Models.Category;
import com.example.testing.Models.CategoryAdapter;
import com.example.testing.Models.Story;
import com.example.testing.R;
import com.example.testing.Services.ApiServices;

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
        //categoryAdapter.setData(getListCategory());
        categoryAdapter.setOnBookClickListener(new CategoryAdapter.OnBookClickListener() {
            @Override
            public void onBookClick(Story book) {
                // Handling book click event
                onBookItemClick(book);
            }
        });
    }

    private void setUpRecyclerView() {
        categoryAdapter = new CategoryAdapter(requireContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        rcvCategory.setAdapter(categoryAdapter);
    }

    // Setting up data
    private void setUpData() {
        try{

            ApiServices.getCategoryApiEndPoint().getAllCategory().enqueue(new Callback<BaseResult<List<Category>>>() {
                @Override
                public void onResponse(Call<BaseResult<List<Category>>> call, Response<BaseResult<List<Category>>> response) {
                    if (response.isSuccessful()) {
                        BaseResult<List<Category>> result = response.body();
                        List<Category> list = result.getData();
                        categoryAdapter.setData(list);
                    }else {
                        Log.d("API_Response", "Response not successful: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<BaseResult<List<Category>>> call, Throwable t) {
                    Toast.makeText(mMainActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        }catch (Exception e){
            Toast.makeText(mMainActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("Exception",e.getMessage());

        }


    }


    // Handling book item click
    private void onBookItemClick(Story story) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("Id", story.getId());
        startActivity(intent);
    }

}

