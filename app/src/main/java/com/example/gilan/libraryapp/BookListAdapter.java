package com.example.gilan.libraryapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gilan.libraryapp.database.entities.Book;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView title_t;
        private final TextView autor_t ;
        private final TextView publisher_t;
        private final TextView genre_t ;
        private final TextView isbn_t ;
        private final TextView rokwyd_t;
        private final TextView status_t;
        private final TextView edition_t;

        private BookViewHolder(View itemView) {
            super(itemView);
            title_t = (TextView) itemView.findViewById(R.id.texttytul);
            autor_t = (TextView) itemView.findViewById(R.id.textautor);
            publisher_t = (TextView) itemView.findViewById(R.id.textwydawca);
            genre_t = (TextView) itemView.findViewById(R.id.textgatunek);
            isbn_t = (TextView) itemView.findViewById(R.id.textisbn);
            rokwyd_t = (TextView) itemView.findViewById(R.id.textrokwydania);
            status_t = (TextView) itemView.findViewById(R.id.textstatus);
            edition_t = (TextView) itemView.findViewById(R.id.textnredycja);
        }
    }

    private final LayoutInflater mInflater;
    private List<Book> mBooks; // Cached copy of words

    public BookListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.custom_row, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        if (mBooks != null) {
            Book current = mBooks.get(position);
            holder.title_t.setText(current.title);
            holder.autor_t.setText(current.author);
            holder.publisher_t.setText(current.publisher);
            holder.genre_t.setText(Integer.toString(current.genre_id));
            holder.isbn_t.setText(current.isbn_number);
            holder.rokwyd_t.setText( Integer.toString(current.year));

            holder.edition_t.setText(Integer.toString(current.edition));
            holder.status_t.setText(current.state);
        } else {
            // Covers the case of data not being ready yet.
            holder.title_t.setText("No Word");
        }
    }

    public void setBooks(List<Book> books){
        mBooks = books;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mBooks != null)
            return mBooks.size();
        else return 0;
    }
}
