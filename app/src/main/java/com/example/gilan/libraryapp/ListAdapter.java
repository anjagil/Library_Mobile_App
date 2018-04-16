package com.example.gilan.libraryapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListAdapter extends BaseAdapter {
    public static ArrayList<Row_Adapter> data;
    private LayoutInflater mInflater;

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Row_Adapter> objects) {
        //super(context, resource, objects);
        data = objects;
        mInflater = LayoutInflater.from(context);

    }





    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RowBeanHolder holder;

        if(convertView == null){

            //row = inflater.inflate(layoutResourceId, parent, false);
            convertView = mInflater.inflate(R.layout.custom_row, null);
            holder = new RowBeanHolder();
            holder.imgIcon = (ImageView)convertView.findViewById(R.id.imageView2);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.texttytul);
            holder.txtAuthor = (TextView)convertView.findViewById(R.id.textautor);
            holder.txtGenre = (TextView)convertView.findViewById(R.id.textgatunek);

            convertView.setTag(holder);
        }
        else
        {
            holder = (RowBeanHolder)convertView.getTag();
        }

        Row_Adapter object = data.get(position);
        holder.txtTitle.setText(object.title);
        holder.imgIcon.setImageResource(object.icon);
        holder.txtAuthor.setText(object.author);
        holder.txtGenre.setText(object.genre);

        return convertView;
    }

    static class RowBeanHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtAuthor;
        TextView txtGenre;
    }
}
