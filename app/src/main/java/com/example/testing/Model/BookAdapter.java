package com.example.testing.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private List<Book> mBooks;

    public void addBook(Book book) {
        mBooks.add(book);
        notifyDataSetChanged();
    }

    public void removeBook(Book book) {
        mBooks.remove(book);
        notifyDataSetChanged();
    }

    public BookAdapter() {
    }

    public BookAdapter(List<Book> mBooks) {
        this.mBooks = mBooks;
    }

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    private OnBookClickListener onBookClickListener;

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.onBookClickListener = listener;
    }


    public void setData(List<Book> list){
        this.mBooks = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = mBooks.get(position);
        if(book == null){
            return;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(book);
                }
            }
        });
        String base64Image = book.getImageBase64();
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.imgBook.setImageBitmap(decodedByte);
        holder.tvTitle.setText(book.getTitle());
        holder.tvChapter.setText("Number of Chapters: " + book.getNumberOfChapters());
    }

    @Override
    public int getItemCount() {
        if(mBooks != null){
            return mBooks.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView tvTitle;
        private TextView tvChapter;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
        }
    }
}
