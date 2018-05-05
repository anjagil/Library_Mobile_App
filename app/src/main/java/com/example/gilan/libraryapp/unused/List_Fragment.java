//package com.example.gilan.libraryapp;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.ListFragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class List_Fragment extends ListFragment {
//    public  ArrayAdapter<Books> adapter;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_list_, container,
//                false);
//
//        //
//        //
//        //To be changed when DB is created.
//        // Instead of MainActivity.p_books -> get all elements from db and save into one List.
//        //
//        ArrayList<Books> books = (ArrayList<Books>) MainActivity.p_books;
//
//        //create our new array adapter
//      adapter = new ListAdapter(getContext(), 0, books);
//       // ArrayAdapter<Books> adapter = new ListAdapter(getActivity(), 0, books);
//        ListView listView = (ListView) rootView.findViewById(R.id.list);
//        listView.setAdapter(adapter);
//
//        return rootView;
//    }
//    protected void displayReceivedData(String message)
//    {
//        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
//        adapter.notifyDataSetChanged();
//    }
//}
