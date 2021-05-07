package com.example.deep.androidtutorial.ui.booklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deep.androidtutorial.HomePage;
import com.example.deep.androidtutorial.R;
import com.example.deep.androidtutorial.adapter.BookAdapter;
import com.example.deep.androidtutorial.database.BookDao;
import com.example.deep.androidtutorial.model.Book;

import java.util.List;

public class BookListFragment extends Fragment {
    private BookDao bookDao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_booklist, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomePage homePage = (HomePage) getActivity();
        bookDao = homePage.getBookDao();
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Book> books = bookDao.fetchAllBooks();
        recyclerView.setAdapter(new BookAdapter(books));

    }
}