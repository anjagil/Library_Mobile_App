package com.example.gilan.libraryapp.fragments;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Add_Book_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ViewGroup container1;
    private EditText et_title, et_author, et_genre, et_isbn, et_publisher, et_pub_year, et_edition;
    public SendNewBookData SNBD;
    public GetSpinnerData getData;
    View rootView;
    Spinner spinner_status, spinner_author;
    List<String> categories;
    String state_string;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container1 = container;
        rootView = inflater.inflate(R.layout.fragment_add_book_, container, false);

        // Spinner element
        spinner_status = (Spinner) rootView.findViewById(R.id.spinner);
        spinner_status.setOnItemSelectedListener(this);
        spinner_author = (Spinner) rootView.findViewById(R.id.spinner_autor);
        spinner_author.setOnItemSelectedListener(this);

        categories  = new ArrayList<String>();
//for (int i=1; i<getData.get_spinner_data().size(); i++)
{
//    categories.add(getData.get_spinner_data().get(i).name +getData.get_spinner_data().get(i).surname );
}
// Spinner Drop down elements
        List<String> states = new ArrayList<String>();
        states.add("posiadana");
        states.add("wypożyczona");
        states.add("zarezerwowana");
//dodaj elementy do listy autorów
List<String> authors = new ArrayList<String>();


    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_spinner_item, states);
        }

        // Drop down layout style - list view with radio button
      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
        spinner_status.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter1 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dataAdapter1 = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_spinner_item, authors);
        }

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_author.setAdapter(dataAdapter1);
        return  rootView;

}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        state_string = parent.getItemAtPosition(position).toString();


    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public interface SendNewBookData {
        void send_new_book_data(String author_data, String title_data, String genre_data, String publisher_data, int edition_data, int pub_year_data, String state, String isbn);
    }

    public interface GetSpinnerData {

        List<Author> get_spinner_data();
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
                et_author = (EditText) view.findViewById(R.id.edit_autor);
                et_title = (EditText) view.findViewById(R.id.edit_title);
                et_genre = (EditText) view.findViewById(R.id.edit_gatunek);
                et_isbn = (EditText) view.findViewById(R.id.edit_isbn);
                et_publisher = (EditText) view.findViewById(R.id.edit_wydawca);
                et_edition = (EditText) view.findViewById(R.id.edit_nr_wydania);
                et_pub_year = (EditText) view.findViewById(R.id.edit_rok_wyadania);
                // zappisz dane do listy
                if (et_edition.getText().toString().equals("Nr Wydania")) {
                    et_edition.setText("0");
                }
                if (et_pub_year.getText().toString().equals("Rok Wydania")) {
                    et_pub_year.setText("0");
                }
                et_edition = (EditText) view.findViewById(R.id.edit_nr_wydania);
                et_pub_year = (EditText) view.findViewById(R.id.edit_rok_wyadania);
                if (( et_genre.getText().toString().isEmpty()) ||( et_publisher.getText().toString().isEmpty()) ||( et_title.getText().toString().isEmpty()) ||( et_isbn.getText().toString().isEmpty()) ||( et_edition.getText().toString().isEmpty()) ||( et_pub_year.getText().toString().isEmpty())) {
                    Toast.makeText(getContext(), "Uzupełnij wymagane pola", Toast.LENGTH_SHORT).show();
                } else
                      SNBD.send_new_book_data( "String", et_title.getText().toString(), et_genre.getText().toString(), et_publisher.getText().toString(), Integer.parseInt(et_edition.getText().toString()), Integer.parseInt(et_pub_year.getText().toString()), state_string, et_isbn.getText().toString());
                    //Toast.makeText(getContext(), et_genre.getText().toString(), Toast.LENGTH_SHORT).show();



            }
        });
    }
}
