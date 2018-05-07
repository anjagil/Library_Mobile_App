package com.example.gilan.libraryapp.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gilan.libraryapp.R;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.adapters.BookListAdapter;
import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.entities.Genre;
import com.example.gilan.libraryapp.database.viewmodels.AuthorViewModel;
import com.example.gilan.libraryapp.database.viewmodels.BookViewModel;
import com.example.gilan.libraryapp.database.viewmodels.GenreViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Add_Book_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ViewGroup container1;
    private EditText et_title, et_isbn, et_publisher, et_pub_year, et_edition;

    private BookListAdapter mBookListAdapter;
    public BookViewModel mBookViewModel;

    private AuthorViewModel mAuthorViewModel;
    private GenreViewModel mGenresViewModel;

    public SendNewBookData SNBD;
    public int editedBookId = -1;
    public Book editedBook = null;

    public interface SendNewBookData {
        void send_new_book_data(Book book);
    }

    View rootView;
    Spinner spinner_status, spinner_author, spinner_genre;

    List<Author> authors;
    List<Genre> genres;

    String state_string;

    public ArrayAdapter<String> spinner_data_adapter;
    public ArrayAdapter<Author> author_data_adapter;
    public ArrayAdapter<Genre> genre_data_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            editedBookId = getArguments().size() > 0 ? getArguments().getInt("book_id") : -1;
        }

        mBookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        container1 = container;
        rootView = inflater.inflate(R.layout.fragment_add_book_, container, false);

        // Spinner element
        spinner_status = (Spinner) rootView.findViewById(R.id.spinner);
        spinner_status.setOnItemSelectedListener(this);
        spinner_author = (Spinner) rootView.findViewById(R.id.spinner_autor);
        spinner_author.setOnItemSelectedListener(this);
        spinner_genre = (Spinner) rootView.findViewById(R.id.spinner_gatunek);
        spinner_genre.setOnItemSelectedListener(this);

        et_title = (EditText) rootView.findViewById(R.id.edit_title);
        et_isbn = (EditText) rootView.findViewById(R.id.edit_isbn);
        et_publisher = (EditText) rootView.findViewById(R.id.edit_wydawca);
        et_edition = (EditText) rootView.findViewById(R.id.edit_nr_wydania);
        et_pub_year = (EditText) rootView.findViewById(R.id.edit_rok_wyadania);

        addStates();
        addAuthors();
        addGenres();

        if(editedBookId != -1) {
            inputEditedBook();
        }

        return rootView;
    }

    private void addStates() {
        List<String> states = new ArrayList<String>();
        states.add("posiadana");
        states.add("wypożyczona");
        states.add("zarezerwowana");

        // Creating adapter for spinner
        spinner_data_adapter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            spinner_data_adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),
                    android.R.layout.simple_spinner_item,
                    states);
        }

        // Drop down layout style - list view with radio button
        spinner_data_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_status.setAdapter(spinner_data_adapter);
    }

    private void addAuthors() {
        author_data_adapter = null;
        authors = new ArrayList<Author>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            author_data_adapter = new ArrayAdapter<Author>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, authors);
        }

        author_data_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_author.setAdapter(author_data_adapter);

        mAuthorViewModel = ViewModelProviders.of(this).get(AuthorViewModel.class);

        mAuthorViewModel.getAllAuthors().observe(this, new Observer<List<Author>>() {
            @Override
            public void onChanged(@Nullable final List<Author> dbAuthors) {
                author_data_adapter.addAll(dbAuthors);
            }
        });

    }

    private void addGenres() {
        genre_data_adapter = null;
        genres = new ArrayList<Genre>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            genre_data_adapter = new ArrayAdapter<Genre>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, genres);
        }

        genre_data_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_genre.setAdapter(genre_data_adapter);

        mGenresViewModel = ViewModelProviders.of(this).get(GenreViewModel.class);

        mGenresViewModel.getAllGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(@Nullable final List<Genre> dbGenres) {
                genre_data_adapter.addAll(dbGenres);
            }
        });

    }

    private void inputEditedBook() {
       mBookViewModel.getBookById(editedBookId).observe(this, new Observer<Book>() {
            @Override
            public void onChanged(@Nullable final Book book) {
                editedBook = book;
                et_title.setText(book.title);
                et_isbn.setText(book.isbn_number);
                et_pub_year.setText(Integer.toString(book.year));
                et_publisher.setText(book.publisher);
                et_edition.setText(Integer.toString(book.edition));

                int author_id = 0;
                for(Author author : authors) {
                    if(author.author_id == book.author_fk_id) {
                        author_id = author_data_adapter.getPosition(author);
                    }
                }

                int genre_id = 0;
                for(Genre genre : genres) {
                    if(genre.genre_id == book.genre_fk_id) {
                        genre_id = genre_data_adapter.getPosition(genre);
                    }
                }

                spinner_author.setSelection(author_id);
                spinner_genre.setSelection(genre_id);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        state_string = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SNBD = (SendNewBookData) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Dodaj");
        Button button = (Button) view.findViewById(R.id.button_dodaj_okladke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_photo_fragment nextFrag = new Add_photo_fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(container1.getId(), nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button button_zapisz = (Button) view.findViewById(R.id.button_edytuj);
        button_zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sprawdz czy wszystkie pola są zapisane

                state_string = spinner_status.getSelectedItem().toString();

                if (spinner_author.getSelectedItem().toString() != null &&
                        !et_title.getText().toString().matches("") &&
                        !et_isbn.getText().toString().matches("")  &&
                        !et_publisher.getText().toString().matches("")  &&
                        !et_edition.getText().toString().matches("")  &&
                        !et_pub_year.getText().toString().matches("")  &&
                        spinner_genre.getSelectedItem().toString() != null) {

                    if (editedBookId != -1 && editedBook != null) { // editing book
                        createOrUpdateBook();
                        mBookViewModel.update(editedBook);
                        SNBD.send_new_book_data(editedBook);
                    } else {
                        Book book = createOrUpdateBook();
                        mBookViewModel.insert(book);
                        SNBD.send_new_book_data(book);
                    }
                } else {
                    Toast.makeText(getContext(), "Some data is missing.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Book createOrUpdateBook() {
        Author author = (Author) spinner_author.getSelectedItem();
        Genre genre = (Genre) spinner_genre.getSelectedItem();

        if(editedBook != null) {
            editedBook.title = et_title.getText().toString();
            editedBook.isbn_number = et_isbn.getText().toString();
            editedBook.state = state_string;
            editedBook.publisher = et_publisher.getText().toString();
            editedBook.year = Integer.parseInt(et_pub_year.getText().toString());
            editedBook.genre_fk_id = genre.genre_id;
            editedBook.author_fk_id = author.author_id;
            editedBook.edition = Integer.parseInt(et_edition.getText().toString());
            return editedBook;
        } else {
            Book book = new Book(
                    et_isbn.getText().toString(),
                    et_title.getText().toString(),
                    state_string,
                    et_publisher.getText().toString(),
                    Integer.parseInt(et_pub_year.getText().toString()),
                    author.author_id,
                    genre.genre_id,
                    Integer.parseInt(et_edition.getText().toString())
            );
            return book;
        }
    }
}
