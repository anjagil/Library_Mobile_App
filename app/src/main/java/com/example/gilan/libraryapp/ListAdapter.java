package com.example.gilan.libraryapp;

import android.animation.TypeConverter;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListAdapter extends ArrayAdapter<Books>{

    private Context context;
    private List<Books> b_list;
    public ListAdapter(@NonNull Context context, int resource, ArrayList<Books> objects) {
        super(context, resource, objects);
        this.context = context;
        this.b_list = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        Books b_ = b_list.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_row, null);

        TextView title_t = (TextView) view.findViewById(R.id.texttytul);
        TextView autor_t = (TextView) view.findViewById(R.id.textautor);
        TextView publisher_t = (TextView) view.findViewById(R.id.textwydawca);
        TextView genre_t = (TextView) view.findViewById(R.id.textgatunek);
        TextView isbn_t = (TextView) view.findViewById(R.id.textisbn);
        TextView rokwyd_t = (TextView) view.findViewById(R.id.textrokwydania);
        TextView status_t = (TextView) view.findViewById(R.id.textstatus);
        TextView edition_t = (TextView) view.findViewById(R.id.textnredycja);


        title_t.setText(b_.getTitle());
        autor_t.setText(b_.getAuthor());
        publisher_t.setText(b_.getPublisher());
        genre_t.setText(b_.getGenre());
        isbn_t.setText(b_.getIsbn_number());
        rokwyd_t.setText( Integer.toString(b_.getYear()));

        edition_t.setText(Integer.toString(b_.getEdition()));
        status_t.setText(b_.getState());


        return view;
    }
}

