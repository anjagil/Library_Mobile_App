package com.example.gilan.libraryapp;


import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener, Sort_Fragment.SendMessage, Add_Book_Fragment.SendNewBookData{
public static List<Books> p_books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = null;
        Class fragment_class = null ;
        fragment_class = List_Fragment.class;
        try {
            fragment = (Fragment) fragment_class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        //
        //
        //Added a public list, just to check the functionality before DB is created.
        //
        //
        p_books = new ArrayList<>();
        p_books.add(new Books("12345567890", "Ania z Zielonego Wzgórza", "zajęta", "S i Ska", 1992, "L.M.Montgomery", "powieść", 3));
        p_books.add(new Books("22722232345", "Inny Świat", "zajęta", "Wyd. Literackie", 2013, "G.H.Grudziński", "biografia", 1));
        p_books.add(new Books("978837392495", "Tango", "dostępna", "Noir Sur Blanc", 2014, "S. Mrożek", "tragedia", 5));
        p_books.add(new Books("221234556789", "Kamizelka", "dostępna", "Greg", 2013, "Bolesław Prus", "nowela", 1));
        p_books.add(new Books("92890857900", "Granica", "zajęta", "Greg", 2010, "Zofia Nałkowska", "powieść", 1));
        p_books.add( new Books("ABCD-EFGH-IJKL_MNOP ", "Zbrodnia i Kara", "dostepna", "K&K", 1992, "Dostojewski", "Proza psychologiczna", 1));
        p_books.add( new Books("ABCD-EFGH-IJKL_MNO1 ", "Harry Potter", "zajęta", "wydawnictwo PAN", 2001,  "j.k.rowling", "fantasy",2));
        p_books.add( new Books("ABCD-EFGH-IJKL_MNO2 ", "1984", "zajęta", "wydawnictwo polskie", 1995,  "G. Orwell", "fikcja",2));
        //create our new array adapter
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragment_class = null;
        if (id == R.id.nav_camera) {
            fragment_class = List_Fragment.class;
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            fragment_class = Add_Author_Fragment.class;

        } else if (id == R.id.nav_slideshow) {
            fragment_class = List_Fragment.class;
        } else if (id == R.id.nav_manage) {
            fragment_class = Sort_Fragment.class;

        } else if (id == R.id.nav_share) {
            fragment_class = Add_Book_Fragment.class;

        }
        try {
            fragment = (Fragment) fragment_class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        // Close the navigation drawer

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
       // toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
      //  toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "TEXT", Toast.LENGTH_SHORT).show();

    }

    public static void adapter_set()
    {

    }

    @Override
    public void sendData(String message) {
        String tag = message;
        Fragment fragment = null;
        Class fragment_class = null ;
        fragment_class = List_Fragment.class;
        try {
            fragment = (Fragment) fragment_class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }

    @Override
    public void send_new_book_data(String author_data, String title_data, String genre_data , String publisher_data, int edition_data, int pub_year_data) {
       p_books.add(new Books("", title_data,"dostepna", publisher_data, pub_year_data, author_data, genre_data, edition_data));
        Fragment fragment = null;
        Class fragment_class = null ;
        fragment_class = List_Fragment.class;
        try {
            fragment = (Fragment) fragment_class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }


}
