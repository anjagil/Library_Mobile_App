package com.example.gilan.libraryapp.fragments;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gilan.libraryapp.R;
import com.example.gilan.libraryapp.adapters.BookListAdapter;
import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.viewmodels.BookViewModel;

import java.util.List;

public class BookFragment extends Fragment {
    private BookListAdapter mBookListAdapter;
    public BookViewModel mBookViewModel;

    public BookFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        mBookListAdapter = new BookListAdapter(getContext(),
            new BookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book item) {
                System.out.println(item.title);
            }
        },
            new BookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book item) {
                delete(item);
                }
        });


        rv.setAdapter(mBookListAdapter);
        return rv;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
    }


    public void setAdapter(BookListAdapter mBookListAdapter) {
        this.mBookListAdapter = mBookListAdapter;
    }

    public void booksByGenre(int id) {
        mBookViewModel.byGenre(id).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable final List<Book> books) {
                mBookListAdapter.setBooks(books);
            }
        });
    }

    public void delete(Book book) {
        mBookViewModel.delete(book);
    }

    public void allBooks(String sortBy, String sortDir) {
        mBookViewModel.getAllBooks(sortBy, sortDir).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable final List<Book> books) {
                mBookListAdapter.setBooks(books);
            }
        });
    }

    public void searchBooks(String type, String searchValue) {
        mBookViewModel.searchBooks(type, searchValue).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable final List<Book> books) {
                mBookListAdapter.setBooks(books);
            }
        });
    }




}