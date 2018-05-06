package com.example.gilan.libraryapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.gilan.libraryapp.unused.Books;
import com.example.gilan.libraryapp.MainActivity;
import com.example.gilan.libraryapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.view.View.*;


public class Sort_Fragment extends Fragment implements OnClickListener {
    List<RadioButton> list_of_buttons = new ArrayList<RadioButton>();
    View rootView;
    Button button;
    RadioButton rb_ascending;
    RadioButton rb_descending;
    RadioButton rb_isbn;
    RadioButton rb_author;
    RadioButton rb_genre;
    RadioButton rb_title;
    String sorting_mode;
    public ArrayAdapter<Books> adapter_f;
    public SendMessage SM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sort_, container,
                false);
        button = rootView.findViewById(R.id.button_sortuj);
        button.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_sortuj:

                if ((rb_ascending.isChecked() || rb_descending.isChecked())
                        && (rb_author.isChecked() || rb_genre.isChecked() ||
                        rb_isbn.isChecked() || rb_title.isChecked())) {

                    if (rb_title.isChecked()) {
                        sorting_mode = "title";
                    } else if (rb_isbn.isChecked()) {
                        sorting_mode = "isbn_number";
                    } else if (rb_genre.isChecked()) {
                        sorting_mode = "genre_id";
                    } else if (rb_author.isChecked()) {
                        sorting_mode = "author_id";
                    } else {
                        return;
                    }
                    if (rb_descending.isChecked()) {
                        SM.sendData("D:"+ sorting_mode);
                    } else {
                        SM.sendData("A:"+sorting_mode);
                    }
                }

                break;
            default: break;
        }
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
        getActivity().setTitle("Sort");

        rb_ascending = rootView.findViewById(R.id.radioButton_ascending);
        rb_descending = rootView.findViewById(R.id.radioButton_descending);
        rb_isbn = rootView.findViewById(R.id.radioButton_isbn);
        rb_author = rootView.findViewById(R.id.radioButton_author);
        rb_genre = rootView.findViewById(R.id.radioButton_genre);
        rb_title = rootView.findViewById(R.id.radioButton_title);
    }

}
