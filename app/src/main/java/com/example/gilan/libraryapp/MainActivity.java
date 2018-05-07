package com.example.gilan.libraryapp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
import com.example.gilan.libraryapp.adapters.GenreListAdapter;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.entities.Genre;
import com.example.gilan.libraryapp.database.viewmodels.AuthorViewModel;
import com.example.gilan.libraryapp.database.viewmodels.GenreViewModel;
import com.example.gilan.libraryapp.fragments.Add_Book_Fragment;
import com.example.gilan.libraryapp.fragments.AuthorFragment;
import com.example.gilan.libraryapp.fragments.BookFragment;
import com.example.gilan.libraryapp.fragments.GenreFragment;
import com.example.gilan.libraryapp.fragments.Search_Fragment;
import com.example.gilan.libraryapp.fragments.Sort_Fragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        View.OnClickListener,
        Sort_Fragment.SendMessage,
        Search_Fragment.SendMessage,
        Add_Book_Fragment.SendNewBookData,
        BookFragment.SendMessage {

    public GenreViewModel mGenreViewModel;
    private GenreListAdapter genreAdapter;

    public AuthorViewModel mAuthorViewModel;
    private AuthorListAdapter authorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNavigationAndDrawers();

        genreAdapter = new GenreListAdapter(this);
        authorAdapter = new AuthorListAdapter(this, new AuthorListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Author item) {
                System.out.println(item.name);
            }
        }, new AuthorListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Author item) { mAuthorViewModel.delete(item);
                Log.d("Test", "NIE DZIALA USUWANIE!!!!!!!!!!!!!!!!!!");

            }
        });

        createBookListView(null, null);
    }

    public void createBookListView(final String sortBy, final String sortDir) {
        setTitle("Tytuły");

        final BookFragment fragment = new BookFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).runOnCommit(
                new Runnable() {
                    public void run() {
                        fragment.allBooks(sortBy, sortDir);
                    }
                }
        ).commit();
    }

    public void createSearchBookListView(final String type, final String searchValue) {
        final BookFragment fragment = new BookFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).runOnCommit(
                new Runnable() {
                    public void run() {
                        fragment.searchBooks(type, searchValue);
                    }
                }
        ).commit();
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

        mAuthorViewModel.getAllAuthors().observe(this, new Observer<List<Author>>() {
            @Override
            public void onChanged(@Nullable final List<Author> authors) {
                authorAdapter.setAuthors(authors);
            }
        });
    }

    public void openBookInAddView(int id) {
        Add_Book_Fragment fragment = null;
        Class fragment_class = Add_Book_Fragment.class;

        try {
            fragment = (Add_Book_Fragment) fragment_class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("book_id", id);

        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
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

    @Override
    public void sendData(String message) {
        if(message.contains("OpenBook")) {
            String id = message.split(":")[1];
            openBookInAddView(Integer.parseInt(id));
        } else if(message.substring(0,6).equalsIgnoreCase("search")) {
             String[] messageData = message.split("!");
             String type = messageData[1];
             String value = messageData.length > 2 ? messageData[2] : "";

             createSearchBookListView(type, value);
        } else {
            String type = message.substring(2);
            String sort = message.substring(0,1);

            createBookListView(type, sort);
        }
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

    @Override
    public void send_new_book_data(Book book) {
        createBookListView(null, null);
    }

    public void createNavigationAndDrawers() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_books) {
            createBookListView(null, null);
        } else if (id == R.id.nav_genres){
            createGenreListView();
        } else if (id == R.id.nav_authors) {
            createAuthorListView();
        } else {
            Fragment fragment = null;
            Class fragment_class = null;

            if (id == R.id.nav_sort) {
                fragment_class = Sort_Fragment.class;
            }
            else if (id == R.id.nav_add) {
                fragment_class = Add_Book_Fragment.class;
            } else if (id == R.id.nav_search) {
                fragment_class = Search_Fragment.class;
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

}
