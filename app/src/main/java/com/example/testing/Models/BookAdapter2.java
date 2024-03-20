package com.example.testing.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;

import java.util.List;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.BookViewHolder>{

    private List<Story> mStories;

    public BookAdapter2() {
    }

    public BookAdapter2(List<Story> mStories) {
        this.mStories = mStories;
    }

    public interface OnRemoveClickListener {
        void onRemoveClick(int position);
    }

    private OnRemoveClickListener onRemoveClickListener;

    public void setOnRemoveClickListener(OnRemoveClickListener listener) {
        this.onRemoveClickListener = listener;
    }

    public void updateData(List<Story> wishlistStories) {
        mStories = wishlistStories;
        notifyDataSetChanged();
    }

    public interface OnBookClickListener {
        void onBookClick(Story story);
    }

    private OnBookClickListener onBookClickListener;

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.onBookClickListener = listener;
    }



    public void setData(List<Story> list){
        this.mStories = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book2, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Story story = mStories.get(position);
        if(story == null){
            return;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(story);
                }
            }
        });
        String base64Image = story.getImage();
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.imgBook.setImageBitmap(decodedByte);
        holder.tvTitle.setText(story.getName());
        holder.tvChapter.setText("Number of Chapter:" + story.getNumberChapter());

    }

    @Override
    public int getItemCount() {
        if(mStories != null){
            return mStories.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView tvTitle;
        private TextView tvChapter;
        private Button btnRemove;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
            btnRemove = itemView.findViewById(R.id.btn_remove);

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onRemoveClickListener != null) {
                        onRemoveClickListener.onRemoveClick(position);
                    }
                }
            });

        }
    }
}
