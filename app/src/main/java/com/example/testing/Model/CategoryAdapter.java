package com.example.testing.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private Context mContext;
    private List<Category> mListCategory;

    private OnBookClickListener onBookClickListener;


    // Constructor và các phương thức khác

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.onBookClickListener = listener;
    }

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category> list){
        this.mListCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if(category == null){
            return;
        }

        holder.tvNameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        BookAdapter bookAdapter = new BookAdapter();
        bookAdapter.setData(category.getBooks());

        bookAdapter.setOnBookClickListener(new BookAdapter.OnBookClickListener() {
            @Override
            public void onBookClick(Book book) {
                if (onBookClickListener != null) {
                    onBookClickListener.onBookClick(book);
                }
            }
        });

        holder.rcvBook.setAdapter(bookAdapter);
    }

    @Override
    public int getItemCount() {
        if(mListCategory != null){
            return mListCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameCategory;
        private RecyclerView rcvBook;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
            rcvBook = itemView.findViewById(R.id.rcv_book);
        }
    }
}
