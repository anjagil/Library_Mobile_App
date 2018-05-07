package com.example.gilan.libraryapp.database.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.gilan.libraryapp.database.dao.AuthorDao;
import com.example.gilan.libraryapp.database.dao.BookDao;
import com.example.gilan.libraryapp.database.dao.GenreDao;
import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.entities.Genre;
import com.example.gilan.libraryapp.database.utils.PopulateDbAsync;

@Database(entities = { Book.class, Genre.class, Author.class }, version = 20)
public abstract class BooksRoomDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    public abstract AuthorDao authorDao();
    public abstract GenreDao genreDao();

    private static BooksRoomDatabase INSTANCE;

    public static BooksRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BooksRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        BooksRoomDatabase.class, "books_database")
//                        .fallbackToDestructiveMigration()
//                        .addCallback(sBooksDatabaseCallback)
                        .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sBooksDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

}
