package com.example.gilan.libraryapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.entities.Book;

import java.util.List;

@Dao
public interface AuthorDao {
    @Delete
    public void deleteAuthors(Author... authors);
    @Insert
    void insert(Author author);

    @Query("DELETE FROM authors")
    void deleteAll();

    @Query("SELECT * FROM authors ORDER BY name ASC")
    LiveData<List<Author>> getAllAuthors();




}
