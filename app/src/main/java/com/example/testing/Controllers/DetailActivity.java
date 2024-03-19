package com.example.testing.Controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.BaseResult;
import com.example.testing.Models.Book;
import com.example.testing.Models.Chapter;
import com.example.testing.Models.ChapterAdapter;
import com.example.testing.Models.Story;
import com.example.testing.R;
import com.example.testing.Services.ApiServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements ChapterAdapter.OnChapterClickListener{

    private ImageView imageViewBook;
    private TextView textViewTitle;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Button btnAddToWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        bindingView();
        bindingAction();

        handleIntent(getIntent());
    }

    private void bindingView() {
        imageViewBook = findViewById(R.id.img_book_detail);
        textViewTitle = findViewById(R.id.tv_title_detail);
        recyclerView = findViewById(R.id.rcv_book_chapter);
        toolbar = findViewById(R.id.detail_toolbar);
        btnAddToWishlist = findViewById(R.id.btn_add_to_wishlist);
    }

    private void bindingAction() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Story story = getIntent().getParcelableExtra("story");

//                WishListFragment wishListFragment = (WishListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_wishlist);
//                if (wishListFragment != null) {
//                    wishListFragment.addToWishlist(story);
//                }
            }
        });
    }

    private void handleIntent(Intent intent) {
        if (intent != null && intent.hasExtra("Id")) {
            int bookId = intent.getIntExtra("Id", -1);

            ApiServices.getStoryApiEndPoint().getStoryById(bookId).enqueue(new Callback<BaseResult<Story>>() {

                @Override
                public void onResponse(Call<BaseResult<Story>> call, Response<BaseResult<Story>> response) {
                    if(response.isSuccessful()){
                        BaseResult<Story> result = response.body();
                        if(result.getStatusCode().equals("200")){
                            Story story = result.getData();
                            if(story != null){
                                setUpBookDetails(story);
                                setUpChapterList(story);
                                String.valueOf(intent.putExtra("name", story.getName()));

                            }
                        }else{
                            Toast.makeText(DetailActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<BaseResult<Story>> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setUpBookDetails(Story story) {
        String base64Image = story.getImage();
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageViewBook.setImageBitmap(decodedByte);
        textViewTitle.setText(story.getName());
    }

    private void setUpChapterList(Story story) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Chapter> chapters = getChapterList(story);
        ChapterAdapter adapter = new ChapterAdapter(chapters, this);
        recyclerView.setAdapter(adapter);
    }


    private List<Chapter> getChapterList(Story story) {
        List<Chapter> chapters = new ArrayList<>();
        if (story != null) {
            int numberOfChapters = story.getNumberChapter();
            for (int i = 1; i <= numberOfChapters; i++) {
                Chapter chapter = new Chapter(i, "Chapter " + i);
                chapters.add(chapter);
            }
        }
        return chapters;
    }

    @Override
    public void onChapterClick(Chapter chapter) {
        Intent oldintent = getIntent();
        String name = oldintent.getStringExtra("name");
        Intent intent = new Intent(this, ChapterDetailActivity.class);
        intent.putExtra("chapterId", chapter.getChapterId());
        String.valueOf(intent.putExtra("name", name));


        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Book getBookById(int bookId) {
        List<Book> listBook = new ArrayList<>();

//        listBook.add(new Book(R.drawable.book1, "Book 1"));
//        listBook.add(new Book(R.drawable.book2, "Book 2"));
//        listBook.add(new Book(R.drawable.book3, "Book 3"));
//        listBook.add(new Book(R.drawable.book4, "Book 4"));
//        listBook.add(new Book(R.drawable.book5, "Book 5"));

        for (Book book : listBook) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}