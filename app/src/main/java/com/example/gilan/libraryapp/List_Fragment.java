package com.example.gilan.libraryapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_, container,
                false);


        ArrayList<Books> books = new ArrayList();
       books.add( new Books("ABCD-EFGH-IJKL_MNOP ", "Zbrodnia i Kara", "dostepna", "K&K", 1992, "Dostojewski", "Proza psychologiczna", 1));
        books.add( new Books("ABCD-EFGH-IJKL_MNO1 ", "Harry Potter", "zajęta", "wydawnictwo PAN", 2001,  "j.k.rowling", "fantasy",2));
        books.add( new Books("ABCD-EFGH-IJKL_MNO2 ", "1984", "zajęta", "wydawnictwo polskie", 1995,  "G. Orwell", "fikcja",2));
        //create our new array adapter
        ArrayAdapter<Books> adapter = new ListAdapter(getContext(), 0, books);
       // ArrayAdapter<Books> adapter = new ListAdapter(getActivity(), 0, books);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }
}
