package com.example.gilan.libraryapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gilan.libraryapp.R;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Book;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Book item);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView title_t;
        private final TextView autor_t ;
        private final TextView publisher_t;
        private final TextView genre_t ;
        private final TextView isbn_t ;
        private final TextView rokwyd_t;
        private final TextView status_t;
        private final TextView edition_t;
        private final ImageView editButton;
        private final ImageView removeButton;
        private final ImageView bookImage;

        private BookViewHolder(View itemView) {
            super(itemView);
            title_t = (TextView) itemView.findViewById(R.id.textAuthorSurname);
            autor_t = (TextView) itemView.findViewById(R.id.textautor);
            publisher_t = (TextView) itemView.findViewById(R.id.textwydawca);
            genre_t = (TextView) itemView.findViewById(R.id.textgatunek);
            isbn_t = (TextView) itemView.findViewById(R.id.textisbn);
            rokwyd_t = (TextView) itemView.findViewById(R.id.textrokwydania);
            status_t = (TextView) itemView.findViewById(R.id.textstatus);
            edition_t = (TextView) itemView.findViewById(R.id.textnredycja);
            editButton = (ImageView) itemView.findViewById(R.id.button_edytuj);
            removeButton = (ImageView) itemView.findViewById(R.id.button_usun);
            bookImage = (ImageView) itemView.findViewById(R.id.bookImage);
        }

        public void bind(final Book book, final OnItemClickListener editListener,
                         final OnItemClickListener removeListener) {
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    editListener.onItemClick(book);
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    removeListener.onItemClick(book);
                }
            });
        }

    }

    private final LayoutInflater mInflater;
    private List<Book> mBooks; // Cached copy of words
    private OnItemClickListener editClickListener;
    private OnItemClickListener removeClickListener;

    public BookListAdapter(Context context, OnItemClickListener editListener,
                           OnItemClickListener removeListener) {
        mInflater = LayoutInflater.from(context);
        this.editClickListener = editListener;
        this.removeClickListener = removeListener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mInflater.inflate(R.layout.custom_row, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        if (mBooks != null) {
            Book current = mBooks.get(position);
            holder.title_t.setText(current.title);
//            holder.autor_t.setText(Integer.toString(current.author_id));
            holder.autor_t.setText(current.author_name + current.author_surname);
            holder.publisher_t.setText(current.publisher);
            holder.genre_t.setText(current.genre_name);
            holder.isbn_t.setText(current.isbn_number);
            holder.rokwyd_t.setText( Integer.toString(current.year));

            holder.edition_t.setText(Integer.toString(current.edition));
            holder.status_t.setText(current.state);

            if(current.imageInBytes != null && current.imageInBytes.length > 0) {
                Bitmap bookImageFile = BitmapFactory.decodeByteArray(current.imageInBytes,
                        0, current.imageInBytes.length);
                holder.bookImage.setImageBitmap(bookImageFile);
            }

            holder.bind(current, editClickListener, removeClickListener);
        } else {
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
