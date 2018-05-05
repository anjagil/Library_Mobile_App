package com.example.gilan.libraryapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gilan.libraryapp.adapters.BookListAdapter;
import com.example.gilan.libraryapp.adapters.GenreListAdapter;

public class GenreFragment extends Fragment {
    private GenreListAdapter mGenreListAdapter;

    public GenreFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(mGenreListAdapter);
        return rv;
    }

    public void setAdapter(GenreListAdapter mGenreListAdapter) {
        this.mGenreListAdapter = mGenreListAdapter;
    }


}