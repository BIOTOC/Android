package com.example.testing.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testing.R;

public class ChapterDetailActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("chapterId") && intent.hasExtra("chapterDetail")) {
            int chapterId = intent.getIntExtra("chapterId", -1);
            String chapterDetail = intent.getStringExtra("chapterDetail");

            TextView chapterIdTextView = findViewById(R.id.chapter_id_text_view);
            TextView chapterDetailTextView = findViewById(R.id.chapter_detail_text_view);

            chapterIdTextView.setText("Chapter " + chapterId);
            chapterDetailTextView.setText(chapterDetail);
        }

    }
}