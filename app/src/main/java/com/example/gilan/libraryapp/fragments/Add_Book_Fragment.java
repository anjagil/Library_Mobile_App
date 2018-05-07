package com.example.gilan.libraryapp.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gilan.libraryapp.R;
import com.example.gilan.libraryapp.database.entities.Author;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    Button button_camera, button_sd;
    ImageView bookView;
    Integer REQUEST_CAMERA=0, SELECT_FILE=1;
    byte[] bookPhotoByte;

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
bookView = (ImageView) rootView.findViewById(R.id.imageView_book) ;
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
                final CharSequence[] items={"Camera","Gallery", "Cancel"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add Image");

                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (items[i].equals("Camera")) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,REQUEST_CAMERA);

                        } else if (items[i].equals("Gallery")) {

                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent, SELECT_FILE);

                        } else if (items[i].equals("Cancel")) {
                            dialogInterface.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        Button button_zapisz = (Button) view.findViewById(R.id.button_edytuj);
        button_zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sprawdz czy wszystkie pola są zapisane
               // et_author = (EditText) view.findViewById(R.id.edit_autor);
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




            }
        });
    }
    // from https://colinyeoh.wordpress.com/2012/05/18/android-convert-image-uri-to-byte-array/
    public byte[] convertImageToByte(Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = getContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public  void onActivityResult( int requestCOe, int resultCode, Intent data)
    {
        super.onActivityResult(requestCOe, resultCode, data);


        if(requestCOe==REQUEST_CAMERA){

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            bookView.setImageBitmap(bitmap);
            //3
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            bookPhotoByte = bytes.toByteArray();
            //4
            // File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
            //  try {
            //     file.createNewFile();
            //     FileOutputStream fo = new FileOutputStream(file);
            //     //5
            //    fo.write(bytes.toByteArray());
            //     fo.close();
            //     Toast.makeText(getContext(), file.getAbsolutePath().toString(), Toast.LENGTH_SHORT).show();
            //  } catch (IOException e) {
            //      // TODO Auto-generated catch block
            //      e.printStackTrace();
            //  }

        }else if(requestCOe==SELECT_FILE){

            Uri selectedImageUri = data.getData();
            Toast.makeText(getContext(), selectedImageUri.toString(), Toast.LENGTH_SHORT).show();
            bookView.setImageURI(selectedImageUri);
            bookPhotoByte = convertImageToByte(selectedImageUri);
        }






    }
}
