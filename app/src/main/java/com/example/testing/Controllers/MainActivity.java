package com.example.testing.Controllers;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testing.R;
import com.example.testing.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bindingView();
        bindingAction();

        replaceFragment(new HomeFragment());
    }

    private void bindingView() {
        setSupportActionBar(binding.toolbar);
    }

    private void bindingAction() {
        binding.bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.mHome) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.mSearch) {
                replaceFragment(new SearchFragment());
            } else if (item.getItemId() == R.id.mWishList) {
                Bundle bundle = new Bundle();
                int bookId = getIntent().getIntExtra("bookId", -1);
                bundle.putInt("bookId", bookId);

                WishListFragment wishListFragment = new WishListFragment();
                wishListFragment.setArguments(bundle);

                replaceFragment(wishListFragment);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}
