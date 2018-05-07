package com.example.gilan.libraryapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gilan.libraryapp.database.entities.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    public void insert(Book book);

    @Update
    public void update(Book book);

    @Delete
    public void deleteBooks(Book... books);

    @Query("DELETE FROM books")
    void deleteAll();

    @Query("SELECT * , " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "WHERE books.id = :id " +
            "ORDER BY books.title ASC ")
    LiveData<Book> bookById(int id);

//    @Query("SELECT * FROM books")
    @Query("SELECT * , " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY books.title ASC")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY title DESC")
    LiveData<List<Book>> getAllBooksDesc();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY authors.name ASC")
    LiveData<List<Book>> getAllBooksByAuthors();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY authors.name DESC")
    LiveData<List<Book>> getAllBooksByAuthorsDesc();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY genres.name ASC")
    LiveData<List<Book>> getAllBooksByGenres();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY genres.name DESC")
    LiveData<List<Book>> getAllBooksByGenresDesc();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY isbn_number ASC")
    LiveData<List<Book>> getAllBooksByIsbn();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "ORDER BY isbn_number DESC")
    LiveData<List<Book>> getAllBooksByIsbnDesc();

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "WHERE genres.name LIKE :searchValue ORDER BY title ASC")
    LiveData<List<Book>> searchBooksByGenre(String searchValue);

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "WHERE authors.name LIKE :searchValue ORDER BY books.title ASC")
    LiveData<List<Book>> searchBooksByAuthor(String searchValue);

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "WHERE isbn_number LIKE :searchValue ORDER BY title ASC")
    LiveData<List<Book>> searchBooksByIsbn(String searchValue);

    @Query("SELECT *, " +
            "authors.name as author_name, authors.surname as author_surname, " +
            "genres.name as genre_name " +
            "FROM books " +
            "INNER JOIN authors ON books.author_fk_id = authors.author_id " +
            "INNER JOIN genres ON books.genre_fk_id = genres.genre_id " +
            "WHERE title LIKE :searchValue ORDER BY title ASC")
    LiveData<List<Book>> searchBooksByTitle(String searchValue);

}
