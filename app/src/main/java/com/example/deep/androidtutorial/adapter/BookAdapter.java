package com.example.deep.androidtutorial.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deep.androidtutorial.R;
import com.example.deep.androidtutorial.model.Book;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @NotNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.tname.setText(book.getName());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tname;

        public BookViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.bookname);
        }
    }
}
