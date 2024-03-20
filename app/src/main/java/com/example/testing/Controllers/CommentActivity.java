package com.example.testing.Controllers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.Models.CommentAdapter;
import com.example.testing.R;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private int chapterId;
    private RecyclerView recyclerView;
    private EditText usernameEditText;
    private EditText contentEditText;
    private Button addButton;
    private CommentAdapter commentAdapter;
    private DatabaseHelper databaseHelper;

    private TextView storyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseHelper = new DatabaseHelper(this);

        chapterId = getIntent().getIntExtra("chapterId", -1);
        String name = getIntent().getStringExtra("name");


        recyclerView = findViewById(R.id.recycler_view_comments);
        usernameEditText = findViewById(R.id.edit_text_username);
        contentEditText = findViewById(R.id.edit_text_comment);
        addButton = findViewById(R.id.button_submit);
        storyName = findViewById(R.id.story_id_text_view);

        storyName.setText(name + " - Chapter " + chapterId);

        // Tạo một Adapter mới
        commentAdapter = new CommentAdapter(new ArrayList<>());

        // Đặt Adapter cho RecyclerView
        recyclerView.setAdapter(commentAdapter);

        // Đặt LayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load comments
        loadComments();

        // Xử lý sự kiện thêm comment
        addButton.setOnClickListener(v -> addComment());
    }


    private void loadComments() {
        List<String> commentList = databaseHelper.getCommentsForChapter(chapterId);

        commentAdapter.updateComments(commentList);
    }

    private void addComment() {
        String username = usernameEditText.getText().toString();
        String content = contentEditText.getText().toString();

        databaseHelper.addComment(chapterId, username, content);

        loadComments();

        usernameEditText.setText("");
        contentEditText.setText("");
    }
}