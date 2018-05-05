package com.example.gilan.libraryapp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gilan.libraryapp.adapters.AuthorListAdapter;
import com.example.gilan.libraryapp.adapters.BookListAdapter;
import com.example.gilan.libraryapp.adapters.GenreListAdapter;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.entities.Genre;
import com.example.gilan.libraryapp.database.viewmodels.AuthorViewModel;
import com.example.gilan.libraryapp.database.viewmodels.BookViewModel;
import com.example.gilan.libraryapp.database.viewmodels.GenreViewModel;
import com.example.gilan.libraryapp.fragments.Add_Author_Fragment;
import com.example.gilan.libraryapp.fragments.Add_Book_Fragment;
import com.example.gilan.libraryapp.fragments.AuthorFragment;
import com.example.gilan.libraryapp.fragments.BookFragment;
import com.example.gilan.libraryapp.fragments.GenreFragment;
import com.example.gilan.libraryapp.fragments.Sort_Fragment;
import com.example.gilan.libraryapp.unused.Books;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener, Sort_Fragment.SendMessage, Add_Book_Fragment.SendNewBookData{

    public static List<Books> p_books;

    public BookViewModel mBookViewModel;
    private BookListAdapter adapter;

    public GenreViewModel mGenreViewModel;
    private GenreListAdapter genreAdapter;

    public AuthorViewModel mAuthorViewModel;
    private AuthorListAdapter authorAdapter;

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

        adapter = new BookListAdapter(this);
        genreAdapter = new GenreListAdapter(this);
        authorAdapter = new AuthorListAdapter(this);

        createBookListView();
    }

    public void createBookListView() {

        BookFragment fragment = null;
        Class fragment_class = BookFragment.class;

        try {
            fragment = (BookFragment) fragment_class.newInstance();
            fragment.setAdapter(adapter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        mBookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        mBookViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable final List<Book> books) {
                adapter.setBooks(books);
            }
        });
    }

    public void createGenreListView() {

        GenreFragment fragment = null;
        Class fragment_class = GenreFragment.class;

        try {
            fragment = (GenreFragment) fragment_class.newInstance();
            fragment.setAdapter(genreAdapter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        mGenreViewModel = ViewModelProviders.of(this).get(GenreViewModel.class);

        mGenreViewModel.getAllGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(@Nullable final List<Genre> genres) {
                genreAdapter.setGenres(genres);
            }
        });
    }

    public void createAuthorListView() {

        AuthorFragment fragment = null;
        Class fragment_class = AuthorFragment.class;

        try {
            fragment = (AuthorFragment) fragment_class.newInstance();
            fragment.setAdapter(authorAdapter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        mAuthorViewModel = ViewModelProviders.of(this).get(AuthorViewModel.class);

        mAuthorViewModel.getmAllAuthors().observe(this, new Observer<List<Author>>() {
            @Override
            public void onChanged(@Nullable final List<Author> authors) {
                authorAdapter.setAuthors(authors);
            }
        });
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

        if (id == R.id.nav_books) {
            createBookListView();
        }
        else if (id == R.id.nav_genres){
            createGenreListView();
        } else if (id == R.id.nav_authors) {
            createAuthorListView();
        }
        else {
            Fragment fragment = null;
            Class fragment_class = null;

            if (id == R.id.nav_sort) {
                fragment_class = Sort_Fragment.class;
            }
            else if (id == R.id.nav_add) {
                fragment_class = Add_Book_Fragment.class;
            }

            try {
                fragment = (Fragment) fragment_class.newInstance();
                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

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
        fragment_class = BookFragment.class;
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
        fragment_class = BookFragment.class;
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
