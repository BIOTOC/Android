package com.example.testing.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private List<Chapter> chapterList;
    private OnChapterClickListener listener;

    public ChapterAdapter(List<Chapter> chapterList, OnChapterClickListener listener) {
        this.chapterList = chapterList;
        this.listener = listener;
    }

    public interface OnChapterClickListener {

        void onChapterClick(Chapter chapter);
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);
        holder.bind(chapter);
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView chapterTextView;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterTextView = itemView.findViewById(R.id.chapter_text_view);
            itemView.setOnClickListener(this);
        }

        public void bind(Chapter chapter) {
            chapterTextView.setText(chapter.getTitle());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Chapter clickedChapter = chapterList.get(position);
                if (listener != null) {
                    listener.onChapterClick(clickedChapter);
                }
            }
        }
    }
}
