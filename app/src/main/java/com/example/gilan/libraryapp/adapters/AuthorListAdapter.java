package com.example.gilan.libraryapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gilan.libraryapp.R;
import com.example.gilan.libraryapp.database.entities.Author;

import java.util.List;

public class AuthorListAdapter extends RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder> {

    class AuthorViewHolder extends RecyclerView.ViewHolder {
        private final TextView author_name;
        private final TextView author_surname;

        private AuthorViewHolder(View itemView) {
            super(itemView);
            author_name = (TextView) itemView.findViewById(R.id.textAuthorName);
            author_surname = (TextView) itemView.findViewById(R.id.textAuthorSurname);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Author item);
    }
        private final LayoutInflater mInflater;
    private List<Author> mAuthors; // Cached copy of words

    public AuthorListAdapter(Context context, OnItemClickListener onItemClickListener, OnItemClickListener itemClickListener) { mInflater = LayoutInflater.from(context); }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.custom_author_row, parent, false);
        return new AuthorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        if (mAuthors != null) {
            Author current = mAuthors.get(position);
            holder.author_name.setText(current.name);
            holder.author_surname.setText(current.surname);
        } else {
            // Covers the case of data not being ready yet.
            holder.author_name.setText("No Word");
        }
    }

    public void setAuthors(List<Author> authors){
        mAuthors = authors;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAuthors != null)
            return mAuthors.size();
        else return 0;
    }
}
