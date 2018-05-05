package com.example.gilan.libraryapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gilan.libraryapp.R;
import com.example.gilan.libraryapp.database.entities.Genre;

import java.util.List;

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.GenreViewHolder> {

    class GenreViewHolder extends RecyclerView.ViewHolder {
        private final TextView genre_name;


        private GenreViewHolder(View itemView) {
            super(itemView);
            genre_name = (TextView) itemView.findViewById(R.id.textGenreName);
        }
    }

    private final LayoutInflater mInflater;
    private List<Genre> mGenres; // Cached copy of words

    public GenreListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.custom_genre_row, parent, false);
        return new GenreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        if (mGenres != null) {
            Genre current = mGenres.get(position);
            holder.genre_name.setText(current.name);
        } else {
            // Covers the case of data not being ready yet.
            holder.genre_name.setText("No Word");
        }
    }

    public void setGenres(List<Genre> Genres){
        mGenres = Genres;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGenres != null)
            return mGenres.size();
        else return 0;
    }
}
