package com.example.gilan.libraryapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gilan.libraryapp.adapters.AuthorListAdapter;

public class AuthorFragment extends Fragment {
    private AuthorListAdapter mAuthorListAdapter;

    public AuthorFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(mAuthorListAdapter);
        return rv;
    }

    public void setAdapter(AuthorListAdapter mAuthorListAdapter) {
        this.mAuthorListAdapter = mAuthorListAdapter;
    }


}