package com.example.gilan.libraryapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.gilan.libraryapp.database.entities.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(Book book);

    @Delete
    public void deleteBooks(Book... books);

    @Query("DELETE FROM books")
    void deleteAll();

    @Query("SELECT * FROM books ORDER BY title ASC")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM books ORDER BY title DESC")
    LiveData<List<Book>> getAllBooksDesc();

    @Query("SELECT * FROM books ORDER BY author_id ASC")
    LiveData<List<Book>> getAllBooksByAuthors();

    @Query("SELECT * FROM books ORDER BY author_id DESC")
    LiveData<List<Book>> getAllBooksByAuthorsDesc();

    @Query("SELECT * FROM books ORDER BY genre_id ASC")
    LiveData<List<Book>> getAllBooksByGenres();

    @Query("SELECT * FROM books ORDER BY genre_id DESC")
    LiveData<List<Book>> getAllBooksByGenresDesc();

    @Query("SELECT * FROM books ORDER BY isbn_number ASC")
    LiveData<List<Book>> getAllBooksByIsbn();

    @Query("SELECT * FROM books ORDER BY isbn_number DESC")
    LiveData<List<Book>> getAllBooksByIsbnDesc();

    @Query("SELECT * FROM books WHERE genre_id LIKE :searchValue ORDER BY title ASC")
    LiveData<List<Book>> searchBooksByGenre(String searchValue);

    @Query("SELECT * FROM books WHERE author_id = :searchValue ORDER BY title ASC")
    LiveData<List<Book>> searchBooksByAuthor(String searchValue);

    @Query("SELECT * FROM books WHERE isbn_number = :searchValue ORDER BY title ASC")
    LiveData<List<Book>> searchBooksByIsbn(String searchValue);

    @Query("SELECT * FROM books WHERE title LIKE :searchValue ORDER BY title ASC")
    LiveData<List<Book>> searchBooksByTitle(String searchValue);

    //    @Query("SELECT * FROM books WHERE genre_id = :genre ORDER BY title ASC")
    @Query("SELECT * FROM books WHERE genre_id = :genre ORDER BY title DESC")
    LiveData<List<Book>> getBooksFromGenre(int genre);

}
