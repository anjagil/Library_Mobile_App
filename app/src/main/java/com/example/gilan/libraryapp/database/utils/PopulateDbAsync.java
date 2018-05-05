package com.example.gilan.libraryapp.database.utils;

import android.os.AsyncTask;

import com.example.gilan.libraryapp.database.dao.AuthorDao;
import com.example.gilan.libraryapp.database.dao.BookDao;
import com.example.gilan.libraryapp.database.dao.GenreDao;
import com.example.gilan.libraryapp.database.db.BooksRoomDatabase;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.entities.Genre;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
    private final BookDao mBookDao;
    private final GenreDao mGenreDao;
    private final AuthorDao mAuthorDao;

    public PopulateDbAsync(BooksRoomDatabase db) {
        mBookDao = db.bookDao();
        mGenreDao = db.genreDao();
        mAuthorDao = db.authorDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mAuthorDao.deleteAll();
        mGenreDao.deleteAll();
        mBookDao.deleteAll();

        Author author = new Author("L.M","Montgomery");
        mAuthorDao.insert(author);
        author = new Author("G.H","Grudziński");
        mAuthorDao.insert(author);
        author = new Author("S.","Mrożek");
        mAuthorDao.insert(author);
        author = new Author("Bolesław","Prus");
        mAuthorDao.insert(author);
        author = new Author("Zofia","Nałkowska");
        mAuthorDao.insert(author);
        author = new Author("Fiodor","Dostojewski");
        mAuthorDao.insert(author);
        author = new Author("J.K","Rowling");
        mAuthorDao.insert(author);
        author = new Author("G.","Orwell");
        mAuthorDao.insert(author);

        Genre genre = new Genre("powieść");
        mGenreDao.insert(genre);
        genre = new Genre("biografia");
        mGenreDao.insert(genre);
        genre = new Genre("tragedia");
        mGenreDao.insert(genre);
        genre = new Genre("nowela");
        mGenreDao.insert(genre);
        genre = new Genre("powieść");
        mGenreDao.insert(genre);
        genre = new Genre("Proza psychologiczna");
        mGenreDao.insert(genre);
        genre = new Genre("fantasy");
        mGenreDao.insert(genre);
        genre = new Genre("fikcja");
        mGenreDao.insert(genre);

        Book book = new Book("12345567890", "Ania z Zielonego Wzgórza", "zajęta", "S i Ska", 1992, 0, 0, 3);
        mBookDao.insert(book);
        book = new Book("22722232345", "Inny Świat", "zajęta", "Wyd. Literackie", 2013, 1, 1, 1);
        mBookDao.insert(book);
        book = new Book("978837392495", "Tango", "dostępna", "Noir Sur Blanc", 2014, 2, 2, 5);
        mBookDao.insert(book);
        book = new Book("221234556789", "Kamizelka", "dostępna", "Greg", 2013, 3, 3, 1);
        mBookDao.insert(book);
        book = new Book("92890857900", "Granica", "zajęta", "Greg", 2010, 4, 4, 1);
        mBookDao.insert(book);
        book = new Book("ABCD-EFGH-IJKL_MNOP ", "Zbrodnia i Kara", "dostepna", "K&K", 1992, 5, 5, 1);
        mBookDao.insert(book);
        book = new Book("ABCD-EFGH-IJKL_MNO1 ", "Harry Potter", "zajęta", "wydawnictwo PAN", 2001,  6, 6,2);
        mBookDao.insert(book);
        book = new Book("ABCD-EFGH-IJKL_MNO2 ", "1984", "zajęta", "wydawnictwo polskie", 1995,  7, 7, 2);
        mBookDao.insert(book);

        return null;
    }
}
