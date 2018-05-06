package com.example.gilan.libraryapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gilan.libraryapp.adapters.AuthorListAdapter;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.viewmodels.AuthorViewModel;
import com.example.gilan.libraryapp.database.viewmodels.BookViewModel;

public class AuthorFragment extends Fragment {
    private AuthorListAdapter mAuthorListAdapter;
    public AuthorViewModel mAuthorViewModel;
    public AuthorFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        rv.setAdapter(mAuthorListAdapter);

        return rv;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAuthorViewModel = ViewModelProviders.of(this).get(AuthorViewModel.class);
    }
    public void setAdapter(AuthorListAdapter mAuthorListAdapter) {
        this.mAuthorListAdapter = mAuthorListAdapter;
    }
    public void delete(Author book) {
        mAuthorViewModel.delete(book);
    }

}