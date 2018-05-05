package com.example.gilan.libraryapp.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.gilan.libraryapp.database.dao.GenreDao;
import com.example.gilan.libraryapp.database.db.BooksRoomDatabase;

import com.example.gilan.libraryapp.database.entities.Genre;

import java.util.List;

public class GenreRepository {

    private GenreDao mGenreDao;
    private LiveData<List<Genre>> mAllGenres;

    public GenreRepository(Application application) {
        BooksRoomDatabase db = BooksRoomDatabase.getDatabase(application);
        mGenreDao = db.genreDao();
        mAllGenres = mGenreDao.getAllGenres();
    }

    public LiveData<List<Genre>> getAllGenres() {
        return mAllGenres;
    }

    public void insert(Genre Genre) {
        new insertAsyncTask(mGenreDao).execute(Genre);
    }

    private static class insertAsyncTask extends AsyncTask<Genre, Void, Void> {

        private GenreDao mAsyncTaskDao;

        insertAsyncTask(GenreDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Genre... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
