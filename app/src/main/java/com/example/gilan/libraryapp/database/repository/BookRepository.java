package com.example.gilan.libraryapp.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.gilan.libraryapp.database.dao.BookDao;
import com.example.gilan.libraryapp.database.db.BooksRoomDatabase;
import com.example.gilan.libraryapp.database.entities.Book;

import java.util.List;

public class BookRepository {

    private BookDao mBookDao;
    private LiveData<List<Book>> mAllBooks;

    public BookRepository(Application application) {
        BooksRoomDatabase db = BooksRoomDatabase.getDatabase(application);
        mBookDao = db.bookDao();
    }

    public LiveData<List<Book>> getAllBooks(String sortBy, String sortDir) {
        boolean isDescending = (sortDir != null && sortDir.equalsIgnoreCase("D"));
        if((sortBy == null || sortBy.equalsIgnoreCase("title")) && isDescending) {
            mAllBooks = mBookDao.getAllBooksDesc();
        } else if(sortBy != null && sortBy.equalsIgnoreCase("isbn_number")) {
            mAllBooks = !isDescending ?
                    mBookDao.getAllBooksByIsbn() : mBookDao.getAllBooksByIsbnDesc();
        } else if(sortBy != null && sortBy.equalsIgnoreCase("genre_id")) {
            mAllBooks = !isDescending ?
                    mBookDao.getAllBooksByGenres() : mBookDao.getAllBooksByGenresDesc();
        } else if(sortBy != null && sortBy.equalsIgnoreCase("author_id")) {
            mAllBooks = !isDescending ?
                    mBookDao.getAllBooksByAuthors() : mBookDao.getAllBooksByAuthorsDesc();
        } else {
            mAllBooks = mBookDao.getAllBooks();
        }
        return mAllBooks;
    }

    public LiveData<List<Book>> searchBooks(String type, String searchValue) {
        switch(type) {
            case "author_id" : {
                mAllBooks = mBookDao.searchBooksByAuthor("%"+searchValue+"%"); break;
            }
            case "title": {
                mAllBooks = mBookDao.searchBooksByTitle("%"+searchValue+"%"); break;
            }
            case "genre_id": {
                mAllBooks = mBookDao.searchBooksByGenre("%"+searchValue+"%"); break;
            }
            case "isbn_number": {
                mAllBooks = mBookDao.searchBooksByIsbn("%"+searchValue+"%"); break;
            }
        }
        return mAllBooks;
    }

    public void deleteBook(Book book) {
        new deleteAsyncTask(mBookDao).execute(book);
    }

    private static class deleteAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDao mAsyncTaskDao;

        deleteAsyncTask(BookDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Book... params) {
            mAsyncTaskDao.deleteBooks(params[0]);
            return null;
        }
    }

    public void insert(Book book) {
        new insertAsyncTask(mBookDao).execute(book);
    }

    public LiveData<List<Book>> getBooksByGenre(int genre_id) {
      return mBookDao.getBooksFromGenre(genre_id);
    }

    private static class insertAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDao mAsyncTaskDao;

        insertAsyncTask(BookDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Book... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
