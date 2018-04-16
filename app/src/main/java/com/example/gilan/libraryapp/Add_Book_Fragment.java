package com.example.gilan.libraryapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Add_Book_Fragment extends Fragment {

    ViewGroup container1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
container1= container;
        return inflater.inflate(R.layout.fragment_add_book_, container, false);
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
    }
}
