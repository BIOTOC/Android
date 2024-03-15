package com.example.testing.Controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.BookAdapter;
import com.example.testing.Models.Story;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;


public class WishListFragment extends Fragment {
    private RecyclerView rvWishlist;
    private BookAdapter bookAdapter;
    private List<Story> wishlist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        rvWishlist = view.findViewById(R.id.rv_wishlist);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvWishlist.setLayoutManager(layoutManager);
        bookAdapter = new BookAdapter(wishlist);
        rvWishlist.setAdapter(bookAdapter);
    }

    public void addToWishlist(Story story) {
        wishlist.add(story);
        bookAdapter.notifyDataSetChanged();
    }
}
