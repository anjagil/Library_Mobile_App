package com.example.gilan.libraryapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;


public class Sort_Fragment extends Fragment {

View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_list_, container,
                false);
        return inflater.inflate(R.layout.fragment_sort_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sort");
       // Button button = rootView.findViewById(R.id.button_sortuj);
       RadioButton rb_ascending = (RadioButton) rootView.findViewById(R.id.radioButton_ascending);
        RadioButton rb_descending = (RadioButton) rootView.findViewById(R.id.radioButton_descending);
        final RadioButton rb_isbn = (RadioButton) rootView.findViewById(R.id.radioButton_isbn);
        final RadioButton rb_author = (RadioButton) rootView.findViewById(R.id.radioButton_author);
        final RadioButton rb_genre = (RadioButton) rootView.findViewById(R.id.radioButton_genre);
        RadioButton rb_title = (RadioButton) rootView.findViewById(R.id.radioButton_title);



                // Code here executes on main thread after user presses button
                // user clicks button to sort. If there is no sorting option checked, Teast is appearing, else - list of books is sorted







    }

   // @Override
  //  public void onClick(View v) {
      //  if(rb_isbn.isChecked())
      //  {
            //implement sorting ISBN
     //   }
     //   else if(rb_author.isChecked())
     //   {
            //implement sorting author
     //   }
    //    else if(rb_genre.isChecked())
      //  {
            //implement sorting author
      //  }
      //  else if(rb_genre.isChecked())
      //  {
            //implement sorting author
     //   }
      //  else
      //  {
      //      Toast.makeText(getContext(), "Wybierz Sortowanie", Toast.LENGTH_SHORT);
     //   }
   // }
}
