package com.example.testing.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testing.Models.BaseResult;
import com.example.testing.Models.Chapter;
import com.example.testing.R;
import com.example.testing.Services.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterDetailActivity extends AppCompatActivity {

    private int chapterId;
    private ScrollView scrollView;
    private Button viewCommentsButton;
    private TextView chapterIdTextView;
    private TextView chapterDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("chapterId")) {
            chapterId = intent.getIntExtra("chapterId", -1);
            String name = intent.getStringExtra("name");


            ApiServices.getChapterApiEndPoint().getChapterById(chapterId).enqueue(new Callback<BaseResult<Chapter>>() {
                @Override
                public void onResponse(Call<BaseResult<Chapter>> call, Response<BaseResult<Chapter>> response) {
                    if (response.isSuccessful()) {
                        BaseResult<Chapter> result = response.body();
                        if(result.getStatusCode().equals("200")){
                            chapterDetailTextView.setText(result.getData().getChapterDetail());
//                            chapterIdTextView.setText(name + " - Chapter " + chapterId);
                            chapterIdTextView.setText(result.getData().getTitle()+"- chapter "+result.getData().getChapterId());
                        }else{
                            Toast.makeText(ChapterDetailActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Log.d("API_Response", "Response not successful: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<BaseResult<Chapter>> call, Throwable t) {
                        Log.e("Exception",t.getMessage());
                    Toast.makeText(ChapterDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void bindingView() {
        chapterIdTextView = findViewById(R.id.chapter_id_text_view);
        chapterDetailTextView = findViewById(R.id.chapter_detail_text_view);
        scrollView = findViewById(R.id.scrollView);
        viewCommentsButton = findViewById(R.id.view_comments_button);
        viewCommentsButton.setVisibility(View.GONE);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (isScrollViewAtBottom(scrollView)) {
                viewCommentsButton.setVisibility(View.VISIBLE);
            } else {
                viewCommentsButton.setVisibility(View.GONE);
            }
        });
    }

    private void bindingAction() {
        viewCommentsButton.setOnClickListener(v -> {
            Intent oldintent = getIntent();
            String name = oldintent.getStringExtra("name");
            Intent intent = new Intent(ChapterDetailActivity.this, CommentActivity.class);
            intent.putExtra("chapterId", chapterId);
            String.valueOf(intent.putExtra("name", name));

            startActivity(intent);
        });

    }

    private boolean isScrollViewAtBottom(ScrollView scrollView) {
        if (scrollView.getChildAt(0) != null) {
            return (scrollView.getHeight() + scrollView.getScrollY()) >= (scrollView.getChildAt(0).getHeight());
        }
        return false;
    }


}