package com.example.gilan.libraryapp.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.gilan.libraryapp.database.dao.AuthorDao;
import com.example.gilan.libraryapp.database.dao.AuthorDao;
import com.example.gilan.libraryapp.database.db.BooksRoomDatabase;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Author;

import java.util.List;

public class AuthorRepository {
    
    private AuthorDao mAuthorDao;
    private LiveData<List<Author>> mAllAuthors;


    public AuthorRepository(Application application) {
        BooksRoomDatabase db = BooksRoomDatabase.getDatabase(application);
        mAuthorDao = db.authorDao();
        mAllAuthors = mAuthorDao.getAllAuthors();
    }

    public LiveData<List<Author>> getAllAuthors() {
        return mAllAuthors;
    }



    public void deleteAuthor(Author author) {
        new deleteAsyncTask(mAuthorDao).execute(author);
    }

    private static class deleteAsyncTask extends AsyncTask<Author, Void, Void> {

        private AuthorDao mAsyncTaskDao;

        deleteAsyncTask(AuthorDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Author... params) {
            mAsyncTaskDao.deleteAuthors(params[0]);
            return null;
        }
    }

}
