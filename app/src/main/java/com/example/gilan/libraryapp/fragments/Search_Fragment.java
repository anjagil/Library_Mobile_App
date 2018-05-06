package com.example.gilan.libraryapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.gilan.libraryapp.R;
import com.example.gilan.libraryapp.unused.Books;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;


public class Search_Fragment extends Fragment implements OnClickListener {
    View rootView;

    List<RadioButton> list_of_buttons = new ArrayList<RadioButton>();
    Button searchButton;
    RadioButton rb_isbn;
    RadioButton rb_author;
    RadioButton rb_genre;
    RadioButton rb_title;
    TextView search_value;

    public SendMessage SM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container,
                false);
        searchButton = rootView.findViewById(R.id.button_search);
        searchButton.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        String valueFromField = (this.search_value.getText().toString() != null ?
                this.search_value.getText().toString() : "");
        String searchedValue = "search!";
        switch (v.getId()) {
            case R.id.button_search:
                if (rb_title.isChecked()) {
                    searchedValue += "title";
                } else if (rb_isbn.isChecked()) {
                    searchedValue += "isbn_number";
                } else if (rb_genre.isChecked()) {
                    searchedValue += "genre_id";
                } else if (rb_author.isChecked()) {
                    searchedValue += "author_id";
                } else {
                    return;
                }
                break;
        }
        SM.sendData(searchedValue + "!" + valueFromField);
    }

    public interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Search");

        rb_isbn = rootView.findViewById(R.id.radioButton_isbn);
        rb_author = rootView.findViewById(R.id.radioButton_author);
        rb_genre = rootView.findViewById(R.id.radioButton_genre);
        rb_title = rootView.findViewById(R.id.radioButton_title);
        searchButton = rootView.findViewById(R.id.button_search);
        search_value = rootView.findViewById(R.id.searchText);
    }

}
