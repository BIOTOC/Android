package com.example.testing.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testing.Model.Book;
import com.example.testing.Model.BookAdapter;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;


public class WishListFragment extends Fragment {
    private RecyclerView rvWishlist;
    private BookAdapter bookAdapter;
    private List<Book> wishlist = new ArrayList<>();

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

    public void addToWishlist(Book book) {
        wishlist.add(book);
        bookAdapter.notifyDataSetChanged();
    }
}
