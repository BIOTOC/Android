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

import com.example.testing.Models.BookAdapter2;
import com.example.testing.Models.Story;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

public class WishListFragment extends Fragment {
    private RecyclerView rvWishlist;
    private BookAdapter2 bookAdapter;
    private List<Story> wishlistStories;
    private View mView;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_wishlist, container, false);
        rvWishlist = mView.findViewById(R.id.rv_wishlist);
        rvWishlist.setLayoutManager(new LinearLayoutManager(getActivity()));

        wishlistStories = new ArrayList<>();
        bookAdapter = new BookAdapter2(wishlistStories);
        rvWishlist.setAdapter(bookAdapter);

        databaseHelper = new DatabaseHelper(getActivity());
        loadWishlistFromDatabase();

        Log.d("WishListFragment", "Fragment created successfully.");

        bookAdapter.setOnBookClickListener(new BookAdapter2.OnBookClickListener() {
            @Override
            public void onBookClick(Story story) {
                // Xử lý sự kiện khi một mục được nhấn
                // Ví dụ: Chuyển sang DetailActivity và truyền dữ liệu của câu chuyện (story)
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("Id", story.getId());
                startActivity(intent);
            }
        });

        bookAdapter.setOnRemoveClickListener(new BookAdapter2.OnRemoveClickListener() {
            @Override
            public void onRemoveClick(int position) {
                removeItem(position);
            }
        });

        return mView;
    }

    private void removeItem(int position) {
        if (position >= 0 && position < wishlistStories.size()) {
            // Lấy ra mục cần xóa
            Story storyToRemove = wishlistStories.get(position);

            // Xóa mục khỏi danh sách hiển thị
            wishlistStories.remove(position);
            bookAdapter.notifyItemRemoved(position);
            bookAdapter.notifyItemRangeChanged(position, wishlistStories.size());

            // Xóa mục khỏi cơ sở dữ liệu
            if (databaseHelper != null) {
                databaseHelper.removeFromWishlist(storyToRemove.getId());
            }
        }
    }

    private void loadWishlistFromDatabase() {
        wishlistStories.clear();
        wishlistStories.addAll(databaseHelper.getWishlist());
        Log.e("WishListFragment", "checkdata: " + wishlistStories.isEmpty());
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
