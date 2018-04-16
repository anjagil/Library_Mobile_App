package com.example.gilan.libraryapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends Fragment {

    View rootview;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            rootview = inflater.inflate(R.layout.fragment_list_, container, false);
            ArrayList<Row_Adapter> list= new ArrayList<>();
            list.add( new Row_Adapter(R.drawable.ic_menu_send, "Zbrodnia i Kara", " Fiodor Dostojewski", "dramat"));
            ListView lv = ((ListView)rootview.findViewById(R.id.list));
            lv.setAdapter(new ListAdapter(getActivity(),4, list));

            return rootview;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Row_Adapter data[] = new Row_Adapter[] {


                    new Row_Adapter(R.drawable.ic_menu_send, "Zbrodnia i Kara", " Fiodor Dostojewski", "dramat"),
                    new Row_Adapter(R.drawable.ic_menu_send, "Zbrodnia i Kara", " Fiodor Dostojewski", "dramat"),
                    new Row_Adapter(R.drawable.ic_menu_send, "Zbrodnia i Kara", " Fiodor Dostojewski", "dramat"),
                    new Row_Adapter(R.drawable.ic_menu_send, "Zbrodnia i Kara", " Fiodor Dostojewski", "dramat"),
                    new Row_Adapter(R.drawable.ic_menu_send, "Zbrodnia i Kara", " Fiodor Dostojewski", "dramat"),
                    new Row_Adapter(R.drawable.ic_menu_send, "Zbrodnia i Kara", " Fiodor Dostojewski", "dramat"),

            };

            getActivity().setTitle("Sort");
        }
    }
