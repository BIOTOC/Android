package com.example.testing.Controllers;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.BookAdapter;
import com.example.testing.Models.PaginationResult;
import com.example.testing.Models.Story;
import com.example.testing.R;
import com.example.testing.Services.ApiServices;
import com.example.testing.Services.Story.Request.FilterStoryRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private List<Story> storyList = new ArrayList<Story>();
    private Integer page = 1;
    private Integer pageSize = 3;
    private Integer totalPages;
    private RecyclerView rcvBookSearch;
    private BookAdapter bookAdapter;
    private View mView;
    private SearchView searchView;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);

        bindingView();
        bindingAction();
        setUpRecyclerView();

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        setUpSearchView(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void bindingView() {
        rcvBookSearch = mView.findViewById(R.id.rcv_book_search);
    }

    private void bindingAction() {
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvBookSearch.setLayoutManager(linearLayoutManager);
        rcvBookSearch.setVisibility(View.GONE);

        if (ApiServices.getStoryApiEndPoint() != null) {
                        // Khởi tạo adapter với danh sách câu chuyện từ API
                        bookAdapter = new BookAdapter(storyList);
                        rcvBookSearch.setAdapter(bookAdapter);

                        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                        rcvBookSearch.addItemDecoration(itemDecoration);

                        bookAdapter.setOnBookClickListener(new BookAdapter.OnBookClickListener() {
                            @Override
                            public void onBookClick(Story story) {
                                onBookItemClick(story);
                            }
                        });
        } else {
            Log.e("API_Response", "StoryApiEndPoint is null");
        }
    }


    private void setUpSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(requireActivity().SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // No action needed here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handleQueryTextChange(newText);
                return true;
            }
        });
    }

    private void handleQueryTextChange(String newText) {
        if (bookAdapter != null) {
            if (ApiServices.getStoryApiEndPoint() != null) {
//
                page = 1;
                FilterStoryRequest request = new FilterStoryRequest(page,pageSize,newText);
                ApiServices.getStoryApiEndPoint().filterStoryByName(request).enqueue(new Callback<PaginationResult<List<Story>>>() {
                    @Override
                    public void onResponse(Call<PaginationResult<List<Story>>> call, Response<PaginationResult<List<Story>>> response) {
                        if (response.isSuccessful()) {
                            if(response.body().getStatusCode().equals("200")){
                                storyList = response.body().getData();
                                bookAdapter.setData(storyList);

                                totalPages = response.body().getTotalPages();
                                page+=1;
                                if (newText.isEmpty() || storyList.isEmpty()) {
                                    rcvBookSearch.setVisibility(View.GONE);
                                } else {
                                    rcvBookSearch.setVisibility(View.VISIBLE);
                                }
                            }else{
                                Toast.makeText(mView.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d("API_Response", "Response not successful: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<PaginationResult<List<Story>>> call, Throwable t) {

                    }
                });
            } else {
                Log.e("API_Response", "StoryApiEndPoint is null");
            }
        }
    }


    private void onBookItemClick(Story story) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("Id", story.getId());
        startActivity(intent);
    }

    private List<Story> getListBooks() {
        List<Story> listBook = new ArrayList<>();

        return listBook;
    }
}
