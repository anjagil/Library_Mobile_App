package com.example.gilan.libraryapp.database.utils;

import android.os.AsyncTask;

import com.example.gilan.libraryapp.database.dao.BookDao;
import com.example.gilan.libraryapp.database.db.BooksRoomDatabase;
import com.example.gilan.libraryapp.database.entities.Book;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
    private final BookDao mDao;

    public PopulateDbAsync(BooksRoomDatabase db) {
        mDao = db.bookDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Book book = new Book("12345567890", "Ania z Zielonego Wzgórza", "zajęta", "S i Ska", 1992, "L.M.Montgomery", 1, 3);
        mDao.insert(book);
        book = new Book("22722232345", "Inny Świat", "zajęta", "Wyd. Literackie", 2013, "G.H.Grudziński", 2, 1);
        mDao.insert(book);
        book = new Book("978837392495", "Tango", "dostępna", "Noir Sur Blanc", 2014, "S. Mrożek", 3, 5);
        mDao.insert(book);
        book = new Book("221234556789", "Kamizelka", "dostępna", "Greg", 2013, "Bolesław Prus", 4, 1);
        mDao.insert(book);
        book = new Book("92890857900", "Granica", "zajęta", "Greg", 2010, "Zofia Nałkowska", 5, 1);
        mDao.insert(book);
        book = new Book("ABCD-EFGH-IJKL_MNOP ", "Zbrodnia i Kara", "dostepna", "K&K", 1992, "Dostojewski", 6, 1);
        mDao.insert(book);
        book = new Book("ABCD-EFGH-IJKL_MNO1 ", "Harry Potter", "zajęta", "wydawnictwo PAN", 2001,  "j.k.rowling", 7,2);
        mDao.insert(book);
        book = new Book("ABCD-EFGH-IJKL_MNO2 ", "1984", "zajęta", "wydawnictwo polskie", 1995,  "G. Orwell", 8, 2);
        mDao.insert(book);
        return null;
    }
}
