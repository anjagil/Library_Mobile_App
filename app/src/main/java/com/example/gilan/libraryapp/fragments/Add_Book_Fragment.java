package com.example.gilan.libraryapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gilan.libraryapp.R;

public class Add_Book_Fragment extends Fragment {

    ViewGroup container1;
    private EditText et_title, et_author, et_genre, et_isbn, et_publisher, et_pub_year, et_edition;
    public SendNewBookData SNBD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
container1= container;
        return inflater.inflate(R.layout.fragment_add_book_, container, false);
    }
    public interface SendNewBookData {
        void send_new_book_data(String author_data, String title_data, String genre_data, String publisher_data, int edition_data, int pub_year_data);
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
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Add_photo_fragment nextFrag= new Add_photo_fragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(container1.getId() , nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button button_zapisz = (Button) view.findViewById(R.id.button_edytuj);
        button_zapisz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //sprawdz czy wszystkie pola są zapisane
                et_author = (EditText) view.findViewById(R.id.edit_autor);
                et_title = (EditText) view.findViewById(R.id.edit_title);
                et_genre = (EditText) view.findViewById(R.id.edit_gatunek);
                et_isbn = (EditText) view.findViewById(R.id.edit_isbn);
                et_publisher = (EditText) view.findViewById(R.id.edit_wydawca);
                et_edition = (EditText) view.findViewById(R.id.edit_nr_wydania);
                et_pub_year = (EditText) view.findViewById(R.id.edit_rok_wyadania);
                // zappisz dane do listy
                if(et_edition.getText().toString().equals("Nr Wydania")) {et_edition.setText("0");}
                if(et_pub_year.getText().toString().equals("Rok Wydania")) {et_pub_year.setText("0");}
                et_edition = (EditText) view.findViewById(R.id.edit_nr_wydania);
                et_pub_year = (EditText) view.findViewById(R.id.edit_rok_wyadania);
                if((et_author.getText() !=null && et_title.getText() != null && et_genre != null) || et_edition.getText().toString().equals("Nr Wydania") || et_pub_year.getText().toString().equals("Rok Wydania"))
                {
                    SNBD.send_new_book_data(et_author.getText().toString(), et_title.getText().toString(), et_genre.getText().toString(),et_publisher.getText().toString() , Integer.parseInt(et_edition.getText().toString()), Integer.parseInt(et_pub_year.getText().toString()) );
                }
                else
                    Toast.makeText(getContext(), "Uzupełnij wymagane pola", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
